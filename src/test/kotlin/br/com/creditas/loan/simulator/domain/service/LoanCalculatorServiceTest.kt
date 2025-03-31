package br.com.creditas.loan.simulator.domain.service

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.util.stream.Stream
import kotlin.test.assertEquals


internal class LoanCalculatorServiceTest {
    private val loanCalculatorService = LoanCalculatorService()

    @ParameterizedTest
    @MethodSource("getLoanSimulationArguments")
    fun `Should return the correct interest rate`(
        loanValue: BigDecimal,
        yearlyInterestRate: BigDecimal,
        numberOfInstallments: Int,
        expectedMonthlyPayment: BigDecimal
    )  {

        val loanSimulation = loanCalculatorService.getLoanSimulation(
            loanValue = loanValue,
            yearlyInterestRate = yearlyInterestRate,
            numberOfInstallments = numberOfInstallments
        )

        val expectedTotalPayment = expectedMonthlyPayment.multiply(numberOfInstallments.toBigDecimal())
        val expectedTotalInterest = expectedTotalPayment.minus(loanValue)

        assertEquals(expectedMonthlyPayment, loanSimulation.monthlyPayment)
        assertEquals(expectedTotalPayment, loanSimulation.totalPayment)
        assertEquals(expectedTotalInterest, loanSimulation.totalInterest)
    }

    companion object {
        @JvmStatic
        val loanSimulationArguments: Stream<Arguments>
            get() = Stream.of(
                Arguments.of(BigDecimal.valueOf(1000), BigDecimal.valueOf(0.05), 2, BigDecimal.valueOf(503.13)),
                Arguments.of(BigDecimal.valueOf(2500), BigDecimal.valueOf(0.03), 12, BigDecimal.valueOf(211.73)),
                Arguments.of(BigDecimal.valueOf(30000), BigDecimal.valueOf(0.1), 120, BigDecimal.valueOf(396.45)),
            )
    }
}