package io.eventuate.examples.tram.ordersandcustomers.orderhistoryservice.domain;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.Money;
import io.eventuate.examples.tram.ordersandcustomers.commondomain.OrderState;
import io.eventuate.examples.tram.ordersandcustomers.orderhistory.common.CustomerView;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

import javax.inject.Singleton;


@Singleton
public class CustomerViewRepository implements PanacheMongoRepositoryBase<CustomerView, Long> {

  public void createCustomer(Long customerId, String customerName, Money creditLimit) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.combine(Updates.set("name", customerName), Updates.set("creditLimit", creditLimit)), new UpdateOptions().upsert(true));
  }

  public void addOrder(Long customerId, Long orderId, Money orderTotal) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId + ".orderTotal", orderTotal), new UpdateOptions().upsert(true));
  }

  public void setOrderState(Long customerId, Long orderId, OrderState orderState) {
    mongoCollection().updateOne(Filters.eq("_id", customerId),
            Updates.set("orders." + orderId + ".state", orderState.name()), new UpdateOptions().upsert(true));
  }
}
