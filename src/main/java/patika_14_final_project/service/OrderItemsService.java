package patika_14_final_project.service;

import patika_14_final_project.dao.OrderItemDAO;
import patika_14_final_project.model.OrderItems;

import java.util.List;

public class OrderItemsService {

    private final OrderItemDAO orderItemDAO;

    public OrderItemsService() {
        this.orderItemDAO = new OrderItemDAO();
    }

    public void save(List<OrderItems> orderItems){

        orderItemDAO.saveAll(orderItems);
        System.out.println("Sipariş Ürünleri Kaydedildi.");

    }
}
