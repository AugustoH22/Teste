package com.adesp.festival.authentication.infrastructure.mappers;

import com.adesp.festival.authentication.application.dtos.request.SignUpRequest;
import com.adesp.festival.authentication.application.dtos.response.SignUpResponse;
import com.adesp.festival.authentication.application.services.TokenService;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "email", ignore = true)
    User signUpRequestToDomain(SignUpRequest signUpRequest);

    @Mapping(target = "role", source = "role", qualifiedByName = "roleEnumToString")
    SignUpResponse userToSignUpResponse(User user);

    @Named("roleEnumToString")
    default String roleEnumToString(Roles role){
        return role.name();
    }
}
