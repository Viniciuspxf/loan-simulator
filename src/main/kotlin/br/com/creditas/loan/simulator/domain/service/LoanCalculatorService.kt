package br.com.creditas.loan.simulator.domain.service

import br.com.creditas.loan.simulator.domain.model.LoanSimulation
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class LoanCalculatorService {

    fun getLoanSimulation(
        loanValue: BigDecimal,
        yearlyInterestRate: BigDecimal,
        numberOfInstallments: Int
    ): LoanSimulation {
        val monthlyPayment = getMonthlyPayment(yearlyInterestRate, loanValue, numberOfInstallments)

        val totalPayment = getTotalPayment(monthlyPayment, numberOfInstallments)

        val totalInterest = getTotalInterest(totalPayment, loanValue)

        return LoanSimulation(
            monthlyPayment = monthlyPayment,
            totalPayment = totalPayment,
            totalInterest = totalInterest
        )
    }

    private fun getMonthlyPayment(
        yearlyInterestRate: BigDecimal,
        loanValue: BigDecimal,
        numberOfInstallments: Int
    ): BigDecimal {
        val monthlyPaymentRate = yearlyInterestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)

        val powerValue = BigDecimal.ONE.plus(monthlyPaymentRate).pow(numberOfInstallments)

        val numerator = loanValue.multiply(monthlyPaymentRate).multiply(powerValue)
        val denominator = powerValue.minus(BigDecimal.ONE)

        val monthlyPayment = numerator.divide(denominator, 2, RoundingMode.HALF_UP)

        return monthlyPayment
    }

    private fun getTotalPayment(
        monthlyPayment: BigDecimal,
        numberOfInstallments: Int
    ) = monthlyPayment.multiply(BigDecimal.valueOf(numberOfInstallments.toLong()))

    private fun getTotalInterest(
        totalPayment: BigDecimal,
        loanValue: BigDecimal
    ) = totalPayment.minus(loanValue)
}