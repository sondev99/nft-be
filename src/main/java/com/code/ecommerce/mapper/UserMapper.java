package com.code.ecommerce.mapper;



import com.code.ecommerce.dto.request.RegisterRequest;
import com.code.ecommerce.dto.request.UserRequest;
import com.code.ecommerce.dto.response.UserDto;
import com.code.ecommerce.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User>{
    User reqToEntity(RegisterRequest registerRequest);
    User reqUserToEntity(UserRequest userRequest);

    UserDto toDto(User user);
}