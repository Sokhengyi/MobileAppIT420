package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.yisokheng.finalapp.R
import com.yisokheng.finalapp.modelapp.TeamMember

@Composable
fun AboutUsScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedMember by remember { mutableStateOf<TeamMember?>(null) }
    var isDarkMode by remember { mutableStateOf(false) }
    val backgroundColor = if (isDarkMode) Color(0xFF000000) else Color(0xFFF8F8F8)
    val textColor = if (isDarkMode) Color.White else Color.Black
    val grayTextColor = if (isDarkMode) Color.White else Color.Gray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(45.dp)
                .size(48.dp)
        ) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                tint = Color(0xFFEA0D81)
            )
        }

        IconButton(
            onClick = { isDarkMode = !isDarkMode },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(45.dp)
                .size(48.dp)
        ) {
            Icon(
                if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                contentDescription = null,
                tint = if (isDarkMode) Color.White else Color.Black
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "About Us",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFE91E63),
            ),
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = "Our Team Currently, we are 4th year " +
                    "1st semester students at ACLEDA University of Business," +
                    " majoring in Business IT.",
            style = TextStyle(
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                color = textColor
            ),
            modifier = Modifier.padding(top = 30.dp)
        )

        val teamMembers = listOf(
            TeamMember(
                name = "Yi Sokheng",
                description = "Software Developer",
                bio = "Yi Sokheng is a passionate software developer with expertise in Android development using Jetpack Compose.",
                imageRes = R.drawable.yisokheng
            ),
            TeamMember(
                name = "Cheam Sokkhim",
                description = "Data Scientist",
                bio = "Cheam Sokkhim specializes in data analysis and machine learning.",
                imageRes = R.drawable.cheamsokkhim
            ),
            TeamMember(
                name = "Chean Sokchem",
                description = "Project Manager",
                bio = "Chean Sokchem is skilled in project planning and team coordination.",
                imageRes = R.drawable.cheansokchem
            ),
            TeamMember(
                name = "Seb Ehouy",
                description = "Database",
                bio = "Seb Ehouy has expertise in database design and management.",
                imageRes = R.drawable.sebehouy
            )
        )

        // First Row: Yi Sokheng alone
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center // Center Yi Sokheng
        ) {
            AboutUsColumn(
                imageRes = teamMembers[0].imageRes,
                name = teamMembers[0].name,
                description = teamMembers[0].description,
                textColor = textColor,
                grayTextColor = grayTextColor,
                onClick = {
                    selectedMember = teamMembers[0]
                    showDialog = true
                }
            )
        }

        // Second Row: Remaining team members
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            teamMembers.drop(1).forEach { member ->
                AboutUsColumn(
                    imageRes = member.imageRes,
                    name = member.name,
                    description = member.description,
                    textColor = textColor,
                    grayTextColor = grayTextColor,
                    onClick = {
                        selectedMember = member
                        showDialog = true
                    }
                )
            }
        }

//        SocialMediaLinks(isDarkMode)

        if (showDialog && selectedMember != null) {
            TeamMemberDialog(
                member = selectedMember!!,
                onDismiss = { showDialog = false },
                isDarkMode = isDarkMode
            )
        }
    }
}

@Composable
fun AboutUsColumn(
    imageRes: Int,
    name: String,
    description: String,
    textColor: Color,
    grayTextColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .width(100.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Team Member",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
            contentScale = ContentScale.Crop
        )

        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = textColor
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        // Description
        Text(
            text = description,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = grayTextColor
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun TeamMemberDialog(member: TeamMember, onDismiss: () -> Unit, isDarkMode: Boolean) {
    val dialogTextColor = if (isDarkMode) Color.White else Color.Black
    val dialogBackgroundColor = if (isDarkMode) Color(0xFF121212) else Color.White

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = dialogBackgroundColor
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = member.imageRes),
                    contentDescription = "Team Member",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = member.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = dialogTextColor
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = member.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = dialogTextColor
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Team Member Bio
                Text(
                    text = member.bio,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = dialogTextColor
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Close Button
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEA0D81))
                ) {
                    Text(
                        text = "Close",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

//@Composable
//fun SocialMediaLinks(isDarkMode: Boolean) {
//    val textColor = if (isDarkMode) Color.White else Color.Black
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 30.dp),
//        verticalArrangement = Arrangement.Bottom,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Connect with Us",
//            style = TextStyle(
//                fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = textColor
//            ),
//            modifier = Modifier.padding(bottom = 10.dp)
//        )
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            // Social Media Icons
//            SocialMediaIcon(iconRes = R.drawable.icon_facebook, onClick = {})
//            SocialMediaIcon(iconRes = R.drawable.icon_messenger, onClick = {})
//            SocialMediaIcon(iconRes = R.drawable.icon_telegram, onClick = {})
//            SocialMediaIcon(iconRes = R.drawable.icon_youtube, onClick = {})
//            SocialMediaIcon(iconRes = R.drawable.icon_linkedin, onClick = {})
//        }
//    }
//}
//
//@Composable
//fun SocialMediaIcon(iconRes: Int, onClick: () -> Unit) {
//    Image(
//        painter = painterResource(id = iconRes),
//        contentDescription = "Social Media Icon",
//        modifier = Modifier
//            .size(25.dp)
//            .clickable(onClick = onClick),
//        contentScale = ContentScale.Crop
//    )
//}

