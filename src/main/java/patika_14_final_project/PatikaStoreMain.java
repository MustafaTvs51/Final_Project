package patika_14_final_project;

import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.*;
import patika_14_final_project.model.enums.PaymentMethod;
import patika_14_final_project.model.enums.Role;
import patika_14_final_project.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class PatikaStoreMain {

    private static User LOGINNED_USER;

    private static Customer LOGINNED_CUSTOMER;

    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();

    private static final CategoryService categoryService = new CategoryService();

    private static final ProductService productService = new ProductService();

    private static final CartService cartService = new CartService();

    private static final CartItemService cartItemService = new CartItemService();

    private static final OrderService orderService = new OrderService();

    public static void main(String[] args) {

        while (true) {

            getMainMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("Cıkıs Yapılıyor ... ");
                        return;
                    default:
                        System.out.println("Gecersiz Secim.");

                }
            } catch (PatikaStoreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void getCustomerMenu() throws PatikaStoreException {
        while (true) {
            System.out.println("===== MÜŞTERİ GİRİŞ PANELİ =====");
            System.out.println("1 - Müşteri Kayıt Ol");
            System.out.println("2 - Müşteri Giriş Yap");
            System.out.println("0 - Geri Dön ");
            System.out.print("Seçim Yapınız :");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerCustomer();
                    break;
                case "2":
                    loginCustomer();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Gecersiz Secim.");
            }
        }

    }

    private static void getUserMenu() throws PatikaStoreException {
        while (true) {
            System.out.println("===== KULLANICI GİRİŞ PANELİ =====");
            System.out.println("1 - Kullanıcı Kayıt Ol");
            System.out.println("2 - Kullanıcı Giriş Yap");
            System.out.println("0 - Geri Dön ");
            System.out.print("Seçim Yapınız :");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Gecersiz Secim.");
            }

        }
    }

    private static void loginUser() throws PatikaStoreException {
        System.out.print("Kullanıcı Adı :");
        String userName = scanner.nextLine();
        System.out.print("Şifre Giriniz : ");
        String password = scanner.nextLine();

        User loginedUser = userService.login(userName, password);

        if (loginedUser != null && loginedUser.getActive()) {

            LOGINNED_USER = loginedUser;

            getLoginedUserMenu();

        } else {
            throw new RuntimeException(ExceptionMessagesConstant.USER_IS_NOT_ACTIVE);
        }
    }

    private static void getLoginedUserMenu() throws PatikaStoreException {
        while (true) {
            System.out.println("===== LOGİN OLAN KULLANICI MENÜSÜ =====");
            System.out.println("1 - Kategori Oluştur");
            System.out.println("2 - Kategori Listele");
            System.out.println("3 - Kategori Sil");
            System.out.println("4 - Ürün Oluştur");
            System.out.println("5 - Ürün Listele");
            System.out.println("6 - Ürün Sil");
            System.out.println("7 - Ürün Arama");
            System.out.println("8 - Ürün filtreleme (Kategori Bazlı)");
            System.out.println("9 - Sipariş Listele");
            System.out.println("0 - Geri");
            System.out.print("Seçim Yapınız :");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    categoryList();
                    break;
                case "3":
                    categoryDelete();
                    break;
                case "4":
                    productCreate();
                    break;
                case "5":
                    productList();
                    break;
                case "6":
                    productDelete();
                    break;
                case "7":
                    productSearch();
                    break;
                case "8":
                    productFiltering();
                    break;
                case "9":
                    orderList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Gecersiz Secim.");
            }

        }

    }

    private static void productFiltering() {
        System.out.println("Kategori ismi giriniz : ");
        String categoryName = scanner.nextLine();

        List<Product> products = productService.getAllByCategoryName(categoryName);

        System.out.println("\n==== ÜRÜN LİSTESİ (Filtreleme Sonucu)====");

        products.forEach(product ->
                System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
        );
        System.out.println("======");
    }

    private static void productSearch() {
        System.out.println("Ürün ismi giriniz: ");
        String searchProductName = scanner.nextLine();

        List<Product> products = productService.search(searchProductName);

        System.out.println("\n==== ÜRÜN LİSTESİ (Arama Sonucu)====");

        products.forEach(product ->
                System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
        );
        System.out.println("======");
    }

    private static void orderList() {

    }

    private static void productDelete() {
        System.out.println("Silinecek ürün id'sini giriniz : ");
        String productId = scanner.nextLine();


        productService.deleteById(Long.parseLong(productId));
    }

    private static void productList() {
        int totalPage = productService.getTotalPage();

        int page = 1;

        do {
            List<Product> products = productService.getAll(page);

            System.out.println("\n==== ÜRÜN LİSTESİ(Sayfa)" + page + "/" + totalPage + "====");

            products.forEach(product ->
                    System.out.printf("%s - %s - %s\n", product.getName(), product.getPrice(), product.getCategory().getName())
            );
            System.out.println("========");

            System.out.println("Sonraki sayfa sayısı : ");
            String pageStr = scanner.nextLine();
            page = Integer.parseInt(pageStr);

        } while (page <= totalPage);

    }

    private static void productCreate() throws PatikaStoreException {
        System.out.println("Ürün ismi  giriniz : ");
        String name = scanner.nextLine();
        System.out.println("Ürün fiyatını giriniz : ");
        String price = scanner.nextLine();
        System.out.println("Ürün stok bilgisi giriniz :");
        String stock = scanner.nextLine();
        System.out.println("Kategori id giriniz :");
        String categoryId = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));

        Product product = new Product(name, new BigDecimal(price), Integer.parseInt(stock), category);

        productService.save(product, LOGINNED_USER);

    }

    private static void categoryList() {
        List<Category> categoryList = categoryService.getAll();

        categoryList.forEach(System.out::println);

    }

    private static void categoryDelete() {

        System.out.println("Kategori id giriniz:");
        String categoryId = scanner.nextLine();

        categoryService.deleteById(Long.parseLong(categoryId));

    }

    private static void createCategory() throws PatikaStoreException {
        throw new PatikaStoreException("Not Implemented");

    }

    private static void registerUser() throws PatikaStoreException {
        System.out.print("Kullanıcı Adı :");
        String userName = scanner.nextLine();
        System.out.print("Şifre Giriniz : ");
        String password = scanner.nextLine();
        System.out.println("Role seçiniz : (ADMIN, SUPPORT)");
        String roleString = scanner.nextLine().toUpperCase();

        Role role = Role.valueOf(roleString);
        userService.save(userName, password, role);

    }

    private static void getMainMenu() {
        System.out.println("===== GİRİŞ TÜRÜ SEÇİNİZ =====");
        System.out.println("1 - Kullanıcı Girişi (ADMIN, SUPPORT)");
        System.out.println("2 - Müşteri Girişi");
        System.out.println("0 - Çıkış");
        System.out.print("Seçim Yapınız :");


    }

    private static void loginCustomer() throws PatikaStoreException {
        System.out.print("E-mail : ");
        String email = scanner.nextLine();

        System.out.print("Sifre :");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        LOGINNED_CUSTOMER = customerService.login(email, password);

        while (true) {
            System.out.println("1 - Ürün Listele ");
            System.out.println("2 - Ürün Arama");
            System.out.println("3 - Ürün filtreleme (Kategori Bazlı)");
            System.out.println("4 - Sepete Ürün Ekle ");
            System.out.println("5 - Sepeti Görüntüle");
            System.out.println("6 - Sepeti Temizle");
            System.out.println("7 - Sepetteki Ürünleri Sipariş Et");
            System.out.println("8 - Siparişleri listle");
            System.out.println("0 - Geri");
            System.out.print("Seçim Yapınız :");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    productList();
                    break;
                case "2":
                    productSearch();
                    break;
                case "3":
                    productFiltering();
                    break;
                case "4":
                    addProductToCart();
                    break;
                case "5":
                    listCart();
                    break;
                case "6":
                    clearCart();
                    break;
                case "7":
                    createOrder();
                    break;
                case "8":
                    orderList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Geçersiz Seçim");

            }
        }
    }

    private static void createOrder() throws PatikaStoreException {

        System.out.println("Bir ödeme yöntemi seçiniz : (CREDIT_CARD, DEBIT_CARD, PAYPAL, BANK_TRANSFER");
        String paymentMethodStr = scanner.nextLine();

        orderService.save(LOGINNED_CUSTOMER, PaymentMethod.valueOf(paymentMethodStr));


    }

    private static void clearCart() {
        cartService.clear(LOGINNED_CUSTOMER);

    }

    private static void listCart() {

        List<CartItem> cartItems = cartItemService.getByCustomer(LOGINNED_CUSTOMER);
        System.out.println("\n==== Sepetteki Ürün Listesi ====");

        cartItems.forEach(item ->
                System.out.printf("%s - %s x %s\n",
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()));

        System.out.println("========");
    }

    private static void addProductToCart() throws PatikaStoreException {

        boolean isContinue = true;

        while (isContinue) {

            System.out.println("Ürün adı giriniz : ");
            String productName = scanner.nextLine();

            Product product = productService.getByName(productName);

            if (product == null) {
                System.out.println("Ürün bulunamadı");
            } else {
                System.out.println("Adet giriniz : ");
                int quantity = scanner.nextInt();

                if (product.getStock() < quantity) {
                    throw new PatikaStoreException(ExceptionMessagesConstant.PRODUCT_STOCK_IS_NOT_VALID);
                }

                scanner.nextLine();

                cartService.addToCart(LOGINNED_CUSTOMER, product, quantity);

                System.out.print("Sepetinize ürün eklemeye devam etmek ister misiniz (E/H)");
                String yesNo = scanner.nextLine();

                if (!"E".equalsIgnoreCase(yesNo)) {
                    isContinue = false;

                }
            }
        }


    }

    public static void registerCustomer() throws PatikaStoreException {

        System.out.print("Isım : ");
        String name = scanner.nextLine();

        System.out.print("E-mail : ");
        String email = scanner.nextLine();

        System.out.print("Sifre :");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name, email, password);


    }

}
