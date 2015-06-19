package me.joseph.web.app.service;

import me.joseph.web.app.domain.entity.SomeThing;
import me.joseph.web.app.domain.repository.SomeThingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SomeThingService {

    @Autowired
    private SomeThingDao someThingDao;

    public void save(SomeThing s){
        someThingDao.save(s);
    }

    public SomeThing find(Long id){
        return someThingDao.get(SomeThing.class, id);
    }

    public List<SomeThing> findAll(){
        return someThingDao.getAll(SomeThing.class);
    }

    public Page<SomeThing> myFindAll(Pageable pageable){
        return someThingDao.myFindAll(pageable);
    }

}
