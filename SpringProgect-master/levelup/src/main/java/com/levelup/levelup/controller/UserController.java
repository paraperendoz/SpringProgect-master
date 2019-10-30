package com.levelup.levelup.controller;

import com.levelup.levelup.entity.Role;
import com.levelup.levelup.entity.User;
import com.levelup.levelup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('MENTOR')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/userlist")
    public String userList(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String role,
            Model model) {
        Iterable<User> users;
        if (filter != null && !filter.isEmpty()) {
            users = userRepository.findByName(filter);
        }
        else if (role != null && !role.isEmpty()){
            Set<Role> userroles = new HashSet<>();
            if(role.equalsIgnoreCase("STUDENT")) {
                userroles.add(Role.STUDENT);
            }
            else if(role.equalsIgnoreCase("MENTOR")) {
                userroles.add(Role.STUDENT);
                userroles.add(Role.MENTOR);
            }
            else if(role.equalsIgnoreCase("ADMIN")) {
                userroles.add(Role.STUDENT);
                userroles.add(Role.MENTOR);
                userroles.add(Role.ADMIN);
            }
            users = userRepository.findByRoles(userroles);
        }
        else {
            users = userRepository.findAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("role", role);
        model.addAttribute("filter", filter);
        return "userlist";
    }

    @GetMapping("/adduser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUserForm() {
        return "adduser";
    }


    @PostMapping("/adduser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addUser(@RequestParam String name, @RequestParam String surname,  Model model) {
        if(name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            return  "adduser";
        }
        User n = new User();
        n.setName(name);
        n.setSurname(surname);
        n.setUsername(name+surname);
        n.setPassword(name);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.STUDENT);
        n.setRoles(roles);
        userRepository.save(n);
        return "redirect:/userlist";
    }


    @GetMapping("/user/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveUser(
            @RequestParam String username,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user,
            @RequestParam("file") MultipartFile file
            ) throws IOException {

        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        for (String key : form.keySet()) {
            if( roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + filename));

            user.setFilename(filename);
        }

        userRepository.save(user);

        return "redirect:/userlist";
    }
}
