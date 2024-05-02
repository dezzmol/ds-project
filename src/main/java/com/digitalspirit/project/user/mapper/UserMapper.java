package com.digitalspirit.project.user.mapper;

import com.digitalspirit.project.user.User;
import com.digitalspirit.project.user.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User user);
}
