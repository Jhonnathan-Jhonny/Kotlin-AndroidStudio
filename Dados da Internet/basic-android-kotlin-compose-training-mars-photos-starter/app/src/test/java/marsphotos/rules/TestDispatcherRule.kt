package marsphotos.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher


//TestWarcher: permite realizar ações em diferentes fases de execução de um teste
class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
): TestWatcher(){
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: org.junit.runner.Description) {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: org.junit.runner.Description) {
        Dispatchers.resetMain()
    }
}