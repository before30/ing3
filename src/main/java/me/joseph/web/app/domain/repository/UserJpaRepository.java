package me.joseph.web.app.domain.repository;

import me.joseph.web.app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
}
