package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController, totalPrice: Float) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(
            "EnterYourInfo" to "Enter your Personal Information",
            "FullName" to "Full Name",
            "Telephone" to "Telephone",
            "Address" to "Address",
            "TotalPrice" to "Total Price",
            "ProceedToPayment" to "Proceed to Payment",
            "Back" to "Back",
            "FullNameError" to "Full Name is required",
            "TelephoneError" to "Telephone is required",
            "InvalidTelephone" to "Invalid telephone number",
            "AddressError" to "Address is required"
        ),
        "kh" to mapOf(
            "EnterYourInfo" to "បញ្ចូលព័ត៌មានផ្ទាល់ខ្លួនរបស់អ្នក",
            "FullName" to "ឈ្មោះពេញ",
            "Telephone" to "លេខទូរស័ព្ទ",
            "Address" to "អាសយដ្ឋាន",
            "TotalPrice" to "តម្លៃសរុប",
            "ProceedToPayment" to "បន្តទៅការទូទាត់",
            "Back" to "ត្រឡប់ក្រោយ",
            "FullNameError" to "ឈ្មោះពេញត្រូវបានទាមទារ",
            "TelephoneError" to "លេខទូរស័ព្ទត្រូវបានទាមទារ",
            "InvalidTelephone" to "លេខទូរស័ព្ទមិនត្រឹមត្រូវ",
            "AddressError" to "អាសយដ្ឋានត្រូវបានទាមទារ"
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(color = bgColor), // Dark mode applied
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor) // Apply dark mode background
                .padding(paddingValues)
        ) {
            // State for input fields
            val fullName = remember { mutableStateOf("") }
            val tel = remember { mutableStateOf("") }
            val address = remember { mutableStateOf("") }

            // State for error messages
            val fullNameError = remember { mutableStateOf("") }
            val telError = remember { mutableStateOf("") }
            val addressError = remember { mutableStateOf("") }

            // Validation function
            fun validateInputs(): Boolean {
                var isValid = true

                // Full Name validation
                if (fullName.value.isEmpty()) {
                    fullNameError.value = currentTranslation["FullNameError"]!!
                    isValid = false
                } else {
                    fullNameError.value = ""
                }

                // Telephone validation
                if (tel.value.isEmpty()) {
                    telError.value = currentTranslation["TelephoneError"]!!
                    isValid = false
                } else if (tel.value.length < 10 || !tel.value.matches(Regex("^[0-9]*$"))) {
                    telError.value = currentTranslation["InvalidTelephone"]!!
                    isValid = false
                } else {
                    telError.value = ""
                }

                // Address validation
                if (address.value.isEmpty()) {
                    addressError.value = currentTranslation["AddressError"]!!
                    isValid = false
                } else {
                    addressError.value = ""
                }

                return isValid
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Back button
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = currentTranslation["Back"],
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { navController.popBackStack() }
                    .width(40.dp)
                    .height(35.dp)
                    .align(Alignment.Start),
                tint = textColor
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Title
            Text(
                text = currentTranslation["EnterYourInfo"]!!,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Full Name field
            OutlinedTextField(
                value = fullName.value,
                onValueChange = { fullName.value = it },
                label = { Text(currentTranslation["FullName"]!!, color = textColor) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFE91E63),
                    unfocusedBorderColor = Color.Gray,
                   unfocusedTextColor = textColor,
                    focusedTextColor = textColor,
                ),
                isError = fullNameError.value.isNotEmpty()
            )
            if (fullNameError.value.isNotEmpty()) {
                Text(
                    text = fullNameError.value,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Telephone field
            OutlinedTextField(
                value = tel.value,
                onValueChange = { tel.value = it },
                label = { Text(currentTranslation["Telephone"]!!, color = textColor) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFE91E63),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedTextColor = textColor,
                    focusedTextColor = textColor,
                ),
                isError = telError.value.isNotEmpty()
            )
            if (telError.value.isNotEmpty()) {
                Text(
                    text = telError.value,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Address field
            OutlinedTextField(
                value = address.value,
                onValueChange = { address.value = it },
                label = { Text(currentTranslation["Address"]!!, color = textColor) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFE91E63),
                    unfocusedBorderColor = Color.Gray,
                    unfocusedTextColor = textColor,
                    focusedTextColor = textColor,
                ),
                isError = addressError.value.isNotEmpty()
            )
            if (addressError.value.isNotEmpty()) {
                Text(
                    text = addressError.value,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Display total price
            Text(
                text = "${currentTranslation["TotalPrice"]!!}: $${"%.2f".format(totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            // Proceed to Payment button
            Button(
                onClick = {
                    if (validateInputs()) {
                        // Extract the actual values from MutableState
                        val fullNameValue = fullName.value
                        val telValue = tel.value
                        val addressValue = address.value

                        // Pass the extracted values to PaymentScreen
                        navController.navigate("paymentScreen/$fullNameValue/$telValue/$addressValue/$totalPrice")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
            ) {
                Text(text = currentTranslation["ProceedToPayment"]!!, color = Color.White)
            }
        }
    }
}