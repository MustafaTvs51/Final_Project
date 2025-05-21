package patika_14_final_project.dao.Constants;

public class SqlScriptConstants {


    public static final String CART_FIND_BY_CUSTOMER_ID = """
           SELECT *
           FROM cart
           WHERE customer_id = ?
           """;
    public static final String CART_ITEM_FIND_BY_CUSTOMER_ID = """
            SELECT
                    ci.id       as cart_item_id,
                    ci.quantity as quantity,
                    p.id        as product_id,
                    p.name      as product_name,
                    p.price     as price
            FROM cart_items ci
                        JOIN cart c on c.id = ci.cart_id
                        JOIN public.product p ON p.id = ci.product_id
            WHERE c.customer_id = ?
            """;
    public static final String CART_ITEM_SAVE = """
            INSERT INTO cart_items (cart_id, product_id, quantity)
            VALUES(?,?,?)
        """;
    public static final String CART_FIND_ALL_BY_CUSTOMER_ID = """
        SELECT
            p.name as product_name,
            p.price as price,
            ci.quantity as quantity
        FROM cart c
                    JOIN public.product p ON p.id = c.product_id
        WHERE customer_id = ?
        ORDER BY c.createddate DESC
        """;
    public static final String CART_SAVE = """

            """;
    public static final String CART_ITEM_DELETE = """
            DELETE FROM cart_items WHERE cart_id = ?
            """;
    public static final String ORDER_ITEMS_SAVE = """
            INSERT INTO order_item (order_id, product_id, quantity, price)
            VALUES(?,?,?,?)
            """;


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
            VALUES(?,?,?)
            RETURNING id
            """;

    public static final String PAYMENT_SAVE = """
            INSERT INTO payment (order_id,payment_method,amount)
            VALUES (?,?,?)
            """;

    public static final String PRODUCT_SEARCH_BY_NAME = """
            SELECT p.id         as id,
                   p.name       as name,
                   p.price      as price,
                   p.stock      as stock,
                   c.id         as category_id,
                   c.name       as category_name
            FROM product p
                     LEFT JOIN public.category c ON c.id = p.category_id
            WHERE p.name LIKE ?
            """;

    public static final String PRODUCT_SAVE = """
            INSERT INTO product (name,price,stock,category_id,created_by,updated_by)
            VALUES(?,?,?,?,?,?)
            """;
    public static final String PRODUCT_FIND_ALL = """
            SELECT p.id as id,
                p.name as name,
                p.price as price,
                p.stock as stock,
                c.id as category_id,
                c.name as category_name
            FROM product p,
                 category c
            WHERE p.category_id = c.id
            ORDER BY p.id asc
            LIMIT ? OFFSET ? ;
            """;
    public static final String PRODUCT_FIND_BY_NAME = """
            SELECT * FROM product WHERE name = ?
            """;

    public static final String PRODUCT_DELETE = """
            DELETE FROM product WHERE id = ?
            """;

    public static final String PRODUCT_TOTAL_PAGE_COUNT = """
            SELECT COUNT(*) FROM product
            """;
    public static final String PRODUCT_FIND_BY_CATEGORY_NAME = """
            SELECT p.id as id,
                p.name as name,
                p.price as price,
                p.stock as stock,
                c.id as category_id,
                c.name as category_name
            FROM product p
                        JOIN category c ON c.id = p.category_id
            WHERE c.name = ?
            """;

    public static final String USER_SAVE = """
            INSERT INTO users (username , password , role , active )
            VALUES (?,?,?,?)
            """;

    public static final String USER_FIND_BY_NAME = """
            SELECT * FROM users WHERE username = ? 
            """;

    public static final String CATEGORY_SAVE = """
            INSERT INTO category  (name, created_by, updated_by)
            VALUES (?,?,?)
            """;

    public static final String CATEGORY_DELETE = """
            DELETE FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_BY_ID = """
            SELECT * FROM category WHERE id = ?
            """;

    public static final String CATEGORY_FIND_ALL = """
            SELECT * FROM category
            """;


}



