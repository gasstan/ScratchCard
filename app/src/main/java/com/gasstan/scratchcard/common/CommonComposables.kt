package com.gasstan.scratchcard.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gasstan.scratchcard.R

@Composable
fun CardActivated(
  resetCard: () -> Unit
){
  Text(stringResource(R.string.card_activated))
  Button(
    onClick = resetCard
  ) {
    Text(stringResource(R.string.reset))
  }
}