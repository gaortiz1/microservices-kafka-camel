package com.microservice.order.application.service

import com.microservice.order.application.request.CancelOrderRequest
import com.microservice.order.application.request.CreateOrderRequest
import com.microservice.order.model.Order

interface IOrderService {

    fun createOrder(createOrderRequest: CreateOrderRequest): Order

    fun cancelOrder(cancelOrderRequest: CancelOrderRequest): Order

}