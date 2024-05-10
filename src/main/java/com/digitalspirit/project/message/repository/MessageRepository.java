package com.digitalspirit.project.message.repository;

import com.digitalspirit.project.message.entity.Message;
import com.digitalspirit.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findByUser(User user);
}
