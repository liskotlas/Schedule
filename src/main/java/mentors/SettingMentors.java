package mentors;

import java.time.Duration;

public class SettingMentors {

    Duration consultationDuration;

    public SettingMentors(int consultationDuration){
        this.consultationDuration = Duration.ofMinutes(consultationDuration);
    }

    public static Duration getConsultationDuration() {
        return Duration.ofMinutes(15);
    }
}
