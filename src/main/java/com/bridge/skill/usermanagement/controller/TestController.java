package com.bridge.skill.usermanagement.controller;

import com.bridge.skill.usermanagement.dao.UserRepository;
import com.bridge.skill.usermanagement.dto.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @PostMapping("/test")
    public String test(@RequestBody User user) {
        try {
            final User save = this.userRepository.save(user);
            System.out.println(save);
        } catch (Exception e) {
            System.out.println(e);
            return "fail";
        }
        return "success";
    }

}
