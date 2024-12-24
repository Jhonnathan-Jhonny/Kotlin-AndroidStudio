package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.createSavedStateHandle
import com.example.app_navigation.R
import com.example.app_navigation.ui.SelectOptionScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get: Rule
    val composeTestRule =  createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent(){
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"
        //Definindo o conteúdo como o combinável
        composeTestRule.setContent { SelectOptionScreen(subtotal = subtotal, options = flavors) }

        flavors.forEach { flavor -> //Itere a lista
            // verifique se cada item de string na lista é mostrado na tela.
            //onNodeWithText() para encontrar o texto na tela
            //assertIsDisplayed() para verificar se o texto é mostrado no app.
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(//obter uma string de recurso: Pesquise na tela o ID
                R.string.subtotal_price,//ID do recurso R.string.subtotal_price
                subtotal                //valor subtotal correto
            )
        ).assertIsDisplayed()

        //se o botão Next está desativado.
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }
}