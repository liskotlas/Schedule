package scheduler;

import mentors.Day;
import mentors.MentorSchedule;
import mentors.SettingMentors;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreateConsultationTimeSlot {
    
    public static List<ConsultationTimeSlot> createTimeSlotList (LocalDate date, List<MentorSchedule> mentorScheduleList){

        //сделать проверку в базе на наличие уже созданных слотов на эту дату, если их нет то создаем..
        List<ConsultationTimeSlot> consultationTimeSlotList = new ArrayList<>();
        String dayOfWeek = date.getDayOfWeek().toString();
        Duration duration = SettingMentors.getConsultationDuration();
        
//        Locale localeRu = new Locale("ru", "RU");
        for(MentorSchedule mentorSchedule : mentorScheduleList){
            LocalTime start = mentorSchedule.getWorkTimeStart(dayOfWeek);
            LocalTime end = mentorSchedule.getWorkTimeEnd(dayOfWeek);
            String mentorId = mentorSchedule.getMentorId();
            for(LocalTime s = start; s.isBefore(end); s = s.plus(duration)){
                consultationTimeSlotList.add(new ConsultationTimeSlot(date, s, s.plus(duration), mentorId));
            }
        }
            return consultationTimeSlotList;
        }
    
}
