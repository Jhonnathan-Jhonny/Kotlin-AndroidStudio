package com.example.pontuao

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.pontuao.ui.theme.PontuaçãoTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
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
        // Clica no botão de adição uma vez
        composeTestRule.onNodeWithText(" +").performClick()
        composeTestRule.onNodeWithText(" +").performClick()
        composeTestRule.onNodeWithText(" -").performClick()
        composeTestRule.onNodeWithText("1").assertExists()
    }

}