package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    //Verificar existencia de um item mesmo quando a tela muda
    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        // Set up medium window
        composeTestRule.setContent {
            ReplyApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
        // Navigation rail is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    //Testa mudanças de configs. com o stateRestorationTester:
    //        Ex: Retrato -> Paisagem
    // Verifica se um e-mail ainda está selecionado na tela compacta após uma mudança de configuração.
    @Test
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        // Setup compact window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        // Given third email is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertIsDisplayed()

        // Open detailed page
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
        ).performClick()

        // Verify that it shows the detailed screen for the correct email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()

        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()

        // Verify that it still shows the detailed screen for the same email
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.navigation_back
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        ).assertExists()
    }


}