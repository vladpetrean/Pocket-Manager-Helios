package Configuration;

import Model.Account;
import org.hibernate.Session;

import java.util.ArrayList;

public class DatabseOperation {

    public static ArrayList<Account> obtainAdministrator() {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<Account> administratorArrayList = (ArrayList<Account>) session.createCriteria(Account.class).list();

        HibernateConfiguration.getSessionFactory().close();
        return administratorArrayList;
    }
}
