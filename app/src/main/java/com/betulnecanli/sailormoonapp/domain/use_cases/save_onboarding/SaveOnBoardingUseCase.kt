package com.betulnecanli.sailormoonapp.domain.use_cases.save_onboarding

import com.betulnecanli.sailormoonapp.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository : Repository
) {

    suspend operator fun invoke(completed : Boolean){
        repository.saveOnBoardingState(completed = completed)

    }

}