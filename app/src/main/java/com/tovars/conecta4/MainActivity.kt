package com.tovars.conecta4

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tovars.conecta4.ViewModels.TicTac4ViewModel
import com.tovars.conecta4.ui.screens.ScreenPlay
import com.tovars.conecta4.ui.theme.Conecta4Theme

class MainActivity : ComponentActivity() {

    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mp = MediaPlayer.create(applicationContext, R.raw.audio)
        mp?.isLooping = true
        mp?.setVolume(0.1f,0.1f)
        mp?.start() // Inicia la reproducción


        setContent {
            Conecta4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")

                    //ScreenMainMenu { playstarmusic() }
                    ScreenPlay(TicTac4ViewModel()) { playstarmusic() }
                }
            }
        }
    }

    fun playstarmusic(){

        Log.d("Hola", "Holaaa")

        var mplove: MediaPlayer? = null

        mplove = MediaPlayer.create(applicationContext, R.raw.wow)
        mplove?.setVolume(0.8f,0.8f)
        mplove?.isLooping = false
        mplove?.start()

        mplove?.setOnCompletionListener {

            mplove?.stop()
            mplove?.release()

        }

    }

    override fun onPause() {
        super.onPause()
        mp?.setVolume(0.0f,0.0f)
    }

    override fun onResume() {
        super.onResume()
        mp?.setVolume(0.1f,0.1f)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos del reproductor de medios al destruir la actividad
        mp?.release()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Conecta4Theme {
        Greeting("Android")
    }
}