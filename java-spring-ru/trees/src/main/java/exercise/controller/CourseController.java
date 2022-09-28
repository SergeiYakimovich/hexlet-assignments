package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Course> getPreviousCourses(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        List<Course> result = new ArrayList<>();
        if (course.getPath() == null) {
            return result;
        }
        Arrays.stream(course.getPath().split("\\."))
                .forEach(n -> {
                            if (n != null && !n.isEmpty()) {
                                result.add(courseRepository.findById(Long.parseLong(n)));
                            }
                        }
                );
        return result;
    }
    // END

}
