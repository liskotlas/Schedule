package servlets;

import factories.MentorFactory;
import mentors.Mentor;
import scheduler.Role;
import users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "loginCheck", urlPatterns = "/loginCheck")
public class LoginCheck extends HttpServlet {
    String login;
    String password;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");
        password = req.getParameter("password");

        Mentor mentor = MentorFactory.getMentorFromLoginAndPassword(login, password);

        String userId = mentor.getMentorId();
        req.setAttribute("userId", userId);

        System.out.println(mentor.getRole().toString().equals("MENTOR"));

        if (mentor.getRole().toString().equals("MENTOR")){
            req.getRequestDispatcher("/mentor").forward(req, resp);
        }
//        ArrayList<Mentor> mentors = new Mentor().getDB();

//        if (login.equals("user") && password.equals("pass")) {
////            PrintWriter pw = resp.getWriter();
////            pw.println("Login OK");
//
//            req.getRequestDispatcher("/succesLogin").forward(req, resp);
////            PrintWriter pw = resp.getWriter();
////            pw.println("Логин и пароль правильные");
//        }
//        if (!login.equals("user") || !password.equals("pass")) {
//            req.getRequestDispatcher("/failLogin").forward(req, resp);
////            PrintWriter pw = resp.getWriter();
////            pw.println("Login or password failed");
//        }
    }
}


