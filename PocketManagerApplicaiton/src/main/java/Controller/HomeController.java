package Controller;


import Configuration.DatabaseOperation;
import Model.Account;
import Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static Controller.AuxiliaryFunction.CheckPassword;
import static Controller.AuxiliaryFunction.setSessionUser;


@Controller
public class HomeController {
    @GetMapping({"/index"})
    public String sign_in_get(Model model, HttpServletRequest request) {

        return "index";
    }

    @PostMapping({"/index"})
    public RedirectView sign_in_post(Model model, HttpServletRequest request, HttpServletResponse response) {

        String password = request.getParameter("userPassword");
        String username = request.getParameter("userName");
        if(password != null && username != null){
            System.out.println(password);
            System.out.println(username);
            if(CheckPassword(password, username)){
                setSessionUser(response, "test");
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
        if (AuxiliaryFunction.getSessionUser(request)) {
            User user = DatabaseOperation.getSingleUser(username);
            ArrayList<Account> accountArrayList = DatabaseOperation.obtainUserAccount(user.getId());
            model.addAttribute("accountArrayList", accountArrayList);
            return "home";
        } else {
            return "index";
        }
    }


    @PostMapping({"/create_account/{username}"})
    public RedirectView create_account(Model model, HttpServletRequest request, @PathVariable String username) {
        if (AuxiliaryFunction.getSessionUser(request)) {
        User user =  DatabaseOperation.getSingleUser(username);
        model.addAttribute("user_id", user.getId());
            String type = request.getParameter("type");
            String accountName = request.getParameter("accountName");
            String currency = request.getParameter("currency");
            String amount = request.getParameter("amount");
        Account account = new Account();
            account.setAccountName(accountName);
            account.setType(type);
            account.setCurrency(currency);
            account.setUserId(user.getId());
            try {
                account.setAmount(Integer.parseInt(amount));
                DatabaseOperation.createAccount(account);

            } catch (Exception excetpion) {
                System.out.println(excetpion);
            }
            return new RedirectView("/home/" + username);
        } else {
            return new RedirectView("/index");
        }
    }

    @GetMapping({"/delete_account/{username}/{account_id}"})
    public RedirectView delete_account(Model model, HttpServletRequest request, @PathVariable int account_id, @PathVariable String username) {
        if (AuxiliaryFunction.getSessionUser(request)) {
            Account account = DatabaseOperation.getSingleAccount(account_id);
            if (account != null) {
                DatabaseOperation.deleteAccount(account);
            }

            return new RedirectView("/home/" + username);
        } else {
            return new RedirectView("/index");
        }
    }

    @GetMapping({"/edit_account/{username}/{account_id}"})
    public String edit_account(Model model, HttpServletRequest request, @PathVariable int account_id, @PathVariable String username) {
        if (AuxiliaryFunction.getSessionUser(request)) {
            Account account = DatabaseOperation.getSingleAccount(account_id);
            assert account != null;
            model.addAttribute("accountType", account.getType());
            model.addAttribute("accountName", account.getAccountName());
            model.addAttribute("accountCurrency", account.getCurrency());
            model.addAttribute("accountAmount", account.getAmount());
            model.addAttribute("username", username);
            model.addAttribute("accound_id", account_id);
            return "edit_account";
        } else {
            return "index";
        }
    }

    @PostMapping({"/edit_account/{username}/{account_id}"})
    public RedirectView edit_account_post(Model model, HttpServletRequest request, @PathVariable int account_id, @PathVariable String username) {
        if (AuxiliaryFunction.getSessionUser(request)) {
            Account account = DatabaseOperation.getSingleAccount(account_id);
            assert account != null;
            account.setCurrency(request.getParameter("currency"));
            account.setType(request.getParameter("type"));
            account.setAccountName(request.getParameter("accountName"));
            try {
                account.setAmount(Integer.parseInt(request.getParameter("amount")));
                DatabaseOperation.updateAccount(account);
                return new RedirectView("/home/" + username);

            } catch (Exception excetpion) {
                System.out.println(excetpion);
                return new RedirectView("/home/" + username);

            }
        } else {
            return new RedirectView("/index");
        }
    }
}