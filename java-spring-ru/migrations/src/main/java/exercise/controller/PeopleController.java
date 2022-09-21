package exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String listPersons() throws Exception {
        String query = "SELECT * FROM person";
        List<Map<String, Object>> result = jdbc.queryForList(query);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String showPerson(@PathVariable("id") String id) throws Exception {
        String query = "SELECT * FROM person WHERE id=?";
        Map<String, Object> result = jdbc.queryForMap(query,id);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
    // END
}
