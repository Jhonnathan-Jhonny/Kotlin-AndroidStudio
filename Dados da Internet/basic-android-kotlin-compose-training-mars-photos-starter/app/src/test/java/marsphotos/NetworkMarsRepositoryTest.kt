package marsphotos

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import marsphotos.fake.FakeDataSource
import marsphotos.fake.FakeMarsApiService
import org.junit.Test

class NetworkMarsRepositoryTest {

//    OBS: Se o código em um teste de unidade local referenciar o agente Main, uma exceção,
//    como a mostrada acima, vai ser gerada quando os testes de unidade forem executados.
//    Para resolver esse problema, é preciso definir explicitamente o agente padrão ao executar testes de unidade.
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() {
        //corpo do método de teste executado em uma corrotina.
        runTest {
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
    }
}