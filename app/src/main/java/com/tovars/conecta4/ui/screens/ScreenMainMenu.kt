package com.tovars.conecta4.ui.screens


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tovars.conecta4.R

@Composable
fun ScreenMainMenu(functionmusic: () -> Unit) {

    Box(modifier = Modifier) {


        var starvisibility by remember { mutableStateOf(false) }

        var textlove =
            if (!starvisibility) "Amor toca aqui\uD83D\uDC3C!" else "Te adoro\uD83D\uDDA4 "

        val dplove by animateDpAsState(
            targetValue = if (!starvisibility) 20.dp else 60.dp,
            label = "",
            animationSpec = tween(2000)
        )

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

            Image(
                painter = painterResource(id = R.drawable.board), // Reemplaza "tu_imagen" con el nombre de tu imagen en el directorio res/drawable
                contentDescription = null, // Puedes proporcionar una descripción adecuada si es necesario
                //modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Ajusta la escala según tus preferencias
            )

            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {},
                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffabf6b5)),
                elevation = ButtonDefaults.buttonElevation(50.dp)
            ) {

                Text(text = "Crear partida", fontSize = 20.sp, color = Color(0xff186b48))

            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = {},
                modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfffdbf9e)),
                elevation = ButtonDefaults.buttonElevation(50.dp)
            ) {

                Text(text = "Unirse a una partida", fontSize = 20.sp, color = Color(0xff6b1818))

            }

            //Icon(Icons.Filled.Favorite, contentDescription = "Favorite")

            Button(
                onClick = { starvisibility = !starvisibility },
                modifier = Modifier.clip(CircleShape),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffe51049)),
                elevation = ButtonDefaults.buttonElevation(50.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {

                AnimatedContent(targetState = textlove, label = "") {

                    Text(
                        text = it, fontSize = dplove.value.sp,
                        color = Color(0xffffffff)
                    )

                }


            }


        }

        if (starvisibility) {
            Column {
                Spacer(modifier = Modifier.fillMaxSize(0.4f))
                Loader()
                functionmusic()

            }

        }

    }

}

@Composable
fun Loader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.start))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

