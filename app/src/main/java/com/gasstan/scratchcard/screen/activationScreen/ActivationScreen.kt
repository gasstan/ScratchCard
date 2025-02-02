package com.gasstan.scratchcard.screen.activationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.earthbanc.todo.utils.onFailure
import com.gasstan.scratchcard.R
import com.gasstan.scratchcard.common.CardActivated
import com.gasstan.scratchcard.navigation.Destinations
import com.gasstan.scratchcard.navigation.navigateTo
import com.gasstan.scratchcard.model.ScratchCardState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ActivationScreen(
  navHostController: NavHostController,
  activationViewModel: ActivationViewModel = koinViewModel()
) {
  val cardState by activationViewModel.cardState.collectAsStateWithLifecycle()
  val activationState by activationViewModel.activationState.collectAsStateWithLifecycle()
  var openAlertDialog by remember { mutableStateOf(false) }

  activationState.onFailure {
    AlertDialog(
      onDismissRequest = { openAlertDialog = false },
      title = { Text(stringResource(R.string.error)) },
      text = { Text(it) },
      confirmButton = {
        Button(onClick = { openAlertDialog = false }) {
          Text(stringResource(R.string.ok))
        }
      }
    )
  }

  Content(
    cardState = cardState,
    activateCard = activationViewModel::activateCard,
    goToScratchScreen = { navHostController.navigateTo(Destinations.Scratch) },
    resetCard = activationViewModel::resetCard
  )
}

@Composable
fun Content(
  cardState: ScratchCardState,
  activateCard: () -> Unit,
  goToScratchScreen: () -> Unit,
  resetCard: () -> Unit
) {

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    when (cardState) {
      is ScratchCardState.Scratched -> {
        Button(onClick = activateCard) {
          Text(stringResource(R.string.activate_card))
        }
      }

      ScratchCardState.Activated -> {
        CardActivated(resetCard = resetCard)
      }

      ScratchCardState.Unscratched -> {
        Text(stringResource(R.string.you_have_to_scratch_the_card_first))
        Button(onClick = goToScratchScreen) {
          Text(stringResource(R.string.go_to_scratch_screen))
        }
      }
    }
  }
}