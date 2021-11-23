package com.example.food.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.food.R
import kotlinx.coroutines.launch
import java.util.jar.Attributes
import kotlin.random.Random

data class Food(val name: String, val image: Int)

@Composable
fun Home() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val openDialog = remember {
        mutableStateOf(true)
    }
    val listFoods = listOf<Food>(Food("cơm bình dân",R.drawable.img),
        Food("cơm gà",R.drawable.comga),
        Food("cơm gà luộc",R.drawable.comgaluoc),
        Food("comhop",R.drawable.comhop),
        Food("cơm rau",R.drawable.comrau),
        Food("cơm gà chiên",R.drawable.gachien))
    var food: Food= listFoods[Random.nextInt(listFoods.size)]

    val textPaddingAll = 24.dp
    val buttonPaddingAll = 8.dp
    val dialogShape = RoundedCornerShape(16.dp)
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            if (openDialog.value)
            LazyColumn {
                items(listFoods) { item ->

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Card(
                            shape = RoundedCornerShape(15.dp),
                            elevation = 5.dp
                        ) {
                            Image(
                                painter = painterResource(id = item.image),
                                contentDescription = "anh",
                                modifier = Modifier
                                    .padding(10.dp)
                                    .heightIn(80.dp)
                                    .widthIn(200.dp)
                            )
                        }
                        Text(
                            text = item.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )

                    }
                }

            }
            else
                AlertDialog(

                    onDismissRequest = {
                        openDialog.value = false
                    },

                    text = {

                            Text(food.name,textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth())

                    },

                    buttons = {
                            Column{
                                Image(
                                    painter = painterResource(food.image),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Row(Modifier.padding(all = textPaddingAll)){

                                }
                                Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

                                Row(
                                    modifier = Modifier.padding(all = buttonPaddingAll),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    TextButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            openDialog.value= true
                                        }
                                    ) {
                                        Text(text = "Dismiss", color = MaterialTheme.colors.onSurface)
                                    }
                                }

                            }
                    }
                )

        },
        bottomBar = {
                    ButtonScreen {
                            food= listFoods[Random.nextInt(listFoods.size)]
                            openDialog.value=false

                    }
        },
    )


}



