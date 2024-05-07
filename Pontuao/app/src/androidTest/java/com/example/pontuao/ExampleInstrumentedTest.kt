package com.example.pontuao

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pontuao.ui.theme.PontuaçãoTheme
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get: Rule
    val  composeTestRule = createComposeRule()
    @Test
    fun useAppContext() {
        composeTestRule.setContent {
            PontuaçãoTheme{
                LayoutInicial()
            }
        }

        //testMaisMenos
        // Clica no botão de adição duas vez
        composeTestRule.onNodeWithText("+ ").performClick()
        composeTestRule.onNodeWithText("+ ").performClick()
        //subtraia
        composeTestRule.onNodeWithText("- ").performClick()
        //verifique
        composeTestRule.onNodeWithText("1").assertExists()

        //testZeroButton
        // Clica no botão de subtração duas vezes
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("+").performClick()

        // Verifica se o placar foi dincrementado para 2
        composeTestRule.onNodeWithText("2").assertExists()

        // Clica no botão de zerar
        composeTestRule.onNodeWithText("Zerar").performClick()

        //Como existe dois zeros, ocorrera um erro, por isso a soma
        composeTestRule.onNodeWithText("+").performClick()

        // Verifica se o placar foi resetado para 0
        composeTestRule.onNodeWithText("0").assertExists()
    }

}