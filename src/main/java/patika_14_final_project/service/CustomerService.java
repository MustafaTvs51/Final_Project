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

        if (isExist){
            throw new PatikaStoreException(ExceptionMessagesConstant.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }

        Customer customer = new Customer(name,email, PasswordUtil.hash(password));
        customerDao.save(customer);
        System.out.println("Kayıt Basarili ! ");
    }
}
