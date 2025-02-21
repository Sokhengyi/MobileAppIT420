package com.yisokheng.finalapp.screenapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.yisokheng.finalapp.R
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.darkColors
import androidx.compose.material.*
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.lightColors
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import com.yisokheng.finalapp.modelapp.Brand

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}
@Composable
fun HomeScreen(navController: NavController) {
    var isDarkMode by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("en") }
    val textColor = if (isDarkMode) Color.White else Color.Black
    var searchText by remember { mutableStateOf("") }

    // Filter the food list based on search text
    val filteredFoodList = foodList.filter { food ->
        food.name.contains(searchText, ignoreCase = true) ||
                food.description.contains(searchText, ignoreCase = true)
    }

    val translations = mapOf(
        "en" to mapOf(
            "deliveringTo" to "Delivering To",
            "pnompenh" to "Pnom Penh",
            "orderNow" to "Order Now",
            "searchBox" to "Search restaurants and groceries",
            "categoriestitle" to "Shop Categories",
            "nearby" to "Nearby Restaurants",
            "popRes" to "Popular Restaurants",
            "newArrivals" to "New arrivals up to 50% off",
            "topShops" to " Top Shops",
            "topBrands" to " Top Brands",
            "foodMenu" to "Food Menu",
            "price" to "Price",
        ),
        "kh" to mapOf(
            "deliveringTo" to "កំពុងដឹកជញ្ជូនទៅ",
            "pnompenh" to "ភ្នំពេញ",
            "orderNow" to "កម៉្មង់ឥឡូវនេះ",
            "searchBox" to "ស្វែងរកអ្វីៗ...",
            "categoriestitle" to "ប្រភេទម្ហូប",
            "nearbyHeader" to "ភោជនីយដ្ឋានដែលនៅជិត",
            "popRes" to "ភោជនីយដ្ឋានពេញនិយម",
            "newArrivals" to "មកដល់ថ្មី បញ្ចុះតម្លៃរហូតដល់ 50%",
            "topShops" to "Top ហាង ",
            "topBrands" to "Top ប្រេន ",
            "foodMenu" to "មីនុយម្ហូប",
            "price" to "តម្លៃ",
        )
    )

    val currentTranslation = translations[selectedLanguage]!!

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .background(if (isDarkMode) Color.DarkGray else Color.White),
                contentAlignment = Alignment.Center
            ) {
                Spacer(modifier = Modifier.height(30.dp))
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
        MaterialTheme(
            colors = if (isDarkMode) darkColors() else lightColors()
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(if (isDarkMode) Color.Black else Color.White)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Delivering To and Hotel Name
                    item {
                        Text(
                            text = currentTranslation["deliveringTo"]!!,
                            color = Color(0xFFE91E63),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Current Location",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                text = currentTranslation["pnompenh"]!!,
                                color = Color(0xFF6E6368),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }

                    // Search Box
                    item {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            placeholder = {
                                Text(
                                    text = currentTranslation["searchBox"] ?: "",
                                    color = Color.Gray,
                                )
                            },
                            textStyle = TextStyle(
                                color = Color(0x88000000),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .border(1.dp, Color.LightGray, RoundedCornerShape(50.dp))
                                .background(Color.White)
                        )
                    }

                    // Carousel
                    item {
                        Carousel()
                    }

                    // Order Now Button
                    item {
                        Button(
                            onClick = { /* Handle order now action */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE91E63), // Updated property
                                contentColor = Color.White // Ensures text is readable
                            ),
                            elevation = ButtonDefaults.buttonElevation( // Updated method
                                defaultElevation = 4.dp,
                                pressedElevation = 8.dp
                            )
                        ) {
                            Text(
                                text = currentTranslation["orderNow"] ?: "Order Now", // Safer null handling
                                fontSize = 17.sp
                            )
                        }
                    }

                    item {
                        Text(
                            text = currentTranslation["topShops"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        TopShopsSection(isDarkMode = isDarkMode)
                    }
                    // Categories Section
                    item {
                        Text(
                            text = currentTranslation["categoriestitle"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        CategoriesSection(isDarkMode = isDarkMode)
                    }

                    // Top Shops Section


                    // Top Brands Section
                    item {
                        Text(
                            text = currentTranslation["topBrands"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        TopBrandsSection(isDarkMode = isDarkMode)
                    }

                    // Popular Restaurants Section
                    item {
                        Text(
                            text = currentTranslation["popRes"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        RestaurantScroll()
                    }

                    // New Arrivals Section
                    item {
                        Text(
                            text = currentTranslation["newArrivals"]!!,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        NewArrivalScroll()
                    }

                    // Food List Section
                    item {
                        Text(
                            text = currentTranslation["foodMenu"] ?: "Food Menu",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    itemsIndexed(filteredFoodList) { index, food ->
                        FoodCard(navController = navController, food = food, isDarkMode = isDarkMode, textColor = textColor)
                    }
                }
            }
        }
    }
}


@Composable
fun Carousel() {
    val images = listOf(
        com.yisokheng.finalapp.R.drawable.banner,
        com.yisokheng.finalapp.R.drawable.banner1,
        com.yisokheng.finalapp.R.drawable.banner2
    )
    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // HorizontalPager for images
        com.google.accompanist.pager.HorizontalPager(
            count = images.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) { page ->
            Image(
                painter = rememberImagePainter(data = images[page]),
                contentDescription = "Sale Banner",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Custom Pagination Indicators
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(images.size) { index ->
                val isActive = index == pagerState.currentPage
                val color = if (isActive) Color(0xE9, 0x1E, 0x63, 0xFF) // RGB for active (purple)
                else Color(0xCC, 0xCC, 0xCC) // RGB for inactive (light gray)

                Box(
                    modifier = Modifier
                        .width(if (isActive) 32.dp else 12.dp) // Larger size for active dot
                        .height(12.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun TopShopsSection(isDarkMode: Boolean) {
    val topShops = listOf(
        Shop(com.yisokheng.finalapp.R.drawable.aeon, "Aeon MaxValu Supermarket", "15-30 min"),
        Shop(com.yisokheng.finalapp.R.drawable.kground, "K Ground Mini TK", "30-45 min"),
        Shop(com.yisokheng.finalapp.R.drawable.lucky, "Lucky Supermarket", "25-40 min"),
        Shop(com.yisokheng.finalapp.R.drawable.chipmong, "Chip Mong Supermarket", "25 min"),
        Shop(com.yisokheng.finalapp.R.drawable.bigc, "Big C Mini", "15 min"),
        Shop(com.yisokheng.finalapp.R.drawable.boncafe, "BONCAFE", "20 min"),
        Shop(com.yisokheng.finalapp.R.drawable.guadian, "Guardian", "10 min"),
        Shop(com.yisokheng.finalapp.R.drawable.gtv, "Green Town Vegetable", "25 min")
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(topShops.size) { index ->
            TopShopItem(shop = topShops[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun TopShopItem(shop: Shop, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .width(120.dp)
    ) {
        Image(
            painter = painterResource(id = shop.imageResId),
            contentDescription = shop.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = shop.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = shop.distance,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 5.dp)
        )
    }
}

data class Shop(val imageResId: Int, val name: String, val distance: String)

@Composable
fun CategoriesSection(isDarkMode: Boolean) {
    val categories = listOf(
        Category(com.yisokheng.finalapp.R.drawable.groceries, "Groceries"),
        Category(com.yisokheng.finalapp.R.drawable.convenience, "Convenience"),
        Category(com.yisokheng.finalapp.R.drawable.beverages, "Beverages"),
        Category(com.yisokheng.finalapp.R.drawable.electronics, "Electronics"),
        Category(com.yisokheng.finalapp.R.drawable.fruits, "Fruits"),
        Category(com.yisokheng.finalapp.R.drawable.sports, "Sports"),
        Category(com.yisokheng.finalapp.R.drawable.flowers, "Flowers"),
        Category(com.yisokheng.finalapp.R.drawable.valentine, "Valentine's Collection"),

    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories.size) { index ->
            CategoryItem(category = categories[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun CategoryItem(category: Category, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(id = category.imageResId),
            contentDescription = category.name,
            modifier = Modifier
                .size(55.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = category.name, fontSize = 17.sp, fontWeight = FontWeight.Bold, color = textColor, modifier = Modifier.padding(vertical = 10.dp))
    }
}

data class Category(val imageResId: Int, val name: String)


@Composable
fun RestaurantScroll() {

    // Restaurant Cards Horizontal Scroll
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        RestaurantCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.pizzacompany),
            name = "Pizza Company",
            cuisine = "Italian, Fast Food",
            rating = "⭐ 4.5"
        )
        RestaurantCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.bk),
            name = "Burger King",
            cuisine = "American, Fast Food",
            rating = "⭐ 4.2"
        )
        RestaurantCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.sushires),
            name = "Sushi Spot",
            cuisine = "Japanese, Seafood",
            rating = "⭐ 4.7"
        )
        RestaurantCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.pastacornerres),
            name = "Pasta Corner",
            cuisine = "Italian, Vegetarian",
            rating = "⭐ 4.3"
        )
        RestaurantCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.streakhouseres),
            name = "306 Wagyu Steakhouse",
            cuisine = "Grill, BBQ",
            rating = "⭐ 4.6"
        )
    }
}

@Composable
fun RestaurantCard(imagePainter: Painter, name: String, cuisine: String, rating: String) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = cuisine,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = rating,
            fontSize = 14.sp,
            color = Color(0xFFE91E63),
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun NewArrivalScroll() {
    // New Arrival Cards Horizontal Scroll
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        NewArrivalCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.pizzafood),
            name = "Pizza Company",
            category = "Italian, Fast Food",
            discount = "Up to 50% OFF",
            deliveryDistance = "2.5 km",
            deliveryPrice = "$1.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.burgerfood),
            name = "Burger King",
            category = "American, Fast Food",
            discount = "Up to 40% OFF",
            deliveryDistance = "3.0 km",
            deliveryPrice = "$2.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.sushifood),
            name = "Sushi Spot",
            category = "Japanese, Seafood",
            discount = "Up to 30% OFF",
            deliveryDistance = "1.8 km",
            deliveryPrice = "$6.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.pastacornerfood),
            name = "Pasta corner",
            category = "Italian, Vegetarian",
            discount = "Up to 35% OFF",
            deliveryDistance = "2.2 km",
            deliveryPrice = "$1.99"
        )
        NewArrivalCard(
            imagePainter = painterResource(id = com.yisokheng.finalapp.R.drawable.steakhousefood),
            name = "Steak House",
            category = "Grill, BBQ",
            discount = "Up to 25% OFF",
            deliveryDistance = "4.0 km",
            deliveryPrice = "$2.99"
        )
    }
}

@Composable
fun NewArrivalCard(
    imagePainter: Painter,
    name: String,
    category: String,
    discount: String,
    deliveryDistance: String,
    deliveryPrice: String
) {
    Column(
        modifier = Modifier
            .width(350.dp)
            .shadow(4.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Discount Badge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE91E63))
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = discount,
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // Food/Drink Image
        Image(
            painter = imagePainter,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Restaurant Name
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Cuisine Category
        Text(
            text = category,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Delivery Distance
        Text(
            text = "📍 $deliveryDistance",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Delivery Price
        Text(
            text ="\uD83D\uDEF5 $deliveryPrice",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}
//nhjjj

@Composable
fun TopBrandsSection(isDarkMode: Boolean) {
    val topBrands = listOf(
        Brand(R.drawable.dairyqueenlogo, "Diary Queen", "15-20 min"),
        Brand(R.drawable.starbucklogo, "Starbucks", "20-30 min"),
        Brand(R.drawable.texaslogo, "Texas Chicken", "10-20 min"),
        Brand(R.drawable.pizzalogo, "The Pizza Company", "25-35 min"),
        Brand(R.drawable.chatimelogo, "Chatime", "15-20 min"),
        Brand(R.drawable.dominologo, "Domino's Pizza", "20-30 min"),
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(topBrands.size) { index ->
            TopBrandsItem(brand = topBrands[index], textColor = if (isDarkMode) Color.White else Color.Black)
        }
    }
}

@Composable
fun TopBrandsItem(brand: Brand, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(15.dp)
            .width(120.dp)
    ) {
        Image(
            painter = painterResource(id = brand.imageResId),
            contentDescription = brand.name,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = brand.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 5.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = brand.distance,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 5.dp)
        )
    }
}



@Composable
fun Footer(navController: NavController) {
    // Footer content
    BottomAppBar(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), backgroundColor = Color(0xFFF6F6F6), elevation = 8.dp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Food Icon and Label
            FooterItem(
                icon = "\uD83C\uDF71",
                label = "Food",
                onClick = { navController.navigate("HomeScreen") }
            )

            // Restaurant Icon and Label
            FooterItem(
                icon = "🍽️",
                label = "Restaurant",
                onClick = {navController.navigate("RestaurantScreen") }
            )

            // Search Icon and Label
            FooterItem(
                icon = Icons.Default.ShoppingCart,
                label = "My Cart",
                onClick = {navController.navigate("CartScreen")}
            )

            // Account Icon and Label
            FooterItem(
                icon = Icons.Default.Person,
                label = "Account",
                onClick = {navController.navigate("ProfileScreen")}
            )
        }
    }
}

@Composable
fun FooterItem(icon: Any, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        when (icon) {
            is ImageVector -> Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFFE91E63)
            )
            is String -> Text(
                text = icon, // Use Text for emojis
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black // Customize text color
        )
    }
}


data class Food(
    val imageRes: Int, // Image resource ID
    val name: String, // Name of the food
    val price: String, // Price of the food
    val description: String // Optional description
)

val foodList: List<Food> = listOf(
    Food(com.yisokheng.finalapp.R.drawable.groceries, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(com.yisokheng.finalapp.R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(com.yisokheng.finalapp.R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(com.yisokheng.finalapp.R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak"),
    Food(com.yisokheng.finalapp.R.drawable.pizzafood, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(com.yisokheng.finalapp.R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(com.yisokheng.finalapp.R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(com.yisokheng.finalapp.R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak"),
    Food(com.yisokheng.finalapp.R.drawable.pizzafood, "Hawaiian Pizza", "$12.75", "Pizza with ham and pineapple"),
    Food(com.yisokheng.finalapp.R.drawable.burgerfood, "Cheeseburger", "$8.50", "Classic beef cheeseburger"),
    Food(com.yisokheng.finalapp.R.drawable.sushifood, "Sushi Platter", "$15.00", "Assorted sushi rolls"),
    Food(com.yisokheng.finalapp.R.drawable.steakhousefood, "Tomahawk Steak", "$10.25", "Juicy grilled steak")
)

@Composable
fun FoodList(
    navController: NavController,
    isDarkMode: Boolean,
    textColor: Color,
    translation: Map<String, String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = translation["foodMenu"] ?: "Food Menu",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        itemsIndexed(foodList) { index, food ->
            FoodCard(navController = navController, food = food, isDarkMode = isDarkMode, textColor = textColor)
        }
    }
}

@Composable
fun FoodCard(navController: NavController, food: Food, isDarkMode: Boolean, textColor: Color) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
            },
        shape = RoundedCornerShape(10.dp),
        color = if (isDarkMode) Color(0xFF1E1E1E) else Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = food.imageRes),
                contentDescription = food.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = food.name,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = food.price,
                    fontSize = 16.sp,
                    color = Color(0xFF4CAF50)
                )
                Text(
                    text = food.description,
                    fontSize = 14.sp,
                    color = if (isDarkMode) Color.LightGray else Color.Gray
                )
            }
        }
    }
}
