package exercise;

import java.beans.BeanProperty;
import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {
    @Bean
    public Daytime DefineTime() {
        LocalDateTime ldt = LocalDateTime.now();
        int hour = ldt.getHour();
        if(hour >= 6 && hour < 12) {
            return new Morning();
        }
        if(hour >= 12 && hour < 18) {
            return new Day();
        }
        if(hour >= 18 && hour < 23) {
            return new Evening();
        }
        return new Night();
    }
}
// END
