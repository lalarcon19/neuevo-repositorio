package com.credibanco.sena.Controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.credibanco.sena.Dto.UserDtoRequest;
import com.credibanco.sena.Dto.UserDtoResponse;
import com.credibanco.sena.Service.Impl.UserImpl;
import com.credibanco.sena.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    class userController {
        Logger logger = LoggerFactory.getLogger(UserController.class);

        @Autowired
        UserImpl userImpl;
        @Autowired
        UserService usuarioService;


        @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Object> createUser(@RequestBody UserDtoRequest userDtoRequest) {
            try {
                logger.info("----HttpRequest Create User ----");
                UserDtoResponse response = userImpl.createUser(userDtoRequest);
                logger.info("----Final de la peticion ----");
                return ResponseEntity.ok(response + "Usuario creado");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear usuario");
            }
        }

        @GetMapping("/read/{id}")
        public ResponseEntity<Map<String, Object>> readUser(@PathVariable Long id) {
            Map<String, Object> response = new HashMap<>();
            try {
                response = new HashMap<>();
                List<UserDtoResponse> listUser = this.usuarioService.readUser(id);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/update")
        public UserDtoResponse updateUser(@RequestBody UserDtoRequest userDTORequest) {
          UserDtoResponse userDtoResponse = userImpl.updateUser(userDTORequest);
          return userDtoResponse;
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
            try {
                UserDtoResponse res = usuarioService.deleteUser(id);
                return new ResponseEntity<>(res, HttpStatus.OK);

            } catch (Exception e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
                logger.info("-----Hubo un error-----");
            }
            return null;
        }
    }

}
