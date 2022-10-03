package exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    Meal meal;

    @Autowired
    MyApplicationConfig myApplicationConfig;

    @GetMapping("/daytime")
    public String getEnjoy() {
        String str = myApplicationConfig.DefineTime().getName();
        return "It is " + str +" now. Enjoy your " + meal.getMealForDaytime(str);
    }
}
// END
