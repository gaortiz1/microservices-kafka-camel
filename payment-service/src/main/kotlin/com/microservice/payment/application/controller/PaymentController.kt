package com.microservice.payment.application.controller

import com.microservice.payment.application.request.ChargePaymentRequest
import com.microservice.payment.application.service.PaymentServiceImpl
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class PaymentController(
    private val paymentServiceImpl: PaymentServiceImpl,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody createInvoiceRequest: ChargePaymentRequest) {
        paymentServiceImpl.chargePayment(createInvoiceRequest)
    }

}