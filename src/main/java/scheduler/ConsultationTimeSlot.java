package scheduler;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultationTimeSlot {
    String slot_id;
    LocalDate dateSlot;
    LocalTime timeSlotStart;
    LocalTime timeSlotEnd;
    String mentor_id;
    String user_id;

    public ConsultationTimeSlot(){
    }
    
    public ConsultationTimeSlot (LocalDate dateSlot, LocalTime timeSlotStart, LocalTime timeSlotEnd, String mentor_id){
        this.dateSlot = dateSlot;
        this.timeSlotStart = timeSlotStart;
        this.timeSlotEnd = timeSlotEnd;
        this.mentor_id = mentor_id;
        this.user_id = "";
    }
    
    public void setUser (String user_id){
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ConsultationTimeSlot{" +
                "slot_id='" + slot_id + '\'' +
                ", dateSlot=" + dateSlot +
                ", timeSlotStart=" + timeSlotStart +
                ", timeSlotEnd=" + timeSlotEnd +
                ", mentor_id='" + mentor_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
