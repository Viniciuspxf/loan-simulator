package br.com.creditas.loan.simulator.domain.usecase

import br.com.creditas.loan.simulator.domain.model.LoanSimulation
import br.com.creditas.loan.simulator.domain.service.InterestRateCalculatorService
import br.com.creditas.loan.simulator.domain.service.LoanCalculatorService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period

@Service
class SimulateLoanUseCase(
    private val interestRateCalculatorService: InterestRateCalculatorService,
    private val loanCalculatorService: LoanCalculatorService
) {
    fun getLoanSimulation(loanValue: BigDecimal, numberOfInstallments: Int, birthDate: LocalDate): LoanSimulation {
        val age = Period.between(birthDate, LocalDate.now()).years

        val yearlyInterestRate = interestRateCalculatorService.getYearlyInterestRate(age)

        return loanCalculatorService.getLoanSimulation(loanValue, yearlyInterestRate, numberOfInstallments)
    }
}