package com.gasstan.scratchcard.cache

import com.gasstan.scratchcard.model.ScratchCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class Cache {
  var cardState = MutableStateFlow<ScratchCardState>(ScratchCardState.Unscratched)

  fun resetCard() {
    cardState.update { ScratchCardState.Unscratched }
  }
}