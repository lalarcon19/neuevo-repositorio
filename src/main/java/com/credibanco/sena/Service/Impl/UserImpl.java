package com.credibanco.sena.Service.Impl;
import java.util.List;
import java.util.Optional;


import com.credibanco.sena.Dto.UserDtoRequest;
import com.credibanco.sena.Dto.UserDtoResponse;
import com.credibanco.sena.Entity.User;
import com.credibanco.sena.Repository.IUserRespository;
import com.credibanco.sena.Service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserImpl.class);
    @Autowired
    IUserRespository userRepo;
    public UserDtoResponse createUser(UserDtoRequest userDTORequest) {
        logger.info("---llego al service--");
        try {
            User entity = new User();
            entity.setId(userDTORequest.getId());
            entity.setUserName(userDTORequest.getUserName());
            entity.setPassword(userDTORequest.getPassword());
            entity = userRepo.save(entity);

            logger.info("--Registro en base de datos--");

            UserDtoResponse response = new UserDtoResponse();
            response.setId(entity.getId());
            response.setPassword(entity.getPassword());
            response.setUserName(entity.getUserName());
            logger.info("----termino al proceso del service-----");
            return response;
        } catch (Exception e) {
            logger.error("-----Error al crear un nuevo usuario-----");
        }
        return null;
    }
    @Override
    public List<UserDtoResponse> readUser(Long id) {
        UserDtoResponse userResponse = new UserDtoResponse();
        List<User> listUser= userRepo.findAll();
        return null;
    }
    @Override
    public UserDtoResponse updateUser(UserDtoRequest  userDtoRequest) {
    User user= userRepo.findAllById(userDtoRequest.getId());
    try {
        if ( user != null) {
            user.setId(userDtoRequest.getId());
            user.setUserName(userDtoRequest.getUserName());
            user.setPassword(user.getPassword());
            userRepo.save(user);
            logger.info("-----Se actualizo en la base de datos-------");

            UserDtoResponse userDtoResponse = new UserDtoResponse();
            userDtoResponse.setId(user.getId());
            userDtoResponse.setUserName(user.getUserName());
            userDtoResponse.setPassword(user.getPassword());
            return userDtoResponse;
        } logger.info("-----El usuario se actualizo-------");

    }catch (Exception e){
        logger.info("----No se actualizo el  usuario ----");
    }
    return null;
    }@Override
    public UserDtoResponse deleteUser(Long id) {

        UserDtoResponse response = new UserDtoResponse();
        try {
            Optional<User> usuarioDelete = userRepo.findById(id);

            if (usuarioDelete != null) {
                userRepo.deleteById(id);
            }
            logger.info("-----El ususario ha sido eliminado-------- ");

        } catch (Exception e) {
            logger.info("-----Error----" + e);
        }
        return null;
    }
}
