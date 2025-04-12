package com.ibm.processor.orderapp.util;

import com.ibm.processor.orderapp.dto.OrderDto;
import com.ibm.processor.orderapp.entity.Order;

public class OrderConverter {

    /**
     * Method to convert an entity class to a dto class for response usage.
     * @param order to be converted
     * @return converted order dto
     */
    public static OrderDto toDto(final Order order) {

        final OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setOrderNr(order.getOrderNr());
        orderDto.setOrderedOn(order.getOrderedOn());
        orderDto.setName(order.getName());
        orderDto.setProductName(order.getProduct().getName());
        orderDto.setStatus(order.getStatus().getName());
        orderDto.setQuantity(order.getQuantity());

        return orderDto;
    }

}
