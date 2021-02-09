package io.eventuate.examples.tram.ordersandcustomers.endtoendtests;


import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.CreateOrderRequest;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.CreateOrderResponse;
import io.eventuate.examples.tram.ordersandcustomers.orders.webapi.GetOrderResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/orders")
@RegisterRestClient(configKey="order-api")
public interface OrderRestClientService {
  @POST
  CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
