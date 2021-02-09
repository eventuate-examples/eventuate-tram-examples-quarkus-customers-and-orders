package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice;

import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.CustomerViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.OrderViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service.OrderHistoryViewService;

import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewMongoConfiguration {

  @Singleton
  public OrderHistoryViewService orderHistoryViewService(CustomerViewRepository customerViewRepository,
                                                         OrderViewRepository orderViewRepository) {
    return new OrderHistoryViewService(customerViewRepository, orderViewRepository);
  }
}
