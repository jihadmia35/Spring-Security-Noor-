package bd.edu.seu.ss;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String dashboard(){

        return "dashboard";
    }


    @GetMapping("/about-us")
    public String aboutus(){

        return "about";
    }
}
