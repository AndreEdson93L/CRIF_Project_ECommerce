package com.ecommercecrif.E_Commerce_application.mapper;

import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import com.ecommercecrif.E_Commerce_application.model.dto.RegisterUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UpdateUserDTO;
import com.ecommercecrif.E_Commerce_application.model.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity dtoToUserEntity(RegisterUserDTO registerUserDTO);

    //To return the DTO as response body JSON
    UserResponseDTO userEntityToDto(UserEntity userEntity);

    UpdateUserDTO userEntityToUpdateUserDto(UserEntity userEntity);




}
