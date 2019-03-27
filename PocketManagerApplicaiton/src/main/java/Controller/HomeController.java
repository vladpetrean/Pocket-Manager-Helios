package Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;

import Model.Account;
import Configuration.DatabseOperation;


@Controller
public class HomeController {
    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        ArrayList<Account> accountArrayList = DatabseOperation.obtainAdministrator();
        for(Account account: accountArrayList){
            System.out.println(account.getAccountName());
        }
        name = "Test printare variabila template";
        model.addAttribute("name", name);
        return "hello";
    }
}