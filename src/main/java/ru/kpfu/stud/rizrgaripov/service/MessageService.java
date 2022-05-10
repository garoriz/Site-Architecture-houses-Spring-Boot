package ru.kpfu.stud.rizrgaripov.service;

import ru.kpfu.stud.rizrgaripov.dto.CreateMessageDto;
import ru.kpfu.stud.rizrgaripov.dto.MessageDto;
import ru.kpfu.stud.rizrgaripov.model.Message;
import ru.kpfu.stud.rizrgaripov.model.User;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll(int userId, int recipientId);

    void save(CreateMessageDto createMessageDto);
}
