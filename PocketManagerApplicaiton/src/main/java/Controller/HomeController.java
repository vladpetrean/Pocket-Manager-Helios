package Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

import Model.User;
import Model.Account;

import Configuration.DatabaseOperation;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static Controller.AuxiliaryFunction.CheckPassword;
import static Controller.AuxiliaryFunction.setSessionUser;


@Controller
public class HomeController {
    @GetMapping({"/index"})
    public String sign_in_get(Model model, HttpServletRequest request) {

        return "index";
    }

    @PostMapping({"/index"})
    public RedirectView sign_in_post(Model model, HttpServletRequest request) {

        String password = request.getParameter("userPassword");
        String username = request.getParameter("userName");
        if(password != null && username != null){
            System.out.println(password);
            System.out.println(username);
            if(CheckPassword(password, username)){
                setSessionUser(request, username);
                return new RedirectView("home/"+username);
            }

                model.addAttribute("message", "Login failed");
                return new RedirectView("index");

        }
        else{
            model.addAttribute("message", "Login failed");
            return new RedirectView("index");
        }

    }

    @GetMapping({"/home/{username}"})
    public String display_home(Model model, HttpServletRequest request, @PathVariable String username){
        User user =  DatabaseOperation.getSingleUser(username);
        ArrayList<Account> accountArrayList = DatabaseOperation.obtainUserAccount(user.getId());
        model.addAttribute("accountArrayList", accountArrayList);
        return "home";
    }

    /*TO DO CREATE ACCOUNT*/
    @PostMapping({"/create_account/{username}"})
    public String create_account(Model model, HttpServletRequest request, @PathVariable String username){
        User user =  DatabaseOperation.getSingleUser(username);
        model.addAttribute("user_id", user.getId());
        Account account = new Account();

        DatabaseOperation.createAccount(account);
        return "home";
    }

}