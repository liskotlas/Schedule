package factories;

import mentors.Mentor;
import scheduler.Role;

import java.sql.*;

public class MentorFactory {

//    private Mentor factoryMentor = new Mentor();

    public static Mentor getMentorFromId(String mentorId){

        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            System.out.println("Фабрика");
            String sql = "SELECT * FROM MENTOR WHERE MENTOR_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mentorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                String name = resultSet.getString("NAME");
                String pass = resultSet.getString("PASSWORD");
                Role role = Role.valueOf(resultSet.getString("ROLE"));

                return new Mentor(id, name, pass, role);
            }
        } catch (SQLException e) {
            new RuntimeException().getMessage();
        }
        return null;
    }

    public static Mentor getMentorFromLoginAndPassword(String login, String password){
        System.out.println("Фабрика");
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            String sql = "SELECT * FROM MENTORS WHERE LOGIN=? AND PASSWORD=?";
            System.out.println(sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("1");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                String name = resultSet.getString("NAME");
                String log = resultSet.getString("LOGIN");
                String pass = resultSet.getString("PASSWORD");
                Role role = Role.valueOf(resultSet.getString("ROLE"));
                Mentor mentor =  new Mentor(id, name, log, pass, role);
                System.out.println(mentor);
                return mentor;
            }
        } catch (SQLException e) {
            new RuntimeException("Не могу получить Connection").getMessage();
        }
        System.out.println("Не нашел значение в базе");
        return null;


    }

    public static void main(String[] args) {
        System.out.println(getMentorFromLoginAndPassword("3", "3").toString());
    }


}
