package com.gasstan.scratchcard.di

import com.gasstan.scratchcard.cache.Cache
import com.gasstan.scratchcard.network.ActivationApi
import com.gasstan.scratchcard.network.provideKtorHttpClient
import com.gasstan.scratchcard.repository.ActivationRepository
import com.gasstan.scratchcard.screen.activationScreen.ActivationViewModel
import com.gasstan.scratchcard.screen.mainScreen.MainViewModel
import com.gasstan.scratchcard.screen.scratchScreen.ScratchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  single { provideKtorHttpClient() }

  single { ActivationApi(get()) }
  single { ActivationRepository(get()) }
  single { Cache() }

  viewModel { ActivationViewModel(get(), get()) }
  viewModel { MainViewModel(get()) }
  viewModel { ScratchViewModel(get()) }
}