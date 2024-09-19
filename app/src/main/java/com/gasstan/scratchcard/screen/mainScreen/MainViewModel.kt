package com.gasstan.scratchcard.screen.mainScreen

import androidx.lifecycle.ViewModel
import com.gasstan.scratchcard.cache.Cache
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(cache: Cache) : ViewModel() {
  val cardState = cache.cardState.asStateFlow()
}