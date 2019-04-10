package Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

import Model.User;
import Configuration.DatabaseOperation;

import javax.servlet.http.HttpServletRequest;

import static Controller.AuxiliaryFunction.CheckPassword;
import static Controller.AuxiliaryFunction.setSessionUser;


@Controller
public class HomeController {
    @GetMapping({"/index"})
    public String sign_in_get(Model model, HttpServletRequest request) {
        System.out.println("ceva");
        return "index";
    }

    @PostMapping({"/index"})
    public String sign_in_post(Model model, HttpServletRequest request) {

        String password = request.getParameter("userPassword");
        String username = request.getParameter("userName");
        if(password != null && username != null){
            System.out.println(password);
            System.out.println(username);
            if(CheckPassword(password, username)){
                setSessionUser(request, username);
                return "home";
            }

                model.addAttribute("message", "Login failed");
                return "index";

        }
        else{
            model.addAttribute("message", "Login failed");
            return "index";
        }


    }


}