package patika_14_final_project;

import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.User;
import patika_14_final_project.model.enums.Role;
import patika_14_final_project.service.CustomerService;
import patika_14_final_project.service.UserService;
import patika_14_final_project.util.PasswordUtil;

import java.util.Scanner;

public class PatikaStoreMain {
    private static final Scanner scanner = new Scanner(System.in);

    private static final UserService userService = new UserService();

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
            getLoginedUserMenu();

        } else {
            throw new RuntimeException(ExceptionMessagesConstant.USER_IS_NOT_ACTIVE);
        }
    }

    private static void getLoginedUserMenu() {
        while (true) {
            System.out.println("===== LOGİN OLAN KULLANICI MENÜSÜ =====");
            System.out.println("1 - Kategori Oluştur");
            System.out.println("2 - Kategori Listele ");
            System.out.println("3 - Kategori Sil ");
            System.out.println("4 - Ürün Oluştur");
            System.out.println("5 - Ürün Listele");
            System.out.println("6 - Ürün Sil");
            System.out.println("7 - Sipariş Listele");
            System.out.println("0 - Geri");
            System.out.print("Seçim Yapınız :");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    categoryDelete();
                    break;
                case "3":
                    categoryList();
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
                    orederList();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Gecersiz Secim.");
            }

        }

    }

    private static void orederList() {
    }

    private static void productDelete() {
    }

    private static void productList() {

    }

    private static void productCreate() {

    }

    private static void categoryList() {

    }

    private static void categoryDelete() {

    }

    private static void createCategory() {

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
        customerService.login(email, password);

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
