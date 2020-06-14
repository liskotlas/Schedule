package servlets.mentor;

import mentors.MentorSchedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateSchedule", urlPatterns = "/mentorScheduleCreate")
public class CreateSchedule extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Проверка на существование действующего расписания ментора

//        Создание нового распиcания


//        new MentorSchedule(req.getParameter(""))
    }
}
