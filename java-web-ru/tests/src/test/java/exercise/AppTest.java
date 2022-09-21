package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateValidUser() {
        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Ivan")
                .field("lastName", "Ivanov")
                .field("email", "ivan@mail.ru")
                .field("password", "12345")
                .asString();
        assertThat(responsePost.getStatus()).isEqualTo(302);
        User actualUser = new QUser()
                .firstName.equalTo("Ivan")
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo("Ivan");
        assertThat(actualUser.getLastName()).isEqualTo("Ivanov");
        assertThat(actualUser.getEmail()).isEqualTo("ivan@mail.ru");
        assertThat(actualUser.getPassword()).isEqualTo("12345");
    }

    @Test
    void testCreateNotValidUser() {
        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "")
                .field("lastName", "Ivanov")
                .field("email", "i.ru")
                .field("password", "12")
                .asString();
        assertThat(responsePost.getStatus()).isEqualTo(422);
        User actualUser = new QUser()
                .firstName.equalTo("Ivan")
                .findOne();

        assertThat(actualUser).isNull();

        String content = responsePost.getBody();
        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Ivanov");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");
        assertThat(content).contains("Должно быть валидным email");
    }
    // END
}
