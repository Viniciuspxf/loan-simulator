package br.com.creditas.loan.simulator.app

import br.com.creditas.loan.simulator.app.dto.LoanSimulationRequestDTO
import br.com.creditas.loan.simulator.app.dto.toLoanSimulationResponseDTO
import br.com.creditas.loan.simulator.domain.usecase.SimulateLoanUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/loan-simulator")
class LoanSimulationController(
    private val simulateLoanUseCase: SimulateLoanUseCase
) {
    @PostMapping("/simulate")
    fun getLoanSimulation(
        @RequestBody request: LoanSimulationRequestDTO
    ) = simulateLoanUseCase.getLoanSimulation(
        request.loanValue,
        request.numberOfInstallments,
        request.birthDate,
    ).toLoanSimulationResponseDTO()
}


