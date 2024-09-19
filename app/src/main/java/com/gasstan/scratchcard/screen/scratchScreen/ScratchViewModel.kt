package com.gasstan.scratchcard.screen.scratchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gasstan.scratchcard.cache.Cache
import com.gasstan.scratchcard.model.ScratchCardState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class ScratchViewModel(private val cache: Cache) : ViewModel() {
  val cardState = cache.cardState.asStateFlow()
  val showLoading = MutableStateFlow(false)

  fun scratchCard() {
    viewModelScope.launch {
      showLoading.update { true }
      delay(2000)
      showLoading.update { false }
      cache.cardState.update { ScratchCardState.Scratched(UUID.randomUUID().toString()) }
    }
  }

  fun resetCard() {
    cache.resetCard()
  }
}