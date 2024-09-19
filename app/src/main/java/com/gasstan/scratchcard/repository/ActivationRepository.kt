package com.gasstan.scratchcard.repository

import com.gasstan.scratchcard.utils.Resource
import com.gasstan.scratchcard.network.ActivationApi
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException

class ActivationRepository(
  private val api: ActivationApi,
) {

  suspend fun activateCard() = try {
    val version = api.getVersion()
    if (version.android <= 277028) {
      Resource.Error("Unable to activate card.")
    } else {
      Resource.Success(version)
    }
  } catch (e: ServerResponseException) {
    Resource.Error(e.localizedMessage ?: "Unknown exception")
  } catch (e: ClientRequestException) {
    Resource.Error("${e.response.status} ${e.localizedMessage ?: "Unknown exception"}")
  }
}

