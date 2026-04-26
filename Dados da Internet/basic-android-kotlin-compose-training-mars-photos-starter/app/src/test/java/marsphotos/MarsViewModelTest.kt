package marsphotos

import com.example.marsphotos.network.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import marsphotos.fake.FakeDataSource
import marsphotos.fake.FakeNetworkMarsPhotosRepository
import org.junit.Test

class MarsViewModelTest {


//    Observação: não é necessário chamar MarsViewlModel.getMarsPhotos()
//    diretamente para acionar uma chamada para
//    MarsPhotosRepository.getMarsPhotos(). MarsViewModel.getMarsPhotos()
//    é chamado quando o ViewModel é inicializado.
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess(){
        // O runTest() garante que o teste seja executado por uma corrotina
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
                marsViewModel.marsUiState
            )
        }
    }
}