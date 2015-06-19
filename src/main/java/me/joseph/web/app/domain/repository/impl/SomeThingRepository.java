package me.joseph.web.app.domain.repository.impl;

import me.joseph.web.app.domain.entity.SomeThing;
import me.joseph.web.app.domain.repository.SomeThingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class SomeThingRepository implements SomeThingDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void save(SomeThing o) {
        entityManager.persist(o);
    }

    @Override
    public void update(SomeThing o) {

    }

    @Override
    public void delete(SomeThing o) {

    }

    @Override
    public SomeThing get(Class<SomeThing> type, Long id) {
        return entityManager.find(type, id);
    }

    @Override
    public List<SomeThing> getAll(Class<SomeThing> type) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SomeThing> cq = cb.createQuery(SomeThing.class);
        Root<SomeThing> rootEntry = cq.from(SomeThing.class);
        CriteriaQuery<SomeThing> all = cq.select(rootEntry);
        TypedQuery<SomeThing> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<SomeThing> getByName(Class<SomeThing> type, String name) {
        return null;
    }

    @Override
    public Page<SomeThing> myFindAll(Pageable pageable) {
        CriteriaQuery<Long> countQuery = entityManager.getCriteriaBuilder().createQuery(Long.class);
        countQuery.select(entityManager.getCriteriaBuilder().count(countQuery.from(SomeThing.class)));
        Long count = entityManager.createQuery(countQuery).getSingleResult();

        Query query = entityManager.createQuery("SELECT s FROM SomeThing s");
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<SomeThing> someThings = query.getResultList();

        Page<SomeThing> page = new PageImpl<SomeThing>(someThings, pageable, count);

        return page;
    }

}
