package com.gasstan.scratchcard

import android.app.Application
import com.gasstan.scratchcard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScratchCardApp : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin() {
      androidContext(this@ScratchCardApp)
      modules(appModule)
    }

  }
}