package com.microservice.order.application.service

import com.microservice.OrderStatus
import com.microservice.exception.NotFoundException
import com.microservice.order.application.request.CancelOrderRequest
import com.microservice.order.application.request.CreateOrderRequest
import com.microservice.order.model.Order
import com.microservice.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) :  IOrderService {

    @Transactional
    override fun createOrder(createOrderRequest: CreateOrderRequest): Order =
        orderRepository.save(
            Order(
                name = createOrderRequest.name,
                status = OrderStatus.CREATED
            )
        )

    @Transactional
    override fun cancelOrder(cancelOrderRequest: CancelOrderRequest): Order {

        val order = orderRepository.findById(cancelOrderRequest.orderId)
            .orElseThrow { throw NotFoundException("order_not_found") }

        order.canceled()

        return orderRepository.save(order)
    }
}