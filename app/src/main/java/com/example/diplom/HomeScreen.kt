package com.example.diplom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.diplom.bottomnavigation.BottomNavigation
import com.example.diplom.mapcard.MapCard
import com.example.diplom.menusection.MenuSection
import com.example.diplom.searchbuttons.SearchButtons

import com.example.diplom.favouriteitem.FavouriteItem

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.diplom.item.Item
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import com.example.diplom.favouritesection.FavouriteSection


val MenuItems = listOf(
    MenuItemData("Бамбл кофе", "350 мл", "320 ₽", R.drawable.item_item_image),
    MenuItemData("Американо", "200 мл", "130 ₽", R.drawable.americano),
    MenuItemData("Бамбл кофе", "350 мл", "320 ₽", R.drawable.item_item_image)
)

val FavItems = listOf(
    FavItemData("Капучино", "250 мл", "205 ₽", R.drawable.favourite_item_favourite_image, R.drawable.favourite_item_favourite_milk_add, R.drawable.favourite_item_favourite_cap_add),
    //FavItemData("Американо", "200 мл", "130 ₽", R.drawable.favourite_item_favourite_image, R.drawable.favourite_item_favourite_milk_add, R.drawable.favourite_item_favourite_cap_add),
    //FavItemData("Бамбл кофе", "350 мл", "320 ₽", R.drawable.favourite_item_favourite_image, R.drawable.favourite_item_favourite_milk_add, R.drawable.favourite_item_favourite_cap_add)
)


@Composable
fun HomeScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = true // true, если фон светлый
    )

    var showSearchInput by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filteredMenuItems by remember { mutableStateOf(MenuItems) }


    Box(
        modifier = Modifier.fillMaxSize().systemBarsPadding() // Добавляем это, чтобы элементы располагались под системными панелями
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_long),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp)
        ) {
            item {
                MapCard(
                    onMapCardTapped = { /* Handle navigation */ },
                    addressKm = "Ближайшая кофейня в 3 км",
                    addressName = "ТРЦ \"Пятая Авеню\""
                )
            }
            item {
                SearchButtons(
                    onSearchTapped = { showSearchInput = true },
                    onNewCoffeeTapped = { /* Handle navigation */ },
                    onBlackCoffeeTapped = { /* Handle navigation */ },
                    onMilkCoffeeTapped = { /* Handle navigation */ },
                    onColdCoffeeTapped = { /* Handle navigation */ },
                    onTeaTapped = { /* Handle navigation */ },
                    onMatchaTapped = { /* Handle navigation */ },
                    newCoffeeText = "Новинки",
                    blackCoffeeText = "Чёрный",
                    milkCoffeeText = "С молоком",
                    coldCoffeeText = "Холодный",
                    teaText = "Чай",
                    matchaText = "Матча и какао"
                )
                if (showSearchInput) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        SearchInputField(searchText) { newText ->
                            searchText = newText
                            filteredMenuItems = MenuItems.filter {
                                it.name.contains(newText, ignoreCase = true)
                            }
                        }
                    }
                }
            }
            item {
                FavouriteSection(
                    favouriteText = "Любимое:"
                )
            }
            items(FavItems.chunked(4)) {rowItems ->
                Row (
//                    horizontalArrangement = Arrangement.spacedBy(16.dp),
//                    modifier = Modifier
//                        //.fillMaxWidth()

                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.width(247.dp)){
                                FavouriteItem(
                                    favouriteName = item.favName,
                                    favouriteSize = item.favSize,
                                    favouritePrice = item.favPrice,
                                    favouriteImage = painterResource(id = item.favImageRes),
                                    favouriteMilkAdd = painterResource(id = item.favMilkImageRes),
                                    favouriteCapAdd = painterResource(id = item.favCupImageRes),
                                    onFavouriteItemTapped = { /* Handle navigation */ }
                                )
                        }
                    }
                    // Добавляем пустое пространство, если rowItems содержит только один элемент
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            item {
                MenuSection(
                    menuSectionText = "Чёрный"
                )
            }
            items(MenuItems.chunked(2)) { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)){
                            Item(
                                itemName = item.name,
                                itemSize = item.size,
                                itemPrice = item.price,
                                itemImage = painterResource(id = item.imageRes),
                                onItemTapped = { /* Handle navigation */ },
                                onItemButtonTapped = { /* Handle navigation */ }
                            )
                        }
                    }
                    // Добавляем пустое пространство, если rowItems содержит только один элемент
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .align(Alignment.BottomCenter)
        ) {
            BottomNavigation(
                onMainScreenButtonTapped = { /* Handle navigation */ },
                onBagScreenButtonTapped = { /* Handle navigation */ },
                onProfileScreenButtonTapped = { navController.navigate("profile") }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchInputField(searchText: String, onSearchTextChanged: (String) -> Unit) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    TextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester),
        placeholder = { Text("Поиск...") },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(
                alpha = 102,
                red = 255,
                green = 255,
                blue = 255
            ),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}


data class MenuItemData(
    val name: String,
    val size: String,
    val price: String,
    val imageRes: Int
)

data class FavItemData(
    val favName: String,
    val favSize: String,
    val favPrice: String,
    val favImageRes: Int,
    val favMilkImageRes: Int,
    val favCupImageRes: Int,
)