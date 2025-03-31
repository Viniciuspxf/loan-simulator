package br.com.creditas.loan.simulator.domain.model

import java.math.BigDecimal

data class LoanSimulation(
    val monthlyPayment: BigDecimal,
    val totalPayment: BigDecimal,
    val totalInterest: BigDecimal
)
