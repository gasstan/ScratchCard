package com.gasstan.scratchcard.screen.scratchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gasstan.scratchcard.R
import com.gasstan.scratchcard.common.CardActivated
import com.gasstan.scratchcard.navigation.Destinations
import com.gasstan.scratchcard.navigation.navigateTo
import com.gasstan.scratchcard.model.ScratchCardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScratchScreen(
  navHostController: NavHostController,
  viewModel: ScratchViewModel = koinViewModel()
) {
  val scratchCardState by viewModel.cardState.collectAsStateWithLifecycle()
  val showLoading by viewModel.showLoading.collectAsStateWithLifecycle()

  Content(
    cardState = scratchCardState,
    showLoading = showLoading,
    scratchCard = viewModel::scratchCard,
    resetCard = viewModel::resetCard,
    navigateToActivation = {
      navHostController.navigateTo(Destinations.Activation)
    }
  )
}

@Composable
private fun Content(
  cardState: ScratchCardState,
  showLoading: Boolean,
  scratchCard: () -> Unit,
  resetCard: () -> Unit,
  navigateToActivation: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    when (cardState) {
      ScratchCardState.Unscratched -> {
        if (showLoading) {
          CircularProgressIndicator()
        } else {
          Text(stringResource(R.string.your_code, "********"), textAlign = TextAlign.Center)
        }
        Button(
          onClick = scratchCard
        ) {
          Text(stringResource(R.string.scratch_card))
        }
      }

      is ScratchCardState.Scratched -> {
        Text(stringResource(R.string.your_code, cardState.code), textAlign = TextAlign.Center)
        Button(onClick = navigateToActivation) {
          Text(stringResource(R.string.activate))
        }
      }

      ScratchCardState.Activated ->
        CardActivated(resetCard = resetCard)
    }
  }
}
