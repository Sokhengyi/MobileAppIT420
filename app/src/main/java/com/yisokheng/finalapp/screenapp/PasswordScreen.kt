package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun PreviewPasswordScreen() {
    PasswordScreen(navController = rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val username = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var confirmPassword = remember { mutableStateOf("") }
    var passwordError = remember { mutableStateOf("") }
    var confirmPasswordError = remember { mutableStateOf("") }
    val minPasswordLength = 6

    val translations = mapOf(
        "en" to mapOf(
            "signUp" to "Sign Up",
            "enterPhone" to "Enter your Password.",
            "enterUsername" to "Enter Username",
            "enterPhoneNumber" to "Enter Phone Number",
            "enterPassword" to "Enter Password",
            "confirmPassword" to "Confirm Password",
            "terms" to "By signing up, you agree to our Terms of Service Policy.",
            "next" to "Next",
            "back" to "Back"
        ),
        "kh" to mapOf(
            "signUp" to "ចុះឈ្មោះ",
            "enterPhone" to "បញ្ចូលពាក្យសម្ងាត់.",
            "enterUsername" to "បញ្ចូលឈ្មោះអ្នកប្រើ",
            "enterPhoneNumber" to "បញ្ចូលលេខទូរស័ព្ទ",
            "enterPassword" to "បញ្ចូលពាក្យសម្ងាត់",
            "confirmPassword" to "បញ្ជាក់ពាក្យសម្ងាត់",
            "terms" to "ដោយចុះឈ្មោះ អ្នកយល់ព្រមនឹងគោលការណ៍សេវាកម្មរបស់យើង.",
            "next" to "បន្ទាប់",
            "back" to "ថយក្រោយ"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    MaterialTheme(
        colorScheme = if (isDarkMode) MaterialTheme.colorScheme.copy(
            background = Color(0xFF121212),
            onBackground = Color.White,
            surface = Color(0xFF1E1E1E),
            onSurface = Color.White
        ) else MaterialTheme.colorScheme.copy(
            background = Color(0xFFF5F5F5),
            onBackground = Color.Black,
            surface = Color.White,
            onSurface = Color.Black
        )
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
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
                                if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = null,
                                tint = if (isDarkMode) Color.White else Color.Black
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
                            Text(text = if (selectedLanguage == "en") "KH" else "EN", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSecondary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Text(currentTranslation["signUp"]!!, fontSize = 42.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    currentTranslation["enterPhone"]!!,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text(currentTranslation["enterPassword"]!!, fontSize = 14.sp) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    isError = passwordError.value.isNotEmpty()
                )
                if (passwordError.value.isNotEmpty()) {
                    Text(text = passwordError.value, color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    label = { Text(currentTranslation["confirmPassword"]!!, fontSize = 14.sp) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    isError = confirmPasswordError.value.isNotEmpty()
                )
                if (confirmPasswordError.value.isNotEmpty()) {
                    Text(text = confirmPasswordError.value, color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(currentTranslation["terms"]!!, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(100.dp))
                Button(
                    onClick = {
                        // Clear previous errors
                        passwordError.value = ""
                        confirmPasswordError.value = ""

                        // Validate password length
                        if (password.value.length < minPasswordLength) {
                            passwordError.value =
                                currentTranslation["passwordTooShort"] ?: "Password too short"
                            return@Button // Prevent navigation if validation fails
                        }

                        // Validate password match
                        if (password.value != confirmPassword.value) {
                            confirmPasswordError.value =
                                currentTranslation["passwordMismatch"] ?: "Passwords do not match"
                            return@Button // Prevent navigation if validation fails
                        }

                        // If validation passes, navigate to the OTP screen
                        navController.navigate("OTPScreen")


                    },
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(currentTranslation["next"]!!, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}
