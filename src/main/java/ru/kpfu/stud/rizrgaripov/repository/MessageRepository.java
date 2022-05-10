package ru.kpfu.stud.rizrgaripov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.stud.rizrgaripov.model.Message;
import ru.kpfu.stud.rizrgaripov.model.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByUserIdAndRecipientId(int userId, int recipientId);
}
