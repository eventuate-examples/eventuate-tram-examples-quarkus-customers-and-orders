package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice;

import com.mongodb.client.MongoClient;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.CustomerViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain.OrderViewRepository;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service.OrderHistoryViewService;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewMongoConfiguration {

  @Singleton
  public MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, "customers_and_orders");
  }

  @Singleton
  public CustomerViewRepository customerViewRepository(MongoTemplate mongoTemplate) {
    return new CustomerViewRepository(mongoTemplate);
  }

  @Singleton
  public OrderViewRepository orderViewRepository(MongoTemplate mongoTemplate) {
    return new OrderViewRepository(mongoTemplate);
  }

  @Singleton
  public OrderHistoryViewService orderHistoryViewService(CustomerViewRepository customerViewRepository,
                                                         OrderViewRepository orderViewRepository) {
    return new OrderHistoryViewService(customerViewRepository, orderViewRepository);
  }
}
