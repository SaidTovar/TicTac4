package com.tovars.conecta4.ViewModels

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.tovars.conecta4.Models.StatusTicTac4
import com.tovars.conecta4.Models.TicTac4
import com.tovars.conecta4.Models.CellTicTac
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TicTac4ViewModel: ViewModel() {

    //private val _temp = MutableStateFlow(0f)
    //val temp: StateFlow<Float> get() = _temp


    private val _starvisibility = MutableStateFlow( false)
    val starvisibility  = _starvisibility.asStateFlow()


    private val _ticTac4 = MutableStateFlow(TicTac4(winplayer = {winplayer()} ))
    val ticTac4 = _ticTac4.asStateFlow()


    private val currentPlayerColor = MutableStateFlow(Color.Green)


    private val _listColors = MutableList(6){MutableList(7){ MutableStateFlow<CellTicTac>(CellTicTac())}}
    val listColors: List<List<StateFlow<CellTicTac>>> = _listColors


    private fun winplayer() {

        _ticTac4.value.gameBoardWin.forEachIndexed { indexX, it->

            it.forEachIndexed {indexY, iy ->

                var (r, g, b) = currentPlayerColor.value
                r *= 0.5F
                g *= 0.5F
                b *= 0.5F

                when(iy){

                    StatusTicTac4.PLAYER1 ->{

                        _listColors[indexX][indexY].value = CellTicTac(Color(r,g,b), true)
                        Log.d("celdas win", " celda[$indexX][$indexY]")

                    }
                    StatusTicTac4.PLAYER2 -> {

                        _listColors[indexX][indexY].value = CellTicTac(currentPlayerColor.value, true)
                        Log.d("celdas win", " celda[$indexX][$indexY]")

                    }
                    StatusTicTac4.EMPATE -> {

                        //auxColor.add(Color.White)

                    }
                    StatusTicTac4.NONE -> {

                        //auxColor.add(Color.White)

                    }
                }

            }

        }

        _starvisibility.value = true

    }

    fun setPlay(rowIndex: Int, columnIndex: Int) {

        val aux:Int

        _ticTac4.value = _ticTac4.value.let {

            aux  = it.setPlay(rowIndex, columnIndex)

            //it.copy(currentPlayer = if (it.currentPlayer == StatusTicTac4.PLAYER1) StatusTicTac4.PLAYER2 else StatusTicTac4.PLAYER1)

            it.copy()
        }

        if(aux != -1){

            if (!_listColors[aux][columnIndex].value.isWinner){

                _listColors[aux][columnIndex].value = CellTicTac(currentPlayerColor.value)

            }

        }

        switchPlayerColor()

    }

    private fun switchPlayerColor() {

        //_colorWinner.value = Color(currentPlayerColor.value.red*0.5f, currentPlayerColor.value.green*0.5f, currentPlayerColor.value.blue*0.5f)

        if (_ticTac4.value.currentPlayer == StatusTicTac4.PLAYER1){

            currentPlayerColor.value = _ticTac4.value.player1.playerColor

        }else{

            currentPlayerColor.value = _ticTac4.value.player2.playerColor

        }

    }

    fun resetPlay(){

        currentPlayerColor.value = Color.Green

        _starvisibility.value = false

        _starvisibility.value = false

        _ticTac4.value = TicTac4(winplayer = {winplayer()} )

        _listColors.forEach {
            it.forEach {iy ->

                iy.value = CellTicTac(Color.LightGray)

            }
        }

    }

}