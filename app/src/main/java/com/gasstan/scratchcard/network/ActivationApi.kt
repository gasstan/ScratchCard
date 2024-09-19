package com.gasstan.scratchcard.network

import com.gasstan.scratchcard.model.Version
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val ENDPOINT = "https://api.o2.sk/version"

class ActivationApi(private val client: HttpClient) {

  suspend fun getVersion(
  ): Version = client.get(ENDPOINT)
}