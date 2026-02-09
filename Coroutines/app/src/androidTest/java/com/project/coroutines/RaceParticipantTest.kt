package com.project.coroutines

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
    )
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch { raceParticipant.run() } //simular o início da corrida
        advanceTimeBy(raceParticipant.progressDelayMillis) //ajuda a reduzir o tempo de execução do teste.
        runCurrent() //executa todas as tarefas pendentes no momento atual.
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        val expectedProgress = 100
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis) //Simular o término da corrida
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }
}