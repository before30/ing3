package me.joseph.web.app.domain.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SOMETHING")
public class SomeThing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public SomeThing(){}

    public SomeThing(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id" , id)
                .add("name", name)
                .toString();
    }
}
