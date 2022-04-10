package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.stud.rizrgaripov.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users u where u.login like ?1", nativeQuery = true)
    Optional<User> get(String login);
}
