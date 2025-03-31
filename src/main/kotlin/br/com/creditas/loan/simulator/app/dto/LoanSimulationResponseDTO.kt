package br.com.creditas.loan.simulator.app.dto

import br.com.creditas.loan.simulator.domain.model.LoanSimulation
import java.math.BigDecimal

data class LoanSimulationResponseDTO(
    val monthlyPayment: BigDecimal,
    val totalPayment: BigDecimal,
    val totalInterest: BigDecimal
)

fun LoanSimulation.toLoanSimulationResponseDTO() = LoanSimulationResponseDTO(
    monthlyPayment = this.monthlyPayment,
    totalPayment = this.totalPayment,
    totalInterest = this.totalInterest
)
