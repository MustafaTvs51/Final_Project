package patika_14_final_project;

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

        userService.login(userName, password);
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
