package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import com.yisokheng.finalapp.R

@Composable
fun ProfileScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    val bgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF4F4F4) // Dark Mode Background

    val translations = mapOf(
        "en" to mapOf(
            "Profile" to "Profile",
            "Posts" to "Posts",
            "Orders" to "Orders",
            "WishList" to "Wish List",
            "Likes" to "Likes",
            "AccountInfo" to "Account Information",
            "MyOrder" to "My Order",
            "PaymentMethod" to "Payment Method",
            "DeliveryAddress" to "Delivery Address",
            "Settings" to "Settings",
            "ContactUs" to "Contact with Us",
            "AboutUs" to "About Us"
        ),
        "kh" to mapOf(
            "Profile" to "ប្រវត្តិរូប",
            "Posts" to "ប្រកាស",
            "Orders" to "ការកម្មង់",
            "WishList" to "បញ្ជីបំណង",
            "Likes" to "ចូលចិត្ត",
            "AccountInfo" to "ព័ត៌មានគណនី",
            "MyOrder" to "ការកម្មង់របស់ខ្ញុំ",
            "PaymentMethod" to "វិធីសាស្រ្តទូទាត់",
            "DeliveryAddress" to "អាសយដ្ឋានដឹកជញ្ជូន",
            "Settings" to "ការកំណត់",
            "ContactUs" to "ទំនាក់ទំនងជាមួយយើង",
            "AboutUs" to "អំពីយើង"
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
                .background(bgColor)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE91E63), Color(0xFFE91E63))
                        ))
                            .padding(20.dp)
                     ){

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.khengorder), // Replace `logo` with your image name
                                contentDescription = "Logo",
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(3.dp, Color.White, CircleShape)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Chean Sokchem",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "@cheansokchem",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White, RoundedCornerShape(10.dp))
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                StatItem(count = "25", label = currentTranslation["Posts"]!!, textColor = textColor)
                                StatItem(count = "50", label = currentTranslation["Orders"]!!, textColor = textColor)
                                StatItem(count = "15", label = currentTranslation["WishList"]!!, textColor = textColor)
                                StatItem(count = "1.2k", label = currentTranslation["Likes"]!!, textColor = textColor)
                            }
                        }
                    }

                        // Menu Items Section
                        Spacer(modifier = Modifier.height(20.dp))
                        MenuSection(
                        items = listOf(
                            MenuItem(icon = "\uD83D\uDC64", label = currentTranslation["AccountInfo"]!!, color = Color(
                                0xFFE91E63
                            )
                            ),
                            MenuItem(icon = "\uD83D\uDECD", label = currentTranslation["MyOrder"]!!, color = Color(
                                0xFFC06886
                            )
                            ),
                            MenuItem(icon = "\uD83D\uDCB3", label = currentTranslation["PaymentMethod"]!!, color = Color(
                                0xFFC5AE66
                            )
                            ),
                            MenuItem(icon = "\uD83D\uDCCD", label = currentTranslation["DeliveryAddress"]!!, color = Color(
                                0xFF2B608A
                            )
                            )
                        ),
                textColor = textColor,
                        isDarkMode = isDarkMode
            )

            Spacer(modifier = Modifier.height(20.dp))
            MenuSection(
                items = listOf(
                    MenuItem(icon = "⚙\uFE0F", label = currentTranslation["Settings"]!!, color = Color(
                        0xFF82878C
                    )
                    ),
                    MenuItem(icon = "\uD83D\uDCAC", label = currentTranslation["ContactUs"]!!, color = Color(0xFF007BFF)),
                    MenuItem(icon = "\\ℹ\uFE0F", label = currentTranslation["AboutUs"]!!, color = Color(0xFF6C757D))
                ),
                textColor = textColor,isDarkMode = isDarkMode
            )
        }
    }
}

@Composable
fun StatItem(count: String, label: String, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = textColor
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun MenuSection(items: List<MenuItem>, textColor: Color, isDarkMode: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isDarkMode) Color(0xFF1E1E1E) else Color.White, RoundedCornerShape(10.dp))
            .padding(15.dp)
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle click */ }
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(item.color, CircleShape)
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.icon)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = item.label,
                    fontSize = 16.sp,
                    color = textColor
                )
            }
        }
    }
}

data class MenuItem(val icon: String, val label: String, val color: Color)

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}