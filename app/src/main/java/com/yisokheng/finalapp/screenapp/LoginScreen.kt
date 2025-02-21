@file:Suppress("DEPRECATION")

package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen(navController = rememberNavController())
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    var passwordError by remember { mutableStateOf(false) }

    val textColor =
        if (isDarkMode) Color.White
        else
            MaterialTheme.colorScheme.onSurface
    val secondaryTextColor =
        if (isDarkMode) Color.White.copy(alpha = 0.7f)
        else
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

    val translations = mapOf(
        "en" to mapOf(
            "signuporlogin" to "Sign up or Login",
            "selectmethod" to "Select your preferred method to continue",
            "usernameoremail" to "Enter Username or Email",
            "password" to "Enter your Password",
            "forgotpassword" to "Forgot Password?",
            "signin" to "Sign in",
            "or" to "OR",
            "continuefacebook" to "Continue with Facebook",
            "terms" to "By continuing, you agree to our Terms and Conditions and Privacy Policy"
        ),
        "kh" to mapOf(
            "signuporlogin" to "ចុះឈ្មោះឬចូល",
            "selectmethod" to "ជ្រើសរើសវិធីសាស្ត្រដែលអ្នកពេញចិត្ត ដើម្បីបន្ត",
            "usernameoremail" to "បញ្ចូលឈ្មោះអ្នកប្រើប្រាស់ ឬអ៊ីមែល",
            "password" to "បញ្ចូលពាក្យសម្ងាត់របស់អ្នក",
            "forgotpassword" to "ភ្លេចលេខសម្ងាត់?",
            "signin" to "ចូល",
            "or" to "ឬ",
            "continuefacebook" to "បន្តជាមួយហ្វេសប៊ុក",
            "terms" to "តាមរយៈការបន្ត អ្នកយល់ព្រមនឹងលក្ខខណ្ឌ និងគោលការណ៍ឯកជនភាពរបស់យើង។"
        )
    )

    val currentTranslation = translations[selectedLanguage] ?: translations["en"]!!
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = if (isDarkMode) {
                        listOf(Color(0xFF000000), Color(0xFF000000))
                    } else {
                        listOf(Color(0xFFF8F8F8), Color(0xFFF8F8F8))
                    }
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { isDarkMode = !isDarkMode },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    if (isDarkMode)
                        Icons.Default.LightMode
                    else
                        Icons.Default.DarkMode,
                    contentDescription = null,
                    tint =
                    if (isDarkMode) Color.White
                    else
                        Color.Black
                )
            }
            IconButton(
                onClick = { navController.navigate("AboutUsScreen") },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null,
                    tint = if (isDarkMode) Color.White else Color.Black
                )
            }
            Button(
                onClick = { selectedLanguage = if (selectedLanguage == "en") "kh" else "en" },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA0D81)),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier.size(width = 70.dp, height = 32.dp)
            ) {
                Text(text = if (selectedLanguage == "en") "KH" else "EN",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSecondary)
            }
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentTranslation["signuporlogin"]!!,
                color = textColor,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            Text(
                text = currentTranslation["selectmethod"]!!,
                style = MaterialTheme.typography.bodyMedium,
                color = secondaryTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 32.dp)
            )

            OutlinedTextField(
                value = usernameOrEmail,
                onValueChange = { usernameOrEmail = it },
                label = {
                    Text(currentTranslation["usernameoremail"]!!,
                    color = textColor) },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor)
            )

            Spacer(modifier = Modifier
                .height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = it
                        .isEmpty()
                },
                label = { Text(currentTranslation["password"]!!,
                    color = textColor) },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                visualTransformation =
                if (passwordVisible) VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = textColor)
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor)
            )

            Spacer(modifier = Modifier
                .height(8.dp))

            ClickableText(text = AnnotatedString(currentTranslation["forgotpassword"]!!),
                onClick = { navController.navigate("ForgotPasswordScreen") },
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 14.sp))

            Spacer(modifier = Modifier
                .height(24.dp))

//BUTTON SIGN IN TO HOME SCREEN
            Button(
                onClick = { navController.navigate("HomeScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFEA0D81))) {
                Text(text = currentTranslation["signin"]!!,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = currentTranslation["or"]!!,
                color = textColor,
                modifier = Modifier
                    .padding(vertical = 8.dp))

//BUTTON CONTINUE WITH FACEBOOK
            Button(onClick = { navController.navigate("LoginFBScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1877F2))) {
                Icon(Icons.Default.Facebook,
                    contentDescription = "Facebook",
                    modifier = Modifier.
                    size(24.dp))
                Spacer(modifier = Modifier
                    .width(8.dp))
                Text(currentTranslation["continuefacebook"]!!)
            }
            Text(text = currentTranslation["terms"]!!,
                style = MaterialTheme.typography.bodySmall,
                color = secondaryTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp))
        }
    }
