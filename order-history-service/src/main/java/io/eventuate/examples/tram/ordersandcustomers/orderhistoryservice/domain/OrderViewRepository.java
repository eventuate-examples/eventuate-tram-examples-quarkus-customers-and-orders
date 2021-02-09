package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;
import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.OrderView;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class OrderViewRepository implements PanacheMongoRepositoryBase<OrderView, Long> {
  public void createOrder(Long orderId, Money orderTotal) {
    mongoCollection().updateOne(Filters.eq("_id", orderId),
            Updates.set("orderTotal", orderTotal), new UpdateOptions().upsert(true));
  }

  public void setOrderState(Long orderId, OrderState orderState) {
    mongoCollection().updateOne(Filters.eq("_id", orderId),
            Updates.set("state", orderState.name()), new UpdateOptions().upsert(true));
  }
}
