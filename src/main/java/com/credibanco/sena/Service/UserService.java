package com.credibanco.sena.Service;
import java.util.List;

import com.credibanco.sena.Dto.UserDtoRequest;
import com.credibanco.sena.Dto.UserDtoResponse;
import com.credibanco.sena.Entity.User;
public interface UserService {

    UserDtoResponse createUser (UserDtoRequest usuarioDTORequest) ;
    List<UserDtoResponse> readUser (Long id);
    UserDtoResponse updateUser (UserDtoRequest usuarioDTORequest);
    UserDtoResponse deleteUser (Long id );

}
