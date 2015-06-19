package me.joseph.web.app.domain.repository;

import me.joseph.web.app.domain.entity.SomeThing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SomeThingJpaRepository extends JpaRepository<SomeThing, Long>{
        SomeThing findByNameAllIgnoringCase(
                @Param("name") String name
        );

        @Query("SELECT s FROM SomeThing s")
        Page<SomeThing> myFindAll(Pageable pageable);
}
