import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ApiTests {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://testqa.kai.ru/";
    }
    /*
     * Тест из видео
     */
    @Test
    void getBooks() {
        given().when().get("/api/book/books.php")
                .then().log().all().assertThat().statusCode(200);
    }
    // Запрос по несуществующему URL
    @Test
    void getBook() {
        given().when().get("/api/book.php")
                .then().log().all().assertThat().statusCode(404);
    }

    // Проверка того, что существует книга с id = 2
    @Test
    void getFirstBook() {
        given()
                .when().get("/api/book/single.php/?id=2")
                .then().log().all().assertThat().statusCode(200);
    }

    // Проверка того, что не существует книги с id = 4000
    // в ответе приходит JSON c сообщением "message": "No Data"
    @Test
    void getNonExistsBook() {
        given()
                .when().get("/api/book/single.php/?id=4000")
                .then().log().all().assertThat().statusCode(200);
    }

    // Создание новой книги с помощью запроса с параметрами
    @Test
    void insertNewAuthor() {
        given()
                .when().post("api/book/insert.php?title=War and Piece&pages=2000&year=1890&author_id=10")
                .then().log().all().assertThat().statusCode(200);
    }

    // Создание новой книги с помощью создания сущности
    @Test
    void insertNewAuthorWithPOJO() {
        BookInsert bookInsert = new BookInsert("Dead Souls", 221, 2022, 11);
        given().contentType(ContentType.JSON)
                .body(bookInsert).when().post("/api/book/insert.php").then()
                .log().all().assertThat().statusCode(200);
    }

    // проверка обновления книги
    // newBookData - Json с обновленными данными
    @Test
    void updateBookById() {
        String newBookData = "{\n" +
                "  \"id\": 0,\n" +
                "  \"title\": \"Hello world\",\n" +
                "  \"pages\": \"500\",\n" +
                "  \"year\": \"2001\",\n" +
                "  \"author_id\": \"5\"\n" +
                "}";
        given().body(newBookData).contentType(ContentType.JSON)
                .when().put("/api/book/update.php")
                .then().log().all().assertThat().statusCode(200);
    }

    // обновление книги с использованием сущности
    @Test
    void updateBookByIdWithPojo() {
        BookInsert newBook = new BookInsert("Hello World", 222, 2002, 12);
        given().body(newBook).contentType(ContentType.JSON)
                .when().put("/api/book/update.php")
                .then().log().all().assertThat().statusCode(200);
    }

    // удаление книги по ее id
    @Test
    void deleteBookById() {
        String newBookData = "{\n" +
                "  \"id\": 0\n" +
                "}";
        given().body(newBookData).contentType(ContentType.JSON)
                .when().delete("/api/book/destroy.php")
                .then().log().all().assertThat().statusCode(200);
    }

    // удаление книги по ее id через параметры, без body
    // ответ статус 200, с пустым телом
    @Test
    void deleteBookByIdWithParams() {

        given()
                .when().delete("https://testqa.kai.ru/api/book/destroy.php?id=3")
                .then().log().all().assertThat().statusCode(200);
    }

    // тестирование метода head, не указанного в сваггере
    // метод head аналогичен методу get, но он без body
    @Test
    void headBook() {
        given()
                .when().head("/api/book/books.php")
                .then().log().all().assertThat().statusCode(200);
    }

}
