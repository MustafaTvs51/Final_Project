package patika_14_final_project.dao.Constants;

public class SqlScriptConstants {

    private SqlScriptConstants() {
    }

    public static final String CUSTOMER_SAVE = """
            INSERT INTO customer (name,email,password) VALUES (?,?,?)
            """;

    public static final String CUSTOMER_FIND_BY_ID = """
            SELECT * FROM customer WHERE id = ?
            
            """;

    public static final String CUSTOMER_FIND_ALL = """
            SELECT * FROM customer
            """;

    public static final String CUSTOMER_EXIST_BY_EMAIL = """
            SELECT * FROM customer WHERE email = ? 
            """;


    public static final String ORDER_SAVE = """
            INSERT INTO \"order\" (customer_id,order_date,total_amount)
            VALUES(?,?,?,?,?)
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (order_id,payment_method,amount)
            VALUES (?,?,?)
            """;

    public static final String PRODUCT_SEARCH_BY_NAME = """
            SELECT * FROM product WHERE name LIKE ?
            """;
}



