import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yisokheng.finalapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(4000)
        navController.navigate("LoginScreen") {
            popUpTo("SplashLoad") { inclusive = true } // Remove SplashScreen from backstack
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = if (isDarkMode) {
                        listOf(Color(0xFF020202), Color(0xFF020202))
                    } else {
                        listOf(Color(0xFFEA0D81), Color(0xFFEA0D81))
                    }
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pandalogo), // Replace with your logo
                contentDescription = "Splash Logo",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "foodpanda",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

