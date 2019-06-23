# restaurant-service

Simple restaurant order management system. Allows the client to create orders and mark those orders as either completed or canceled. Also offers an endpoint for viewing placed orders (orders that are not yet complete or canceled).

## Endpoints

**POST**: `/order-service/v1/orders/place`

Send the order as a list of item names.
```
{
	"items": [
		"Cheese Burger",
		"Sweet Potato Fries"
		]
}
```
Returns the list of ordered items, along with additional information about the order itself. 


**PUT**: `/order-service/v1/orders/complete/{order-id}`

Change the status of an order to completed by the order ID (and save).


**PUT**: `/order-service/v1/orders/cancel/{order-id}`

Change the status of an order to canceled by the order ID (and save).


**GET**: `/order-service/v1/orders/placed`

Get a list of orders that have been placed, but are not completed or canceled. 
