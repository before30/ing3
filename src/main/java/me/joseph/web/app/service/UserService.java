package me.joseph.web.app.service;

import me.joseph.common.util.LogUtils;
import me.joseph.web.app.domain.entity.User;
import me.joseph.web.app.domain.repository.UserJpaRepository;
import me.joseph.web.app.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class UserService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Transactional
    public User save(@NotNull @Valid final User user){
        LogUtils.debugLog.info("Creating {}", user);
        User existing = userJpaRepository.findOne(user.getId());

        if (existing != null) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.getId()));
        }

        return userJpaRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getList(){
        LogUtils.debugLog.info("Retrieving the list of all users");
        return userJpaRepository.findAll();
    }

}
