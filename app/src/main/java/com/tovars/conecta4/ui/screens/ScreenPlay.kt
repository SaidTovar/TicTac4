package com.tovars.conecta4.ui.screens

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tovars.conecta4.R
import com.tovars.conecta4.Models.StatusTicTac4
import com.tovars.conecta4.Models.TicTac4
import com.tovars.conecta4.ViewModels.TicTac4ViewModel
import kotlinx.coroutines.delay


@Composable
fun ScreenPlay(ticTac4ViewModel: TicTac4ViewModel) {

    //val temp: Float by controliotViewModel.temp.collectAsState()



    Box {

        Image(
            painter = painterResource(id = R.drawable.fondo_out), // Reemplaza "tu_imagen" con el nombre de tu imagen en el directorio res/drawable
            contentDescription = null, // Puedes proporcionar una descripción adecuada si es necesario
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la escala según tus preferencias
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(color = Color(0x727AB7EB), shape = RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp)),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(50.dp))

            //Titulo


            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0x66000000), shape = RoundedCornerShape(16.dp))
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {

                val gradientColors = listOf(Color(0xffffffff), Color(0xff62baff), Color(0xFF75FF56))

                Text(
                    text = "Tic tac 4", fontSize = 40.sp,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        )
                    )
                )

            }

            Spacer(modifier = Modifier.height(30.dp))

            TableComposable(ticTac4ViewModel)

        }

    }

}


@Composable
fun TableComposable(ticTac4ViewModel: TicTac4ViewModel) {

    val ticTac4 by ticTac4ViewModel.ticTac4.collectAsState()

    val starvisibility by ticTac4ViewModel.starvisibility.collectAsState()

    val counter by ticTac4ViewModel.counter.collectAsState()

    val colorplayer by ticTac4ViewModel.currentPlayerColor.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Crear filas
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$counter", color = Color.Black)

            Column (modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))

            ){

                repeat(ticTac4.gameBoard.size) { rowIndex ->
                    // Crear celdas en cada fila
                    Row(
                        modifier = Modifier
                            .height(46.dp) // Altura de cada fila

                    ) {
                        repeat(ticTac4.gameBoard.first().size) { columnIndex ->
                            // Contenido de la celda (círculo centrado)

                            val colorpres by animateColorAsState(targetValue =  if (ticTac4.gameBoard[rowIndex][columnIndex] == StatusTicTac4.NONE)
                                Color.LightGray
                            else
                                colorplayer,
                                label = "", animationSpec = tween(1000)
                            )

                            Box(
                                modifier = Modifier
                                    .width(46.dp) // Ancho de cada celda
                                    .height(46.dp) // Altura de cada celda
                                    .background(Color(0xff005e86)),
                                contentAlignment = Alignment.Center
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(
                                            colorpres
                                        )
                                        .clickable {

                                            ticTac4ViewModel.setPlay(rowIndex, columnIndex)

                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Contenido adicional si es necesario

                                }


                            }
                        }
                    }
                }

            }
            
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = { ticTac4ViewModel.contar()  }, colors = ButtonDefaults.buttonColors(Color(0xFF367BD1))
            ) {

                Text(text = "TURNO", color = Color.White)

            }

        }

        Box (contentAlignment = Alignment.Center){

            if (starvisibility) {
                Column {

                    Spacer(modifier = Modifier.fillMaxSize(0.4f))
                    Loader()

                }

            }

        }
    }
}
