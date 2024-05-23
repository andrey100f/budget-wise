package com.ubb.budgetwise_users.model.mapper;

import com.ubb.budgetwise_users.model.User;
import com.ubb.budgetwise_users.model.dto.AddUserDto;
import com.ubb.budgetwise_users.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapDtoDto(User user);
    User mapFromAddDtoToModel(AddUserDto userDto);
    User mapToModel(UserDto userDto);

}
