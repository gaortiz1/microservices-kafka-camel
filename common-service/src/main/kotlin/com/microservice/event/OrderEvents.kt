package com.microservice.event

import com.microservice.OrderStatus

data class OrderCreatedEvent(
    val orderId: Int,
    val name: String,
    val status: OrderStatus,
)

data class OrderCancelledEvent(
    val orderId: Int,
    val status: OrderStatus,
)