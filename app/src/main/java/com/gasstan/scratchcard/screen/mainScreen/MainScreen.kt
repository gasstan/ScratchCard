package com.gasstan.scratchcard.screen.mainScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gasstan.scratchcard.R
import com.gasstan.scratchcard.model.ScratchCardState
import com.gasstan.scratchcard.navigation.Destinations
import com.gasstan.scratchcard.navigation.navigateTo
import com.gasstan.scratchcard.ui.theme.ScratchCardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
  navHostController: NavHostController,
  viewModel: MainViewModel = koinViewModel()
) {
  val scratchCardState by viewModel.cardState.collectAsStateWithLifecycle()

  Content(
    scratchCardState = scratchCardState,
    navigateToScratch = { navHostController.navigateTo(Destinations.Scratch) },
    navigateToActivation = { navHostController.navigateTo(Destinations.Activation) }
  )
}

@Composable
private fun Content(
  scratchCardState: ScratchCardState,
  navigateToScratch: () -> Unit,
  navigateToActivation: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(R.string.scratch_card_state, scratchCardState),
      modifier = Modifier.padding(16.dp)
    )

    Button(onClick = navigateToScratch) {
      Text(stringResource(id = R.string.go_to_scratch_screen))
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = navigateToActivation) {
      Text(stringResource(R.string.go_to_activation_screen))
    }
  }
}

@Preview
@Composable
private fun MainScreenPreview() = ScratchCardTheme {
  Content(scratchCardState = ScratchCardState.Unscratched,
    navigateToScratch = { },
    navigateToActivation = {})
}
