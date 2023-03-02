package com.microservice.payment.application.service

import com.microservice.payment.application.request.ChargePaymentRequest
import com.microservice.payment.application.request.RefundPaymentRequest
import com.microservice.payment.model.Payment

interface PaymentService {

    fun chargePayment(chargePaymentRequest: ChargePaymentRequest): Payment

    fun refundPayment(refundPaymentRequest: RefundPaymentRequest): Payment

}