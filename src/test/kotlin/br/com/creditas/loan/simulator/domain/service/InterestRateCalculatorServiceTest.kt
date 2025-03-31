package br.com.creditas.loan.simulator.domain.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.util.stream.Stream

internal class InterestRateCalculatorServiceTest {

    private val interestRateCalculatorService = InterestRateCalculatorService()

    @ParameterizedTest
    @MethodSource("getYearlyInterestRateParameters")
    fun `Should return the correct interest rate`(age: Int, expectedRate: BigDecimal) {
        assertEquals(expectedRate, interestRateCalculatorService.getYearlyInterestRate(age))
    }

    companion object {
        @JvmStatic
        val yearlyInterestRateParameters: Stream<Arguments>
            get() = Stream.of(
                Arguments.of(1, BigDecimal.valueOf(0.05)),
                Arguments.of(25, BigDecimal.valueOf(0.05)),
                Arguments.of(26, BigDecimal.valueOf(0.03)),
                Arguments.of(50, BigDecimal.valueOf(0.03)),
                Arguments.of(51, BigDecimal.valueOf(0.02)),
                Arguments.of(60, BigDecimal.valueOf(0.02)),
                Arguments.of(61, BigDecimal.valueOf(0.04)),
                Arguments.of(100, BigDecimal.valueOf(0.04))
            )
    }
}