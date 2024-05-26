package com.example.diplom

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.diplom.bottomnavigation.BottomNavigation
import com.example.diplom.historyoforders.HistoryOfOrders
import com.example.diplom.nohistoryoforders.NoHistoryOfOrders
import com.example.diplom.previousordercard.PreviousOrderCard
import com.example.diplom.profilecard.ProfileCard
import com.example.diplom.settingbuttons.SettingButtons
import com.example.diplom.App

import com.example.diplom.favourite.Favourite
import com.example.diplom.mapcard.MapCard
import com.example.diplom.menu.Menu
import com.example.diplom.searchbuttons.SearchButtons


@Composable
fun ProfileScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true // true, если фон светлый, и false, если фон темный
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
//            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Image(
            painter = painterResource(id = R.drawable.backgr),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ProfileCard(
                onProfileCardTapped = { /* Handle navigation */ },
                userName = "Ирина",
                userPhoneNumber = "+7 925-570-50-63",
                //profilePhotoImageContent = painterResource(id = R.drawable.profilePhoto)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingButtons(
                onOrdersbuttonTapped = { /* Handle navigation */ },
                onBonusesbuttonTapped = { /* Handle navigation */ },
                onAddressesbuttonTapped = { /* Handle navigation */ },
                onSettingsbuttonTapped = { /* Handle navigation */ },
                orders = "Заказы",
                bonuses = "Баллы",
                addresses = "Адреса",
                settings = "Настройки профиля"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HistoryOfOrders()
//            NoHistoryOfOrders(
//                onMakeOrderButtonTapped = { /* Handle navigation */ }
//            )
            Spacer(modifier = Modifier.height(16.dp))
            PreviousOrderCard(
                onOrderCardTapped = { /* Handle navigation */ },
                dateInfo = "31 марта 2024",
                priceInfo = "205 ₽",
                previousItemNameAndSize = AnnotatedString("Капуччино 250 мл"),
                previousItemPriceText = "205 ₽"
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomNavigation(
                onMainScreenButtonTapped = { navController.navigate("home") },
                onBagScreenButtonTapped = { /* Handle navigation */ },
                onProfileScreenButtonTapped = { /* Handle navigation */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}