package com.digitalspirit.project.message.mapper;

import com.digitalspirit.project.message.dto.MessageDTO;
import com.digitalspirit.project.message.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDTO toMessageDTO(Message message);
}
