package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.stud.rizrgaripov.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users u where u.login like ?1", nativeQuery = true)
    Optional<User> get(String login);

    Optional<User> getUserById(int id);

    @Transactional
    @Modifying
    @Query(value = "update users u set name = ?2 where u.login = ?1", nativeQuery = true)
    void changeName(String login, String name);

    @Transactional
    @Modifying
    @Query(value = "update users u set surname = ?2 where u.login = ?1", nativeQuery = true)
    void changeSurname(String login, String surname);

    @Transactional
    @Modifying
    @Query(value = "update users u set url_photo = ?2 where u.login = ?1", nativeQuery = true)
    void changeUrlPhoto(String login, String urlPhoto);

    @Transactional
    @Modifying
    @Query(value = "update users u set status = ?2 where u.login = ?1", nativeQuery = true)
    void changeStatus(String login, String status);

    @Transactional
    @Modifying
    @Query(value = "update users u set password = ?2 where u.login = ?1", nativeQuery = true)
    void changePassword(String login, String password);
}
