package com.gasstan.scratchcard.model

sealed class ScratchCardState {
  data object Unscratched : ScratchCardState()
  class Scratched(val code: String) : ScratchCardState() {
    override fun toString(): String {
      return "Scratched(code='$code')"
    }
  }

  data object Activated : ScratchCardState()
}