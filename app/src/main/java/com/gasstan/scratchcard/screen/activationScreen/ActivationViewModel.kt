package com.gasstan.scratchcard.screen.activationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gasstan.scratchcard.utils.onSuccess
import com.gasstan.scratchcard.cache.Cache
import com.gasstan.scratchcard.repository.ActivationRepository
import com.gasstan.scratchcard.model.ScratchCardState
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActivationViewModel(
  private val repository: ActivationRepository,
  private val cache: Cache,
) : ViewModel() {
  val cardState = cache.cardState.asStateFlow()

  fun activateCard() {
    viewModelScope.launch {
      repository.activateCard().onSuccess {
        cache.cardState.update { ScratchCardState.Activated }
      }
    }
  }

  fun resetCard() {
    cache.resetCard()
  }
}