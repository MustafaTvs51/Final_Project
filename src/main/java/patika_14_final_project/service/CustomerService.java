package patika_14_final_project.service;

import patika_14_final_project.dao.CustomerDao;
import patika_14_final_project.exception.ExceptionMessagesConstant;
import patika_14_final_project.exception.PatikaStoreException;
import patika_14_final_project.model.Customer;
import patika_14_final_project.util.PasswordUtil;

public class CustomerService {

    private CustomerDao customerDao;

    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public void save(String name, String email, String password) {

        boolean isExist = customerDao.existByEmail(email);

        if (isExist) {
            throw new PatikaStoreException(ExceptionMessagesConstant.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }

        Customer customer = new Customer(name, email, PasswordUtil.hash(password));
        customerDao.save(customer);
        System.out.println("Kayıt Basarili ! ");
    }

    public void login(String email, String password) {

        boolean isExist = customerDao.existByEmail(email);

        if (!isExist) {
            throw new PatikaStoreException(ExceptionMessagesConstant.CUSTOMER_EMAIL_DOES_NOT_EXISTS);
        }
        String hashedPassword = PasswordUtil.hash(password);
        Customer foundCustomer = customerDao.findByEmail(email);

        if (foundCustomer != null) {
            boolean passwordEquals = foundCustomer.getPassword().equals(hashedPassword);
            if (!passwordEquals) {
                throw new PatikaStoreException(ExceptionMessagesConstant.CUSTOMER_PASSWORD_DOES_NOT_MATCH);
            } else {
                System.out.println("Kullanici sisteme giris yapti ! ");
            }
        }
    }
}
