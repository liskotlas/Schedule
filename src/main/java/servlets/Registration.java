package servlets;

import factories.MentorFactory;
import mentors.Mentor;
import org.h2.engine.Session;
import scheduler.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Role role = Role.valueOf(req.getParameter("role"));

            Mentor mentor = new Mentor (name, login, password, role);
        System.out.println(mentor.toString());
            mentor.insertRowDB();
//            Проверка создания записи в БД
            if (MentorFactory.getMentorFromLoginAndPassword(login, password) != null){
                System.out.println("Есть в базе");
            req.setAttribute("mentorId", "12121212");

            getServletContext().getRequestDispatcher("/123.jsp").forward(req, resp);
        }else {
                resp.sendRedirect("registration.html");
            }
    }
}
