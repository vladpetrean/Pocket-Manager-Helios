package com;

import dao.UserDao;
import entities.User;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/user"})
public class LoginServlet extends HttpServlet {

    UserDao userDao;


    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(req, resp);
        userDao =new UserDao(new Configuration().configure().buildSessionFactory());
        RequestDispatcher requestDispatcher=null;
        String userName= request.getParameter("username");
        String password= request.getParameter("password");

        User dbUser= userDao.findByUsername(userName);
        HttpSession session = request.getSession(true);
        if (dbUser == null) {

//            response.sendRedirect("login.jsp");
            requestDispatcher = request.getRequestDispatcher("login.jsp");
        } else if(userName!=null && password!=null && !userName.equals("") && !password.equals("")){
            if (dbUser.getPassword().equals(password)){
                if(dbUser.getRole()==1){
                    request.setAttribute("user","ADMIN");
//                    response.sendRedirect("admin.jsp");
                    requestDispatcher = request.getRequestDispatcher("admin.jsp");
                }else{
//                    response.sendRedirect("user.jsp");
                    request.setAttribute("user","USER");
                    requestDispatcher = request.getRequestDispatcher("user.jsp");
                }
            }else{
                requestDispatcher = request.getRequestDispatcher("login.jsp");
            }

        }else {
            requestDispatcher = request.getRequestDispatcher("login.jsp");
        }

        requestDispatcher.forward(request,response);
    }
}
