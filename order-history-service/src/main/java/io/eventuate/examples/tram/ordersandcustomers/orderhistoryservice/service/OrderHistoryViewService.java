package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service;

import io.eventuate.examples.tram.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.CustomerViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.OrderViewRepository;

public class OrderHistoryViewService {

  private CustomerViewRepository customerViewRepository;
  private OrderViewRepository orderViewRepository;

  public OrderHistoryViewService(CustomerViewRepository customerViewRepository,
                                 OrderViewRepository orderViewRepository) {
    this.customerViewRepository = customerViewRepository;
    this.orderViewRepository = orderViewRepository;
  }

  public void createCustomer(Long customerId, String customerName, Money creditLimit) {
    customerViewRepository.createCustomer(customerId, customerName, creditLimit);
  }

  public void addOrder(Long customerId, Long orderId, Money orderTotal) {
    customerViewRepository.addOrder(customerId, orderId, orderTotal);
    orderViewRepository.createOrder(orderId, orderTotal);
  }

  public void approveOrder(Long customerId, Long orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.APPROVED);
    orderViewRepository.setOrderState(orderId, OrderState.APPROVED);
  }

  public void rejectOrder(Long customerId, Long orderId) {
    customerViewRepository.setOrderState(customerId, orderId, OrderState.REJECTED);
    orderViewRepository.setOrderState(orderId, OrderState.REJECTED);
  }
}
