package me.joseph.web.app.validator;

import com.google.common.base.MoreObjects;
import me.joseph.web.app.domain.UserCreateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserCreateFormPasswordValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return UserCreateForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserCreateForm form = (UserCreateForm)o;
        if (!Objects.equals(form.getPassword1(), form.getPassword2())){
            errors.rejectValue("password2", "user.error.password.no_match");
        }
    }
}
