package br.com.creditas.loan.simulator.domain.usecase

import br.com.creditas.loan.simulator.domain.model.LoanSimulation
import br.com.creditas.loan.simulator.domain.service.InterestRateCalculatorService
import br.com.creditas.loan.simulator.domain.service.LoanCalculatorService
import io.mockk.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.time.LocalDate
import java.util.stream.Stream
import kotlin.test.assertEquals


internal class SimulateLoanUseCaseTest {
    private val interestRateCalculatorService = mockk<InterestRateCalculatorService>()
    private val loanCalculatorService = mockk<LoanCalculatorService>()

    private val simulateLoanUseCase = SimulateLoanUseCase(
        interestRateCalculatorService,
        loanCalculatorService
    )

    @ParameterizedTest
    @MethodSource("getSimulateLoanUseCaseArguments")
    fun `Should return LoanSimulation successfully`(birthDate: LocalDate, expectedAge: Int) {
        mockkStatic(LocalDate::class)

        every { LocalDate.now() } returns LocalDate.of(2025, 3, 30)

        val ageSlot = slot<Int>()

        val yearlyRate = 0.05.toBigDecimal()
        val loanValue = 1000.toBigDecimal()
        val numberOfInstallments = 10

        val expectedLoanSimulation = LoanSimulation(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.valueOf(100))

        every {
            interestRateCalculatorService.getYearlyInterestRate(capture(ageSlot))
        } returns yearlyRate

        every {
            loanCalculatorService.getLoanSimulation(loanValue, yearlyRate, numberOfInstallments)
        } returns expectedLoanSimulation

        val actualLoanSimulation = simulateLoanUseCase.getLoanSimulation(loanValue, numberOfInstallments, birthDate)

        assertEquals(expectedLoanSimulation, actualLoanSimulation)
        assertEquals(expectedAge, ageSlot.captured)

        unmockkStatic(LocalDate::class)
    }

    companion object {
        @JvmStatic
        val simulateLoanUseCaseArguments: Stream<Arguments>
            get() = Stream.of(
                Arguments.of(LocalDate.of(2001, 10, 16), 23),
                Arguments.of(LocalDate.of(2015, 3, 30), 10),
            )
    }
}