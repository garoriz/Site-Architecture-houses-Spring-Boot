package ru.kpfu.stud.rizrgaripov.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.stud.rizrgaripov.dto.ArticleDto;
import ru.kpfu.stud.rizrgaripov.dto.CreateMessageDto;
import ru.kpfu.stud.rizrgaripov.dto.MessageDto;
import ru.kpfu.stud.rizrgaripov.model.Article;
import ru.kpfu.stud.rizrgaripov.model.Message;
import ru.kpfu.stud.rizrgaripov.model.User;
import ru.kpfu.stud.rizrgaripov.repository.MessageRepository;
import ru.kpfu.stud.rizrgaripov.service.MessageService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<MessageDto> getAll(User user, User recipient) {
        return messageRepository
                .findAllByUserAndRecipient(user, recipient)
                .stream()
                .map(MessageDto::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public void save(CreateMessageDto createMessageDto) {
        Message message = new Message(
                createMessageDto.getContent(),
                createMessageDto.getUser(),
                createMessageDto.getRecipient()
        );
        messageRepository.save(message);
    }
}
