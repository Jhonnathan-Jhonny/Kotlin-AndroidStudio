package com.example.frontend

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.verdePrincipal))
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.registration_image),
            contentDescription = "Image",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Name: ${user.name}",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp)
                .padding(top = 30.dp)
        )
        Text(
            text = "Email: ${user.email}",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top= 10.dp)
        )
        Text(
            text = "Password: ${user.password}",
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, bottom = 10.dp, top= 10.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    handleDelete(coroutineScope, context, user.name, navController)
                    navController?.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                Modifier
                    .padding(bottom = 30.dp)
                    .size(200.dp, 50.dp),
                colors = ButtonDefaults.run { buttonColors(Color(R.color.verdeSecundário)) }

            ) {
                Text(text = "Delete",
                    fontSize = 20.sp
                )
            }

            Button(
                onClick = { usingEdit = true },
                Modifier
                    .padding(bottom = 30.dp)
                    .size(200.dp, 50.dp),
                colors = ButtonDefaults.run { buttonColors(Color(R.color.verdeSecundário)) }
            ) {
                Text(text = "Edit",
                fontSize = 20.sp)

            }

            Button(
                onClick = {
                    navController?.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                Modifier
                    .padding(bottom = 30.dp)
                    .size(200.dp, 50.dp),
                colors = ButtonDefaults.run { buttonColors(Color(R.color.verdeSecundário)) }
            ) {
                Text(text = "Log Out",
                fontSize = 20.sp)

            }
        }

    }
}

@Composable
fun LoginScreen(navController: NavController? = null) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.verdePrincipal))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .align(Alignment.Start)
        ) {
            Text(
                text = "Welcome",
                fontSize = 30.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.imagemlogin),
                contentDescription = "Image",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.End)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_email_24),
                        contentDescription = "Icon email"
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_outline_24),
                        contentDescription = "Icon password"
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.eye else R.drawable.eye_visible_off
                            ),
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                )
            )
        }

        Divider(
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(10.dp, 0.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        handleLogin(coroutineScope, context, email, password, navController)
                    }
                },
                Modifier
                    .padding(bottom = 30.dp)
                    .size(200.dp, 50.dp),
                colors = ButtonDefaults.run { buttonColors(Color(R.color.verdeSecundário)) }
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp
                )
            }

            Text(
                text = "OR",
                fontWeight = FontWeight.W500,
                fontSize = 20.sp
            )

            Button(
                onClick = { navController!!.navigate("registration") },
                Modifier
                    .padding(10.dp)
                    .size(250.dp, 100.dp)
                    .padding(20.dp, 0.dp, 20.dp, 20.dp),
                colors = ButtonDefaults.run { buttonColors(Color.Transparent) },
            ) {
                Text(
                    text = "Cadastrar-se",
                    color = Color.Black,
                    fontSize = 20.sp
                )
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
    var erro by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.verdePrincipal))
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.registration_image),
            contentDescription = "Image",
            modifier = Modifier.size(200.dp)
        )

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
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.run { buttonColors(Color(R.color.verdeSecundário)) },

            ) {
            Text("Register",
                fontSize = 20.sp,
            )
        }

        if (erro == 2) {
            Text("Nome de usuário já existe ou todos os campos devem ser preenchidos.")
        }
        Button(
            onClick = {
                navController?.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            },
            Modifier
                .padding(bottom = 30.dp,top=20.dp)
                .size(110.dp, 50.dp)
                .align(Alignment.Start),
            colors = ButtonDefaults.run { buttonColors(Color.Transparent) }
        ) {
            Text(text = "Voltar",
                fontSize = 20.sp
            )

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
        //LoginScreen()
        //InfoScreen()
        RegistrationScreen()
    }
}