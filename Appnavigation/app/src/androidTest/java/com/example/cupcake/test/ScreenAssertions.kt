package com.example.cupcake.test

import androidx.navigation.NavController
import com.example.app_navigation.R
import junit.framework.TestCase.assertEquals

//funções auxiliar que possa ser chamada sempre que você quiser fazer essa declaração.

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
