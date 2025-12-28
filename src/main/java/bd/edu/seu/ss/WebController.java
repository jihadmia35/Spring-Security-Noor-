package bd.edu.seu.ss;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

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


}
