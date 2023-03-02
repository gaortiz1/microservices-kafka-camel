package com.microservice.order.producer

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.microservice.event.OrderCancelledEvent
import com.microservice.event.OrderCreatedEvent
import com.microservice.order.application.request.CancelOrderRequest
import com.microservice.order.application.request.CreateOrderRequest
import com.microservice.order.application.service.IOrderService
import com.microservice.order.application.service.OrderServiceImpl
import com.microservice.order.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderServiceProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val orderServiceImpl: OrderServiceImpl,
) : IOrderService {

    private val objectMapper = jacksonObjectMapper()

    override fun createOrder(createOrderRequest: CreateOrderRequest): Order {
        val order = orderServiceImpl.createOrder(createOrderRequest)
        kafkaTemplate.send(
            "order-created-topic",
            OrderCreatedEvent(
                orderId = order.id!!,
                name = order.name,
                status = order.status
            )
        )
        return order;
    }

    override fun cancelOrder(cancelOrderRequest: CancelOrderRequest): Order {
        val orderCancelled = orderServiceImpl.cancelOrder(cancelOrderRequest)
        kafkaTemplate.send(
            "order-cancelled-topic",
            OrderCancelledEvent(
                orderId = orderCancelled.id!!,
                status = orderCancelled.status
            )
        )
        return orderCancelled
    }
}