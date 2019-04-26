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

import static Controller.AuxiliaryFunction.*;

/* Florin*/
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
            User user = DatabaseOperation.getSingleUser(username);
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
                if (verifyName(account, user.getId())) {
                    DatabaseOperation.createAccount(account);
                } else {
                    return new RedirectView("/home/" + username);
                }
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

    @PostMapping({"/transfer_ammount/{username}"})
    public RedirectView transfer_ammount(Model model, HttpServletRequest request, @PathVariable String username) {
        if (AuxiliaryFunction.getSessionUser(request)) {

            String account_name_source = request.getParameter("account_source_name");
            String account_name_destination = request.getParameter("account_destination_name");

            Account account_source = DatabaseOperation.getSingleAccountByName(account_name_source);
            Account account_destination = DatabaseOperation.getSingleAccountByName(account_name_destination);
            try {
                int ammout = Integer.parseInt(request.getParameter("amount_transfer"));
                assert account_source != null;
                assert account_destination != null;
                if(account_source.getAmount() < ammout){
                    System.out.println("Not enough ammount");
                    return new RedirectView("/home/" + username);
                }
                int new_ammount_destination = account_destination.getAmount() + ammout;
                int new_ammount_source = account_destination.getAmount() - ammout;
                System.out.println(new_ammount_source);
                System.out.println(new_ammount_destination);

                account_destination.setAmount(new_ammount_destination);
                account_source.setAmount(new_ammount_source);
                DatabaseOperation.updateAccount(account_destination);
                DatabaseOperation.updateAccount(account_source);
                return new RedirectView("/home/" + username);
            } catch (Exception e) {
                System.out.println(e);
                return new RedirectView("/home/" + username);

            }

        } else {
            return new RedirectView("/index");
        }
    }
}