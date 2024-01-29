package com.tovars.conecta4.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

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
import com.tovars.conecta4.ViewModels.TicTac4ViewModel


@Composable
fun ScreenPlay(ticTac4ViewModel: TicTac4ViewModel, functionmusic: () -> Unit) {

    Box {

        ImageBackground(R.drawable.fondo_out)

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

            BoxTitle("Tic tac 4")

            Spacer(modifier = Modifier.height(30.dp))

            Row {

                CustomButtonPlayer("Player 1", Color.Green, {})
                
                Spacer(modifier = Modifier.fillMaxWidth(0.4f))

                CustomButtonPlayer("Player 2", Color.Yellow, {})

            }

            TableComposable(ticTac4ViewModel, functionmusic)

        }

    }

}

@Composable
fun ImageBackground(@DrawableRes painterResourceId: Int){

    Image(
        painter = painterResource(id = painterResourceId),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

}

@Composable
fun BoxTitle(title: String){

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x66000000), shape = RoundedCornerShape(16.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {

        val gradientColors = listOf(Color(0xffffffff), Color(0xff62baff), Color(0xFF75FF56))

        Text(
            text = title, fontSize = 40.sp,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradientColors
                )
            )
        )

    }

}


@Composable
fun TableComposable(ticTac4ViewModel: TicTac4ViewModel, functionmusic: () -> Unit) {

    val ticTac4 by ticTac4ViewModel.ticTac4.collectAsState()

    val starvisibility by ticTac4ViewModel.starvisibility.collectAsState()

    val boardColors = ticTac4ViewModel.listColors

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

                            val currentCell = boardColors[rowIndex][columnIndex].collectAsState()

                            val colorpres by animateColorAsState(targetValue =  currentCell.value.color,
                                label = "", animationSpec = tween(1000)
                            )

                            ItemCell(colorpres, currentCell.value.isWinner){
                                ticTac4ViewModel.setPlay(rowIndex, columnIndex)
                            }

                        }
                    }
                }

            }
            
            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonPlayer("TURNO", Color(0xFF367BD1)) { ticTac4ViewModel.resetPlay() }

        }

        if (starvisibility){
            BoxStart { functionmusic() }
        }

    }
}

@Composable
fun ItemCell(colorpres: Color, isWinner: Boolean, onChange: () -> Unit){

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
                .background(colorpres)
                .clickable { onChange() },
            contentAlignment = Alignment.Center
        ) {
            // Contenido adicional si es necesario


            if(isWinner){

                Image(
                    painter = painterResource(id = R.drawable.panda),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.8F),
                    contentScale = ContentScale.Crop
                )

            }

        }


    }

}

@Composable
fun CustomButtonPlayer(title: String, color: Color, resetPlay: () -> Unit) {

    Button(onClick = { resetPlay()  },
        colors = ButtonDefaults.buttonColors(color)

    ) {

        Text(text = title, color = Color.White)

    }

}

@Composable
fun BoxStart(functionmusic: () -> Unit) {

    Box (contentAlignment = Alignment.Center){

        Column {

            Spacer(modifier = Modifier.fillMaxSize(0.4f))
            Loader()
            functionmusic()

        }

    }

}
