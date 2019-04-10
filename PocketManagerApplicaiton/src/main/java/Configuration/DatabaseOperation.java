package Configuration;

import Model.Account;
import Model.User;
import org.hibernate.Session;

import java.util.ArrayList;

public class DatabaseOperation {

    public static ArrayList<Account> obtainAdministrator() {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<Account> administratorArrayList = (ArrayList<Account>) session.createCriteria(Account.class).list();

        HibernateConfiguration.getSessionFactory().close();
        return administratorArrayList;
    }

    public static ArrayList<User> obtainUser() {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<User> userArrayList = (ArrayList<User>) session.createCriteria(User.class).list();

        HibernateConfiguration.getSessionFactory().close();
        return userArrayList;
    }

}
