package me.joseph.web.app.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserCreateForm {
    @NotEmpty
    @Size(max = 64)
    private String id;

    @NotEmpty
    @Size(max = 64)
    private String password1;
    private String password2;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword1(){
        return this.password1;
    }

    public void setPassword1(String password1){
        this.password1 = password1;
    }

    public String getPassword2(){
        return this.password2;
    }

    public void setPassword2(String password2){
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password1", password1)
                .add("password2", password2)
                .toString();
    }
}
