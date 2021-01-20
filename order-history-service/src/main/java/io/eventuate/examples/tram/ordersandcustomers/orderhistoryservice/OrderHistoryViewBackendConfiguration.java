package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice;

import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service.CustomerHistoryEventConsumer;
import io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.service.OrderHistoryEventConsumer;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class OrderHistoryViewBackendConfiguration {

  @Named("orderHistoryDomainEventDispatcher")
  @Singleton
  public DomainEventDispatcher orderHistoryDomainEventDispatcher(OrderHistoryEventConsumer orderHistoryEventConsumer, DomainEventDispatcherFactory domainEventDispatcherFactory) {
    return domainEventDispatcherFactory.make("orderHistoryServiceEvents", orderHistoryEventConsumer.domainEventHandlers());
  }

  @Named("customerHistoryDomainEventDispatcher")
  @Singleton
  public DomainEventDispatcher customerHistoryDomainEventDispatcher(CustomerHistoryEventConsumer customerHistoryEventConsumer, DomainEventDispatcherFactory domainEventDispatcherFactory) {
    return domainEventDispatcherFactory.make("customerHistoryServiceEvents", customerHistoryEventConsumer.domainEventHandlers());
  }
}
