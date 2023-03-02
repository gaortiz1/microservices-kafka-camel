package com.microservice.order.application.controller

import com.microservice.order.application.request.CreateOrderRequest
import com.microservice.order.producer.OrderServiceProducer
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderServiceProducer,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody createOrderRequest: CreateOrderRequest) {
        orderService.createOrder(createOrderRequest)
    }

    companion object {
        private val log = LoggerFactory.getLogger(OrderController::class.java)
    }
}