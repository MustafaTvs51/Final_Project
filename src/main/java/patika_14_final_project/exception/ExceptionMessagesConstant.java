package patika_14_final_project.exception;

public class ExceptionMessagesConstant {


    private ExceptionMessagesConstant(){
    }

    public static final String CUSTOMER_EMAIL_DOES_NOT_EXISTS = "Girilen email ile bir müşteri bulunamamaktadir." ;
    public static final String CUSTOMER_PASSWORD_DOES_NOT_MATCH ="Girilen sifre ve ya email bilgisi yanlıs ! ";
    public static final String CUSTOMER_EMAIL_ALREADY_EXISTS = "Musteri email'i zaten zaten kayitli.";

    public static final String USER_EMAIL_DOES_NOT_EXISTS = "Girilen email ile bir kullanici bulunamamaktadir." ;
    public static final String USER_PASSWORD_DOES_NOT_MATCH ="Girilen sifre ve ya kullanıcı bilgisi yanlıs ! ";
    public static final String USER_EMAIL_ALREADY_EXISTS = "Girilen email  zaten zaten kayitli.";

    public static final String USER_IS_NOT_ADMIN = "Giriş yapan kullanıcı ADMIN rolüne sahip değildir.";
    public static final String USER_IS_NOT_ACTIVE = "Kullanıcı aktif değil ya da bulunamadı ! ";

    public static final String CATEGORY_NOT_FOUND = "Kategori bulunamadı";



    public static final String PRODUCT_STOCK_IS_NOT_VALID = "İstenilen ürünün yeterli stok adeti bulunmamaktadır !  ";

    public static final String CART_ITEMS_IS_EMPTY = "Sepetiniz boş";

}
