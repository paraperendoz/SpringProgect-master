package com.levelup.levelup.repository;

import com.levelup.levelup.entity.Role;
import com.levelup.levelup.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    List<User> findByRoles(Set<Role> roles);
    User findByUsername(String username);
}
