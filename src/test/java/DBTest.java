import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

public class DBTest {

    @BeforeEach
    @SneakyThrows
    void setUpDB() {
        var runner = new QueryRunner();
        var dataSQL = "INSERT INTO credit_request_entity(id, bank_id, status) VALUES (?, ?, ?);";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/app", "app", "9mREsvXDs9Gk89Ef"
                );
        ) {
            runner.update(conn, dataSQL, "1", "4444 4444 4444 4441", "approved");
            runner.update(conn, dataSQL, "2", "4444 4444 4444 4442", "declined");
        }
    }

    @Test
    @SneakyThrows
    void baseTest() {
        var dataSQL = "SELECT * FROM credit_request_entity;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/app", "app", "9mREsvXDs9Gk89Ef"
                );
        ) {
            var first = runner.query(conn, dataSQL, new BeanHandler<>(DBData.class));
            System.out.println(first);
        }


    }
}
