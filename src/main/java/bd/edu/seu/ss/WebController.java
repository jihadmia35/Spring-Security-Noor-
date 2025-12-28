package bd.edu.seu.ss;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static bd.edu.seu.ss.CustomAuthenticationProvider.userMap;

@Controller
public class WebController {

    public final PasswordEncoder passwordEncoder;

    public WebController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/dashboard")
    public String dashboard(){

        return "dashboard";
    }


    @GetMapping("/admin-dashboard")
    public String adminDashboard(){

        return "admin";
    }

    @GetMapping("/about-us")
    public String aboutus(){

        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // return login.html
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String registrationProcess(@ModelAttribute UserDto user) {
        IO.println(user);

        String encryptedPassword = passwordEncoder.encode(user.pin());
        IO.println("Encrypted password: " + encryptedPassword);

        userMap.put(user.mobile(), encryptedPassword);

        // $2a$10$j9Pd1ERNSttFUHlDQKYj2uK52cMk4rCML9GL6FuRv/6YWkdN64/hO
        // $2a$10$tQnSKwOQFHoR2nDkNLIzgOWEakPGAoC/gd9c5dW.TtHTEmJi0PBXy

        return "login";
    }

    public record UserDto(String name, String mobile, String pin) {}
}
