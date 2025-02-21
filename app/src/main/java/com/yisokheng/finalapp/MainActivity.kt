package com.yisokheng.finalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import SplashScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.yisokheng.finalapp.screenapp.PasswordScreen
import com.yisokheng.finalapp.screenapp.PaymentMethodScreen
import com.yisokheng.finalapp.screenapp.AboutUsScreen
import com.yisokheng.finalapp.screenapp.CartScreen
import com.yisokheng.finalapp.screenapp.Footer
import com.yisokheng.finalapp.screenapp.HomeScreen
import com.yisokheng.finalapp.screenapp.InfoScreen
import com.yisokheng.finalapp.screenapp.LoginFBScreen
import com.yisokheng.finalapp.screenapp.LoginScreen
import com.yisokheng.finalapp.screenapp.OTPScreen
import com.yisokheng.finalapp.screenapp.PaymentScreen
import com.yisokheng.finalapp.screenapp.ProfileScreen
import com.yisokheng.finalapp.screenapp.RestaurantProfileScreen
import com.yisokheng.finalapp.screenapp.RestaurantScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen() {
    val navController = rememberNavController() // Create NavController once
    AppNavigation(navController)
}

@Composable
fun AppNavigation(navController: NavController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SplashLoad") {
        composable("SplashLoad") { SplashScreen(navController) }
        composable("LoginScreen") { LoginScreen(navController) }
        composable("AboutUsScreen") { AboutUsScreen(navController) }
        composable("LoginFBScreen") { LoginFBScreen(navController) }
        composable("PasswordScreen") { PasswordScreen(navController) }
        composable("OTPScreen") { OTPScreen(navController) }
        composable("HomeScreen") { HomeScreen(navController) }
        composable("cartScreen") { CartScreen(navController) }
        composable("restaurantScreen") { RestaurantScreen(navController) }
        composable(
            "restaurantProfile/{name}/{rating}/{deliveryTime}/{cuisine}/{imageRes}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("rating") { type = NavType.StringType },
                navArgument("deliveryTime") { type = NavType.StringType },
                navArgument("cuisine") { type = NavType.StringType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val rating = backStackEntry.arguments?.getString("rating") ?: ""
            val deliveryTime = backStackEntry.arguments?.getString("deliveryTime") ?: ""
            val cuisine = backStackEntry.arguments?.getString("cuisine") ?: ""
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.pandalogo

            RestaurantProfileScreen(navController, name, rating, deliveryTime, cuisine, imageRes)
        }

        composable("ProfileScreen") { ProfileScreen(navController) }

        composable("PaymentMethodScreen") { PaymentMethodScreen(navController) }
        composable(
            route = "infoScreen/{totalPrice}",
            arguments = listOf(
                navArgument("totalPrice") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            // Retrieve totalPrice from arguments
            val totalPrice = backStackEntry.arguments?.getFloat("totalPrice") ?: 0f
            InfoScreen(navController, totalPrice)
        }
        composable("Footer") { Footer(navController) }
        composable(
            route = "paymentScreen/{fullName}/{tel}/{address}/{totalPrice}",
            arguments = listOf(
                navArgument("fullName") { type = NavType.StringType },
                navArgument("tel") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType },
                navArgument("totalPrice") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName") ?: ""
            val tel = backStackEntry.arguments?.getString("tel") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""
            val totalPrice = backStackEntry.arguments?.getFloat("totalPrice") ?: 0f
            PaymentScreen(
                navController = navController,
                fullName = fullName,
                tel = tel,
                address = address,
                totalPrice = totalPrice
            )
        }
    }
}




