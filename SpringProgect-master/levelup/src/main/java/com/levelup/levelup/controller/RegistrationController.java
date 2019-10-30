package com.levelup.levelup.controller;

import com.levelup.levelup.entity.Role;
import com.levelup.levelup.entity.User;
import com.levelup.levelup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
        user.setActive(true);
        user.setName(user.getUsername());
        user.setSurname(user.getUsername());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.STUDENT);
        roles.add(Role.MENTOR);
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/login";
    }
}
