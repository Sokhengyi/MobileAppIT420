package com.yisokheng.finalapp.screenapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yisokheng.finalapp.modelapp.CartItem
import com.yisokheng.finalapp.modelapp.CartVM
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    fullName: String,
    tel: String,
    address: String,
    totalPrice: Float,
    viewModel: CartVM = viewModel()
) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFFFFFFF) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(
            "Payment" to "Payment",
            "UserInfo" to "User Information",
            "FullName" to "Full Name",
            "Telephone" to "Telephone",
            "Address" to "Address",
            "CartItems" to "Cart Items",
            "TotalPrice" to "Total Price",
            "ConfirmPayment" to "Confirm Payment",
            "Back" to "Back"
        ),
        "kh" to mapOf(
            "Payment" to "ការទូទាត់",
            "UserInfo" to "ព័ត៌មានអ្នកប្រើ",
            "FullName" to "ឈ្មោះពេញ",
            "Telephone" to "លេខទូរស័ព្ទ",
            "Address" to "អាសយដ្ឋាន",
            "CartItems" to "ធុងទំនិញ",
            "TotalPrice" to "តម្លៃសរុប",
            "ConfirmPayment" to "បញ្ជាក់ការទូទាត់",
            "Back" to "ត្រឡប់ក្រោយ"
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
        },
                bottomBar = { Footer(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor) // Apply dark mode background
                .padding(paddingValues)
        ) {
            val cartItems = viewModel.cartItems.collectAsState()

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = currentTranslation["Back"],
                        tint = textColor
                    )
                }
                Text(
                    text = currentTranslation["Payment"]!!,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // User Information
            Text(
                text = currentTranslation["UserInfo"]!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFFEE2A6A), shape = RoundedCornerShape(10.dp)) // Orange background
                    .border(2.dp, Color(0xFFE91E63), RoundedCornerShape(10.dp)) // Slightly darker border
                    .padding(16.dp) // Padding inside the box
            ) {
                Column {
                    Text(
                        text = "${currentTranslation["FullName"]!!}: $fullName",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White // White text for contrast
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Space between text items
                    Text(
                        text = "${currentTranslation["Telephone"]!!}: $tel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${currentTranslation["Address"]!!}: $address",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Cart Items
            Text(
                text = currentTranslation["CartItems"]!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems.value) { item ->
                    PaymentCartItemView(item, isDarkMode, textColor)
                }
            }

            // Total Price
            Text(
                text = "${currentTranslation["TotalPrice"]!!}: $${"%.2f".format(totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Confirm Payment Button
            Button(
                onClick = {
                    navController.navigate("PaymentMethodScreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE91E63),
                    contentColor = Color.White
                )
            ) {
                Text(currentTranslation["ConfirmPayment"]!!)
            }
        }
    }
}

@Composable
fun PaymentCartItemView(item: CartItem, isDarkMode: Boolean, textColor: Color) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = if (isDarkMode) Color(0xFF1E1E1E) else Color(0xFFD097AA))
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(18.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, fontWeight = FontWeight.Bold, color = textColor)
            Text(text = "$${"%.2f".format(item.price)}", color = Color(0xFFE91E63))
            Text(text = "Quantity: ${item.quantity}", fontSize = 14.sp, color = textColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    val fakeNavController = rememberNavController()
    val fakeViewModel = CartVM() // Assuming CartViewModel has a default constructor
    PaymentScreen(
        navController = fakeNavController,
        fullName = "Sokheng Yi",
        tel = "1234567890",
        address = "123 Main St",
        totalPrice = 100.0f, // Dummy value
        viewModel = fakeViewModel
    )
}
