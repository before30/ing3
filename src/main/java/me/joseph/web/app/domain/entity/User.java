package me.joseph.web.app.domain.entity;

import com.google.common.base.MoreObjects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ING_USER")
public class User {

    @Id
    @NotNull
    @Size(max = 64)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 64)
    @Column(name = "password", nullable = false)
    private String password;

    protected User(){}

    public User(final String id, final String password){
        this.id = id;
        this.password = password;
    }

    public String getId(){
        return id;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", password)
                .toString();
    }
}
