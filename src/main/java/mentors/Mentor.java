package mentors;

import repository.WorkDB;
import scheduler.Role;
import users.User;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Mentor extends User implements WorkDB {
    private String mentorId;
    private String mentorName;
    private String mentorLogin;
    private String mentorPassword;
    private Role role;

    public Mentor(){
        System.out.println("Mentor()");
    }
    
    public Mentor(String name, String login, String password, Role role){
        this.mentorId = UUID.randomUUID().toString();
        this.mentorName = name;
        this.mentorLogin = login;
        this.mentorPassword = password;
        this.role = role;
        System.out.println("Mentor4");
    }

    public Mentor(String mentorId, String name, String login, String password, Role role){
        this.mentorId = mentorId;
        this.mentorName = name;
        this.mentorLogin = login;
        this.mentorPassword = password;
        this.role = role;
    }


    public String getMentorId() {
        return mentorId;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Object getRowDB(String mentorId) {
        Mentor mentor = null;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            String sql = "SELECT * FROM MENTOR WHERE MENTOR_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mentorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                String name = resultSet.getString("NAME");
                String password = resultSet.getString("PASSWORD");
                Role role = Role.valueOf(resultSet.getString("ROLE"));
                
                
                mentor = new Mentor(id, name, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentor;
    }

    public Object getRowLoginDB(String login) {
        Mentor mentor = null;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            String sql = "SELECT * FROM MENTORS WHERE LOGIN=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                String name = resultSet.getString("NAME");
                String password = resultSet.getString("PASSWORD");
                Role role = Role.valueOf(resultSet.getString("ROLE"));


                mentor = new Mentor(id, name, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentor;
    }
    @Override
    public void updateRowDB() {

    }

    @Override
    public void insertRowDB() {
        try (Connection h2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            String sql = "INSERT INTO MENTORS (MENTOR_ID, NAME, LOGIN, PASSWORD, ROLE) Values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = h2.prepareStatement(sql);
            preparedStatement.setString(1, this.mentorId);
            preparedStatement.setString(2, this.mentorName);
            preparedStatement.setString(3, this.mentorLogin);
            preparedStatement.setString(4, this.mentorPassword);
            preparedStatement.setString(5, this.role.toString());
            try {
                preparedStatement.executeUpdate();
                h2.close();
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRowDB(String id) {

    }

    @Override
    public List getDB() {
        List<Mentor> mentorList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            String sql = "SELECT * FROM MENTOR";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                String name = resultSet.getString("NAME");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                Role role = Role.valueOf(resultSet.getString("ROLE"));
                mentorList.add(new Mentor(id, name, login, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorList;
    }

    public static boolean searchTwoIndex (String one, String valueOne, String two, String valueTwo){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            String sql = "SELECT * FROM MENTOR WHERE " + one + " = " + valueOne + " AND " + two + " = " + valueTwo;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean searchTOneIndex (String one, String valueOne){
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            String sql = "SELECT * FROM MENTORS WHERE " + one + " = " + valueOne;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "mentorId='" + mentorId + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", mentorLogin='" + mentorLogin + '\'' +
                ", mentorPassword='" + mentorPassword + '\'' +
                ", role=" + role +
                '}';
    }

    public static void main(String[] args) {

//        System.out.println(searchTOneIndex("LOGIN", "user"));
        System.out.println(new Mentor().getRowLoginDB("2"));
    }
}
