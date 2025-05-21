package patika_14_final_project.service;

import patika_14_final_project.dao.PaymentDAO;
import patika_14_final_project.model.Order;
import patika_14_final_project.model.Payment;
import patika_14_final_project.model.enums.PaymentMethod;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();

    }

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = new PaymentDAO();
    }



    public Payment save(Order order, PaymentMethod paymentMethod){

        Payment payment = new Payment(order,paymentMethod);
        paymentDAO.save(payment);
        return payment;
    }
}

