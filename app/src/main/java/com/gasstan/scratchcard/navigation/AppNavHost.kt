package com.gasstan.scratchcard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gasstan.scratchcard.screen.activationScreen.ActivationScreen
import com.gasstan.scratchcard.screen.mainScreen.MainScreen
import com.gasstan.scratchcard.screen.scratchScreen.ScratchScreen

@Composable
fun AppNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  startDestination: String = Destinations.Main.route,
) {
  NavHost(
    modifier = modifier,
    navController = navController,
    startDestination = startDestination
  ) {
    composable(Destinations.Main.route) {
      MainScreen(navController)
    }

    composable(Destinations.Activation.route) {
      ActivationScreen(navController)
    }

    composable(Destinations.Scratch.route) {
      ScratchScreen(navController)
    }
  }
}

fun NavHostController.navigateTo(destinations: Destinations) {
  navigate(destinations.route) {
    popUpTo(Destinations.Main.route)
  }
}

sealed class Destinations(val route: String) {
  data object Main : Destinations("main")
  data object Activation : Destinations("activation")
  data object Scratch : Destinations("scratch")
}