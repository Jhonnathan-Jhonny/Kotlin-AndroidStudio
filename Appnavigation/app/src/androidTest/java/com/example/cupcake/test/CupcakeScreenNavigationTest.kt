package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.app_navigation.CupcakeApp
import com.example.app_navigation.CupcakeScreen
import com.example.app_navigation.R
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//Cada teste na classe CupcakeScreenNavigationTest envolve o teste de um aspecto da navegação.
// Portanto, cada teste depende do objeto TestNavHostController criado.
// Em vez de ter que chamar manualmente a função setupCupcakeNavHost() para cada
// teste e configurar o controlador de navegação, você pode fazer isso automaticamente usando
// a anotação @Before fornecida pela biblioteca junit. Quando um método é anotado com @Before,
// ele é executado antes de todos os métodos anotados com @Test.

class CupcakeScreenNavigationTest {
    //garantir que seu app vá para o lugar correto ao realizar ações de navegação
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    //lateinit é usada para declarar uma propriedade que pode ser inicializada após a declaração do objeto.
    private lateinit var navController: TestNavHostController

    //determinar o estado da navegação, porque o app usa o controlador para navegar pelas várias telas no app.
    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            CupcakeApp(navController = navController)
        }
    }

    //teste para verificar se a tela Start Order é a rota de destino atual quando o app é iniciado
    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    //A tela inicial não tem um botão porque não há lugar algum para navegar nessa direção, já que essa é a tela inicial.
    // Criar uma função que confirme que a tela inicial não tem um botão "Up":
    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    //criar um comando para clicar em um botão e acionar a navegação,
    // além de conferir se a rota de destino é a tela "Flavor".

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        //Localize o botão One Cupcake pelo ID do recurso de string e execute uma ação de clique nele.
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    private fun navigateToFlavorScreen() {
//    comando para encontrar o botão One Cupcake e execute uma ação de clique
        composeTestRule.onNodeWithStringId(R.string.one_cupcake)
            .performClick()
        //Lembre-se de que o botão Next na tela "Flavor" não é clicável até que um sabor seja selecionado.
        //selecionar sabor
        composeTestRule.onNodeWithStringId(R.string.chocolate)
            .performClick()
    }

    //étodos auxiliares que naveguem até a tela "Pickup" e a de resumo.
    //Criando formato de data
    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composeTestRule.onNodeWithText(getFormattedDate())
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.next)
            .performClick()
    }

//    Ao testar outras telas, além da tela inicial,
//    você também deve testar a funcionalidade do botão "Up" para garantir que
//    ele direcione a navegação para a tela anterior.
    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }
}