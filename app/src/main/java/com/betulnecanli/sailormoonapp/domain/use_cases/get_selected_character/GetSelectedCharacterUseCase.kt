package com.betulnecanli.sailormoonapp.domain.use_cases.get_selected_character

import com.betulnecanli.sailormoonapp.data.repository.Repository
import com.betulnecanli.sailormoonapp.domain.model.SailorMoon

class GetSelectedCharacterUseCase(
    private val repository : Repository
) {

    suspend operator fun invoke(charID: Int): SailorMoon{
        return repository.getSelectedCharacter(charID)
    }

}