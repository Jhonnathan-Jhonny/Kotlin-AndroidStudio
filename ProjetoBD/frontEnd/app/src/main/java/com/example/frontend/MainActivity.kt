package com.example.frontend

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.frontend.repository.RegistrationResponse
import com.example.frontend.repository.User
import com.example.frontend.repository.UserRepository
import com.example.frontend.ui.theme.FrontEndTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.io.path.ExperimentalPathApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

private val userRepository = UserRepository()


//@Composable
//fun MyApp() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") { LoginScreen(navController) }
//        composable("registration") { RegistrationScreen(navController) }
//        composable("information/{name}/{email}/{password}") { InfoScreean(navController,"","","") }
//    }
//}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val infoArgs = rememberSaveable {
        mutableStateOf<InfoArgs?>(null)
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("registration") { RegistrationScreen(navController) }
        composable(
            "information/{name}/{email}/{password}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            infoArgs.value = InfoArgs(name, email, password)
            InfoScreean(navController, name, email, password)
        }
    }

    // Navegação para a tela de informações quando o usuário efetua login
    LaunchedEffect(key1 = currentRoute) {
        if (currentRoute == "information/{name}/{email}/{password}") {
            val name = infoArgs.value?.name ?: ""
            val email = infoArgs.value?.email ?: ""
            val password = infoArgs.value?.password ?: ""
            navController.navigate("information/$name/$email/$password")
        }
    }
}

data class InfoArgs(val name: String, val email: String, val password: String)

@Composable
fun InfoScreean(
    navController: NavController? = null,
    name: String,
    email: String,
    password: String
) {
    Column {
        Text(text = "Name: $name")
        Text(text = "Email: $email")
        Text(text = "Password: $password")
    }
}

@Composable
fun LoginScreen(navController: NavController? = null){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = { Text("password") },
            modifier = Modifier.fillMaxWidth())
        Row {
            Button(onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    handleLogin(coroutineScope,context,email,password,navController)
                }
            },
                Modifier.padding(10.dp)) {
                Text(text = "Login")
            }
            Button(onClick = { navController!!.navigate("registration") },
                Modifier.padding(10.dp)) {
                Text(text = "Cadastrar-se")
            }
        }

    }
}
@Composable
fun RegistrationScreen(navController: NavController? = null) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //1 -> null
    // 2 -> true
    // 3-> false
    var erro by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    erro = 3
                    handleRegistration(coroutineScope, context, name, email, password, navController)
                } else {
                    erro = 2
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        if (erro == 2) {
            Text("Nome de usuário já existe ou todos os campos devem ser preenchidos.")
        }
    }
}

private fun handleLogin(
    scope: CoroutineScope,
    context: Context,
    email: String,
    password: String,
    navController: NavController?
) {
    scope.launch {
        try {
            val response = userRepository.loginUser(email, password)
            withContext(Dispatchers.Main) {
                if (response.status) {
                    navController?.navigate("information/${response.user?.name}/${response.user?.email}/${response.user?.password}")
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Login failed: Invalid credentials.", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}




private fun handleRegistration(
    scope: CoroutineScope,
    context: Context,
    name: String,
    email: String,
    password: String,
    navController: NavController?
) {
    scope.launch {
        try {
            val response = userRepository.registerUser(name, email, password)
            withContext(Dispatchers.Main) {
                if (response.status) {
                    navController?.navigate("login")
                    Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Registration failed: ${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Registration failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontEndTheme {
        //LoginScreen()
        InfoScreean(name = "", email = "", password = "")
        //RegistrationScreen()
    }
}