package br.com.creditas.loan.simulator.domain.service

import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class InterestRateCalculatorService {
    fun getYearlyInterestRate(age: Int): BigDecimal {
        if (age <= 25)
            return BigDecimal.valueOf(0.05)

        if (age <= 50)
            return BigDecimal.valueOf(0.03)

        if (age <= 60)
            return BigDecimal.valueOf(0.02)
        
        return BigDecimal.valueOf(0.04)
    }
}
