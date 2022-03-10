package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.OrderDTO;
import java.util.List;

public interface IOrderService {
    OrderDTO addOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrderDetails();

}
