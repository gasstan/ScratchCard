package com.gasstan.scratchcard.screen.activationScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earthbanc.todo.utils.Resource
import com.earthbanc.todo.utils.onSuccess
import com.gasstan.scratchcard.cache.Cache
import com.gasstan.scratchcard.model.Version
import com.gasstan.scratchcard.repository.ActivationRepository
import com.gasstan.scratchcard.model.ScratchCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActivationViewModel(
  private val repository: ActivationRepository,
  private val cache: Cache,
) : ViewModel() {
  val cardState = cache.cardState.asStateFlow()
  private val _activationState = MutableStateFlow<Resource<Version>>(Resource.Empty())
  val activationState = _activationState.asStateFlow()

  fun activateCard() {
    viewModelScope.launch {
      _activationState.update {
        repository.activateCard().onSuccess {
          cache.cardState.update { ScratchCardState.Activated }
        }
      }
    }
  }

  fun resetCard() {
    cache.resetCard()
  }
}