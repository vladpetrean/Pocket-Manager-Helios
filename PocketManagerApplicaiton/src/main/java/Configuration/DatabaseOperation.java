package Configuration;

import Model.Account;
import Model.User;
import org.hibernate.Session;

import java.util.ArrayList;
/* Mirela*/
public class DatabaseOperation {

    public static ArrayList<Account> obtainAdministrator() {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<Account> administratorArrayList = (ArrayList<Account>) session.createCriteria(Account.class).list();

        HibernateConfiguration.getSessionFactory().close();
        return administratorArrayList;
    }


    public static void createAccount(Account account) {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        HibernateConfiguration.getSessionFactory().close();
    }

    public static ArrayList<User> obtainUser() {

        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<User> userArrayList = (ArrayList<User>) session.createCriteria(User.class).list();

        HibernateConfiguration.getSessionFactory().close();
        return userArrayList;
    }

    public static User getSingleUser(String username) {

        User user =  new User();
        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<User> userArrayList = (ArrayList<User>) session.createCriteria(User.class).list();
        for (User userIterate: userArrayList){
            if(userIterate.getUsername().equals(username)){
                HibernateConfiguration.getSessionFactory().close();
                return userIterate;
            }
        }
        return user;
    }

    public static ArrayList<Account> obtainUserAccount(int id) {
        ArrayList<Account> userAccountArrayList = new ArrayList<Account>();
        ArrayList<Account> accountArrayList = obtainAdministrator();

        for(Account account: accountArrayList){
            if(account.getUserId() == id){
                userAccountArrayList.add(account);
            }
        }
        return userAccountArrayList;
    }

    public static Account getSingleAccount(int account_id) {
        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<Account> accountArrayList = (ArrayList<Account>) session.createCriteria(Account.class).list();
        for (Account account: accountArrayList){
            if(account.getId() == account_id){
                HibernateConfiguration.getSessionFactory().close();
                return account;
            }
        }
        return null;
    }

    public static Account getSingleAccountByName(String account_name) {
        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        ArrayList<Account> accountArrayList = (ArrayList<Account>) session.createCriteria(Account.class).list();
        for (Account account: accountArrayList){
            if(account.getAccountName().equals(account_name)){
                HibernateConfiguration.getSessionFactory().close();
                return account;
            }
        }
        return null;
    }

    public static void deleteAccount(Account account) {
        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(account);
        session.getTransaction().commit();
        HibernateConfiguration.getSessionFactory().close();
    }

    public static void updateAccount(Account account) {
        HibernateConfiguration.createConnection();
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();
        HibernateConfiguration.getSessionFactory().close();
    }
}
