package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.app_navigation.R
import com.example.app_navigation.data.OrderUiState
import com.example.app_navigation.ui.OrderSummaryScreen
import org.junit.Rule
import org.junit.Test

class CupcakeSummaryScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun summaryScreen_verifyContent() {
        val quantity = 1
        val flavor = "Vanilla"
        val date = "Wed Jul 21"
        val subtotal = "$100"

        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = OrderUiState(
                    quantity = quantity,
                    flavor = flavor,
                    date = date,
                    price = subtotal
                ),
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> }
            )
        }

        // Verificar a quantidade
        val expectedQuantityText = composeTestRule.activity.resources.getQuantityString(
            R.plurals.cupcakes,
            quantity,
            quantity
        )
        composeTestRule.onNodeWithText(expectedQuantityText).assertExists()

        // Verificar o sabor
        composeTestRule.onNodeWithText(flavor).assertExists()

        // Verificar a data
        composeTestRule.onNodeWithText(date).assertExists()

        // Verificar o subtotal
        val expectedSubtotalText = composeTestRule.activity.getString(
            R.string.subtotal_price, subtotal
        )
        composeTestRule.onNodeWithText(expectedSubtotalText).assertExists()

        // Verificar os botões
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.send))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.cancel))
            .assertIsDisplayed()
    }

}