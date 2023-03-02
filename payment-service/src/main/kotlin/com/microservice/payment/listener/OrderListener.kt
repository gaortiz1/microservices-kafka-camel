package com.microservice.payment.listener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.microservice.event.OrderCreatedEvent
import com.microservice.payment.application.request.ChargePaymentRequest
import com.microservice.payment.application.service.PaymentServiceImpl
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderListener(
    private val paymentServiceImpl: PaymentServiceImpl,
) {

    private val objectMapper = jacksonObjectMapper()

    @KafkaListener(topics = ["order-created-topic"], groupId = "microservices")
    fun receiveMessage(message: String) {
        println("Received message: $message")
        val orderCreatedEvent = objectMapper.readValue(message, OrderCreatedEvent::class.java)
        paymentServiceImpl.chargePayment(
            ChargePaymentRequest(
                orderId = orderCreatedEvent.orderId,
                name = orderCreatedEvent.name,
            )
        )
    }

}