package com.gasstan.scratchcard.screen.activationScreen

import com.gasstan.scratchcard.cache.Cache
import com.gasstan.scratchcard.model.ScratchCardState
import com.gasstan.scratchcard.model.Version
import com.gasstan.scratchcard.network.ActivationApi
import com.gasstan.scratchcard.repository.ActivationRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivationViewModelTest {

  private val api: ActivationApi = mockk()
  private lateinit var repository: ActivationRepository
  private lateinit var cache: Cache
  private lateinit var viewModel: ActivationViewModel

  @Before
  fun setUp() {
    Dispatchers.setMain(StandardTestDispatcher())
    repository = ActivationRepository(api)
    cache = Cache()
    viewModel = ActivationViewModel(repository, cache)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `successful activation`() = runTest {
    coEvery { api.getVersion() } returns Version(android = 300001)

    viewModel.activateCard()
    advanceUntilIdle()

    assertEquals(ScratchCardState.Activated, viewModel.cardState.value)
  }

  @Test
  fun `activation failed`() = runTest {
    coEvery { api.getVersion() } returns Version(android = 277028)

    viewModel.activateCard()
    advanceUntilIdle()

    assertEquals(ScratchCardState.Unscratched, viewModel.cardState.value)
  }

  @Test
  fun `resetCard sets cardState back to Unscratched`() = runTest {
    cache.cardState.value = ScratchCardState.Activated

    viewModel.resetCard()

    assertEquals(ScratchCardState.Unscratched, viewModel.cardState.value)
  }
}