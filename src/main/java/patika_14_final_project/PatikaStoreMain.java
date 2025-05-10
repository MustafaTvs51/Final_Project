package patika_14_final_project;

import patika_14_final_project.service.CustomerService;

import java.util.Scanner;

public class PatikaStoreMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("===== PATIKA STORE HOŞ GELDINIZ =====");
            System.out.println("1 - Musteri Kaydı ");
            System.out.println("2 - Gırıs Yap ");
            System.out.println("0 - Cıkıs ");

            System.out.print("SECIM YAPINIZ : ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    savaCustomer(scanner);
                    break;
                case "2":
                    loginCustomer(scanner);
                    break;
                case "0":
                    System.out.println("Cıkıs Yapılıyor ... ");
                    return;
                default:
                    System.out.println("Gecersiz Secim.");

            }
        }
    }

    private static void loginCustomer(Scanner scanner) {
        System.out.print("E-mail : ");
        String email = scanner.nextLine();

        System.out.print("Sifre :");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);

    }

    public static void savaCustomer(Scanner scanner) {

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
