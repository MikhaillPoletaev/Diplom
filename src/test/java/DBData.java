import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DBData {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class BaseData {
        private String id;
        private String bank_id;
        private String created;
        private String status;
    }
}
