package com.example.diplom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diplom.item.Item
import com.example.diplom.R

data class MenuItem(
    val name: String,
    val size: String,
    val price: String,
    val imageRes: Int
)

@Composable
fun VerticalGrid(
    items: List<MenuItem>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(items.size) { index ->
            val item = items[index]
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
}