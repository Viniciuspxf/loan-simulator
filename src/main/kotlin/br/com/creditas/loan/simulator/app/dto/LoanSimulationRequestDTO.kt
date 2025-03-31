package br.com.creditas.loan.simulator.app.dto

import java.math.BigDecimal
import java.time.LocalDate

data class LoanSimulationRequestDTO(
    val loanValue: BigDecimal,
    val numberOfInstallments: Int,
    val birthDate: LocalDate,
)
