package mentors;

import org.h2.jdbc.JdbcSQLException;
import repository.WorkDB;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class MentorSchedule implements WorkDB {


    
    String mentorId = "";
    LocalTime startMonday;
    LocalTime endMonday;
    LocalTime startTuesday;
    LocalTime endTuesday;
    LocalTime startWednesday;
    LocalTime endWednesday;
    LocalTime startThursday;
    LocalTime endThursday;
    LocalTime startFriday;
    LocalTime endFriday;
    LocalTime startSaturday;
    LocalTime endSaturday;
    LocalTime startSunday;
    LocalTime endSunday;
    
    public MentorSchedule(String mentorId, LocalTime startMonday, LocalTime endMonday, LocalTime startTuesday, LocalTime endTuesday, LocalTime startWednesday, LocalTime endWednesday, LocalTime startThursday, LocalTime endThursday, LocalTime startFriday, LocalTime endFriday, LocalTime startSaturday, LocalTime endSaturday, LocalTime startSunday, LocalTime endSunday){
        this.mentorId = mentorId;
        this.startMonday = startMonday;
        this.endMonday = endMonday;
        this.startTuesday = startTuesday;
        this.endTuesday = endTuesday;
        this.startWednesday = startWednesday;
        this.endWednesday = endWednesday;
        this.startThursday = startThursday;
        this.endThursday = endThursday;
        this.startFriday = startFriday;
        this.endFriday = endFriday;
        this.startSaturday = startSaturday;
        this.endSaturday = endSaturday;
        this.startSunday = startSunday;
        this.endSunday = endSunday;
    }

    public LocalTime getWorkTimeStart(String dayOfWeek) {
        switch (dayOfWeek) {
            case "Mondey":
                return this.startMonday;
            case "Tuesday":
                return this.startTuesday;
            case "Wednesday":
                return this.startWednesday;
            case "Thursday":
                return this.startThursday;
            case "Friday":
                return this.startFriday;
            case "Saturday":
                return this.startSaturday;
            case "Sunday":
                return this.startSunday;
        }
        return null;
    }

    public LocalTime getWorkTimeEnd(String dayOfWeek) {
        switch (dayOfWeek){
        case "Mondey":
        return this.endMonday;
        case "Tuesday":
        return this.endTuesday;
        case "Wednesday":
        return this.endWednesday;
        case "Thursday":
        return this.endThursday;
        case "Friday":
        return this.endFriday;
        case "Saturday":
        return this.endSaturday;
        case "Sunday":
        return this.endSunday;
    }
        return null;
    }

    public String getMentorId() {
        return mentorId;
    }

    @Override
    public MentorSchedule getRowDB(String mentorId) {
        MentorSchedule mentorSchedule = null;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/scheduler", "sa", "")) {
            String sql = "SELECT * FROM MENTOR_SCHEDULE WHERE MENTOR_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mentorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                LocalTime startMonday = resultSet.getTime("START_MONDAY").toLocalTime();
                LocalTime endMonday = resultSet.getTime("END_MONDAY").toLocalTime();
                LocalTime startTuesday = resultSet.getTime("START_TUESDAY").toLocalTime();
                LocalTime endTuesday = resultSet.getTime("END_TUESDAY").toLocalTime();
                LocalTime startWednesday = resultSet.getTime("START_WEDNESDAY").toLocalTime();
                LocalTime endWednesday = resultSet.getTime("END_WEDNESDAY").toLocalTime();
                LocalTime startThursday = resultSet.getTime("START_THURSDAY").toLocalTime();
                LocalTime endThursday = resultSet.getTime("END_THURSDAY").toLocalTime();
                LocalTime startFriday = resultSet.getTime("START_FRIDAY").toLocalTime();
                LocalTime endFriday = resultSet.getTime("END_FRIDAY").toLocalTime();
                LocalTime startSaturday = resultSet.getTime("START_SATURDAY").toLocalTime();
                LocalTime endSaturday = resultSet.getTime("END_SATURDAY").toLocalTime();
                LocalTime startSunday = resultSet.getTime("START_SUNDAY").toLocalTime();
                LocalTime endSunday = resultSet.getTime("END_SUNDAY").toLocalTime();
                mentorSchedule = new MentorSchedule(id, startMonday, endMonday, startTuesday, endTuesday, startWednesday, endWednesday, startThursday, endThursday, startFriday, endFriday, startSaturday, endSaturday, startSunday, endSunday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorSchedule;
    }

    @Override
    public void updateRowDB() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "")) {
            String sql = "UPDATE MENTOR_SCHEDULE SET START_MONDAY = ?, END_MONDEY = ?, START_TUESDAY = ?, END_TUESDAY = ?, START_WEDNESDAY = ?, END_WEDNESDAY = ?, START_THURSDAY = ?, END_THURSDAY = ?, START_FRIDAY = ?, END_FRIDAY = ?, START_SATURDAY = ?, END_SATUR_DAY = ?, START_SUNDAY = ?, END_SUNDAY = ? WHERE MENTOR_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(15, this.mentorId);
            preparedStatement.setTime(1, Time.valueOf(this.startMonday));
            preparedStatement.setTime(2, Time.valueOf(this.endMonday));
            preparedStatement.setTime(3, Time.valueOf(this.startTuesday));
            preparedStatement.setTime(4, Time.valueOf(this.endTuesday));
            preparedStatement.setTime(5, Time.valueOf(this.startWednesday));
            preparedStatement.setTime(6, Time.valueOf(this.endWednesday));
            preparedStatement.setTime(7, Time.valueOf(this.startThursday));
            preparedStatement.setTime(8, Time.valueOf(this.endThursday));
            preparedStatement.setTime(9, Time.valueOf(this.startFriday));
            preparedStatement.setTime(10, Time.valueOf(this.endFriday));
            preparedStatement.setTime(11, Time.valueOf(this.startSaturday));
            preparedStatement.setTime(12, Time.valueOf(this.endSaturday));
            preparedStatement.setTime(13, Time.valueOf(this.startSunday));
            preparedStatement.setTime(14, Time.valueOf(this.endSunday));
            try{
                preparedStatement.executeUpdate();
            }catch (JdbcSQLException e){
                System.out.println("Запись не найдена");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insertRowDB() {

        try (Connection h2 = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            String sql = "INSERT INTO MENTOR_SCHEDULE (MENTOR_ID, START_MONDAY, END_MONDEY, START_TUESDAY, END_TUESDAY, START_WEDNESDAY, END_WEDNESDAY, START_THURSDAY, END_THURSDAY, START_FRIDAY, END_FRIDAY, START_SATURDAY, END_SATUR_DAY, START_SUNDAY, END_SUNDAY) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = h2.prepareStatement(sql);
            preparedStatement.setString(1, this.mentorId);
            preparedStatement.setTime(2, Time.valueOf(this.startMonday));
            preparedStatement.setTime(3, Time.valueOf(this.endMonday));
            preparedStatement.setTime(4, Time.valueOf(this.startTuesday));
            preparedStatement.setTime(5, Time.valueOf(this.endTuesday));
            preparedStatement.setTime(6, Time.valueOf(this.startWednesday));
            preparedStatement.setTime(7, Time.valueOf(this.endWednesday));
            preparedStatement.setTime(8, Time.valueOf(this.startThursday));
            preparedStatement.setTime(9, Time.valueOf(this.endThursday));
            preparedStatement.setTime(10, Time.valueOf(this.startFriday));
            preparedStatement.setTime(11, Time.valueOf(this.endFriday));
            preparedStatement.setTime(12, Time.valueOf(this.startSaturday));
            preparedStatement.setTime(13, Time.valueOf(this.endSaturday));
            preparedStatement.setTime(14, Time.valueOf(this.startSunday));
            preparedStatement.setTime(15, Time.valueOf(this.endSunday));
            try {
                preparedStatement.executeUpdate();
            }catch (Exception e){
                System.out.println("Запись не уникальна");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRowDB(String mentorId) {
        String sql = "DELETE FROM MENTOR_SCHEDULE WHERE MENTOR_ID=?";
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mentorId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MentorSchedule> getDB() {
        List<MentorSchedule> mentorScheduleList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/schedule", "sa", "")) {
            String sql = "SELECT * FROM MENTOR_SCHEDULE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("MENTOR_ID");
                LocalTime startMonday = resultSet.getTime("START_MONDAY").toLocalTime();
                LocalTime endMonday = resultSet.getTime("END_MONDAY").toLocalTime();
                LocalTime startTuesday = resultSet.getTime("START_TUESDAY").toLocalTime();
                LocalTime endTuesday = resultSet.getTime("END_TUESDAY").toLocalTime();
                LocalTime startWednesday = resultSet.getTime("START_WEDNESDAY").toLocalTime();
                LocalTime endWednesday = resultSet.getTime("END_WEDNESDAY").toLocalTime();
                LocalTime startThursday = resultSet.getTime("START_THURSDAY").toLocalTime();
                LocalTime endThursday = resultSet.getTime("END_THURSDAY").toLocalTime();
                LocalTime startFriday = resultSet.getTime("START_FRIDAY").toLocalTime();
                LocalTime endFriday = resultSet.getTime("END_FRIDAY").toLocalTime();
                LocalTime startSaturday = resultSet.getTime("START_SATURDAY").toLocalTime();
                LocalTime endSaturday = resultSet.getTime("END_SATURDAY").toLocalTime();
                LocalTime startSunday = resultSet.getTime("START_SUNDAY").toLocalTime();
                LocalTime endSunday = resultSet.getTime("END_SUNDAY").toLocalTime();
                mentorScheduleList.add(new MentorSchedule(id, startMonday, endMonday, startTuesday, endTuesday, startWednesday, endWednesday, startThursday, endThursday, startFriday, endFriday, startSaturday, endSaturday, startSunday, endSunday));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentorScheduleList;
    }
}
