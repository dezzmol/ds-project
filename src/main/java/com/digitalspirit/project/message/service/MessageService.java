package com.digitalspirit.project.message.service;

import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.message.dto.MessageDTO;
import com.digitalspirit.project.message.entity.Message;
import com.digitalspirit.project.message.mapper.MessageMapper;
import com.digitalspirit.project.message.repository.MessageRepository;
import com.digitalspirit.project.user.User;
import com.digitalspirit.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;
    private final MessageMapper messageMapper;

    public void createMessageTo(Long userId, String message) {
        User user = userService.getUserById(userId);

        Message newMessage = Message.builder()
                .user(user)
                .message(message)
                .build();

        messageRepository.save(newMessage);
    }

    public void createMessageTo(User user, String message) {
        Message newMessage = Message.builder()
                .user(user)
                .message(message)
                .build();

        messageRepository.save(newMessage);
    }

    public void createMessageForAdmins(String message) {
        List<User> users = userService.getAdmins();

        for (User user : users) {
            createMessageTo(user, message);
        }
    }

    public List<MessageDTO> getMessages() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        List<Message> messages = messageRepository.findByUser(user)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Messages not found"));

        List<MessageDTO> messageDTOS = new ArrayList<>();
        for (Message message : messages) {
            messageDTOS.add(messageMapper.toMessageDTO(message));
        }

        return messageDTOS;
    }
}
