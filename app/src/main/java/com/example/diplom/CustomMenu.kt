package com.example.diplom

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diplom.item.Item

//data class MenuItem(
//    val name: String,
//    val size: String,
//    val price: String,
//    val imageRes: Int
//)

//@Composable
//fun CustomMenu(
//    items: List<MenuItem>,
//    modifier: Modifier = Modifier
//) {
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        modifier = modifier
//    ) {
//        items(items.chunked(2)) { rowItems ->
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ) {
//                rowItems.forEach { item ->
//                    Box(modifier = Modifier.weight(1f)) {
//                        Item(
//                            itemName = item.name,
//                            itemSize = item.size,
//                            itemPrice = item.price,
//                            itemImage = painterResource(id = item.imageRes),
//                            onItemTapped = { /* Handle navigation */ },
//                            onItemB       uttonTapped = { /* Handle navigation */ }
//                        )
//                    }
//                }
//                // Добавляем пустое пространство, если rowItems содержит только один элемент
//                if (rowItems.size == 1) {
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//            }
//        }
//    }
//}