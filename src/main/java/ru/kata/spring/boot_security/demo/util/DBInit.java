package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DBInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        List<Role> adminSet = new ArrayList<>();
        List<Role> userSet = new ArrayList<>();

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        adminSet.add(roleAdmin);
        //adminSet.add(roleUser);
        userSet.add(roleUser);

        User newUser = new User("Ivan", (byte)23, "ivan@mail.com", "$2a$12$AtgSt9sSUS.H2UcJUWyUWu5qviJDWN6nExND80cCUiu0fJ6kLDzWW", "user1", adminSet);
        User admin = new User("Garry", (byte) 30, "garry@gmail.com", "$2a$12$AtgSt9sSUS.H2UcJUWyUWu5qviJDWN6nExND80cCUiu0fJ6kLDzWW", "user2", userSet);

        userService.saveUser(newUser);
        userService.saveUser(admin);
    }
}