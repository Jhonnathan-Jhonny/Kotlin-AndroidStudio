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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend.model.UserRequest
import com.example.frontend.repository.UserRepository
import com.example.frontend.ui.theme.FrontEndTheme
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

private val userRepository = UserRepository()


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("registration") { RegistrationScreen(navController) }
        composable("information") { InfoScreen(navController) }
    }
}

@Composable
fun InfoScreen(
    navController: NavController? = null
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var user by remember { mutableStateOf(UserRequest("", "", "")) }
    var usingEdit by remember { mutableStateOf(false) }

    // Temporary state variables for text fields
    var tempName by remember { mutableStateOf("") }
    var tempEmail by remember { mutableStateOf("") }

    // Track the previous name
    var namePrevious by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val userInfo = userRepository.getUserInfo()
                user = userInfo
                tempName = userInfo.name
                tempEmail = userInfo.email
                namePrevious = userInfo.name
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to load user info: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    if (usingEdit) {
        Column {
            OutlinedTextField(
                value = tempName,
                onValueChange = { tempName = it },
                label = { Text(text = "Name") }
            )
            OutlinedTextField(
                value = tempEmail,
                onValueChange = { tempEmail = it },
                label = { Text(text = "Email") }
            )
            Button(onClick = {
                handleEdit(coroutineScope, context, tempName, tempEmail, namePrevious) { result ->
                    result.onSuccess {
                        // Update the user state and reset the temporary state
                        user = user.copy(name = tempName, email = tempEmail)
                        usingEdit = false
                    }.onFailure {
                        Toast.makeText(context, "Edition failed: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text(text = "Confirm")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Name: ${user.name}")
            Text(text = "Email: ${user.email}")
            Text(
                text = "Password: ${user.password}",
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Row {
                Button(onClick = {
                    handleDelete(coroutineScope, context, user.name, navController)
                    navController?.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }) {
                    Text(text = "Delete")
                }

                Button(onClick = { usingEdit = true }) {
                    Text(text = "Edit")
                }

                Button(onClick = {
                    navController?.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }) {
                    Text(text = "Log Out")
                }
            }
        }
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
                    navController?.navigate("information")
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

private fun handleDelete(
    scope: CoroutineScope,
    context: Context,
    name: String,
    navController: NavController?
) {
    scope.launch {
        try {
            val response = userRepository.deleteUser(name)
            withContext(Dispatchers.Main) {
                if (response == HttpStatusCode.OK) {
                    navController?.navigate("login")
                    Toast.makeText(context, "Delete successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Delete failed:", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Delete failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

private fun handleEdit(
    scope: CoroutineScope,
    context: Context,
    name: String,
    email: String,
    namePrevious: String,
    callback: (Result<HttpStatusCode>) -> Unit
) {
    scope.launch {
        try {
            val response = userRepository.editUser(namePrevious, name, email)
            withContext(Dispatchers.Main) {
                if (response == HttpStatusCode.OK) {
                    Toast.makeText(context, "Edition successful!", Toast.LENGTH_SHORT).show()
                    callback(Result.success(response))
                } else {
                    callback(Result.failure(Exception("Edition failed: Name exist")))
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Edition failed: ${e.message}", Toast.LENGTH_SHORT).show()
                callback(Result.failure(e))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontEndTheme {
        LoginScreen()
        //InfoScreen()
        //RegistrationScreen()
    }
}