package me.joseph.web.app.domain.repository;

import me.joseph.web.app.domain.entity.SomeThing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SomeThingDao {
    void save(SomeThing o);

    void update(SomeThing o);

    void delete(SomeThing o);

    SomeThing get(final Class<SomeThing> type, Long id);

    List<SomeThing> getAll(final Class<SomeThing> type);

    List<SomeThing> getByName(final Class<SomeThing> type, String name);

    Page<SomeThing> myFindAll(Pageable pageable);
    
}
