package Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import Model.Account;
import Configuration.DatabseOperation;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HomeController {
    @GetMapping({"/index"})
    public String sign_in_get(Model model, HttpServletRequest request) {
        return "index";
    }

    @PostMapping({"/index"})
    public String sign_in_post(Model model, HttpServletRequest request) {

        String password = request.getParameter("userPassword");
        String username = request.getParameter("userName");

        if(password != null && username != null){
            System.out.println(password);
            System.out.println(username);
            if(check_sign_in(password, username)){
                FlorinMethod();
                return "hello";
            }
            else{
                model.addAttribute("message", "Login failed");
                return "hello";
            }
        }
        else{
            model.addAttribute("message", "Login failed");
            return "hello";
        }
    }


}