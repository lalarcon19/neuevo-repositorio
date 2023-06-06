package com.credibanco.sena.Repository;

import com.credibanco.sena.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRespository  extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User findAllById(Long id);
}
