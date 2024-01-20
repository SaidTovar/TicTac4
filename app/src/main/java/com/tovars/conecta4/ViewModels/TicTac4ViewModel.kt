package com.tovars.conecta4.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.tovars.conecta4.Models.StatusTicTac4
import com.tovars.conecta4.Models.TicTac4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class TicTac4ViewModel: ViewModel() {

    //private val _temp = MutableStateFlow(0f)
    //val temp: StateFlow<Float> get() = _temp

    private val _gameBoard = MutableStateFlow(Array(6) { Array(7){ StatusTicTac4.NONE } })
    val gameBoard = _gameBoard.asStateFlow()


    private val _starvisibility = MutableStateFlow( false)
    val starvisibility  = _starvisibility.asStateFlow()


    private val _ticTac4 = MutableStateFlow(TicTac4(winplayer = {winplayer()} ))
    val ticTac4 = _ticTac4.asStateFlow()


    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    private var currentPlayerColor = Color.Green

    private val _listColors = mutableStateListOf<MutableList<Color>>()
    val listColors: List<List<Color>> = _listColors

    init {

        _ticTac4.value.gameBoard.forEach {

            var auxColor: MutableList<Color> = emptyList<Color>().toMutableList()

            it.forEach {iy ->

                when(iy){

                    StatusTicTac4.PLAYER1 ->{

                        auxColor.add(Color.Green)

                    }
                    StatusTicTac4.PLAYER2 -> {

                        auxColor.add(Color.Yellow)

                    }
                    StatusTicTac4.EMPATE -> {

                        auxColor.add(Color.White)

                    }
                    StatusTicTac4.NONE -> {

                        auxColor.add(Color.White)

                    }
                }

            }

            _listColors.add(auxColor)

        }

    }

    fun contar(){
        _counter.value++
    }


    private fun winplayer() {

        _starvisibility.value = !_starvisibility.value

    }

    fun setPlay(rowIndex: Int, columnIndex: Int) {

        val aux:Int

        _ticTac4.value = _ticTac4.value.let {

            aux  = it.setPlay(rowIndex, columnIndex)

            //it.copy(currentPlayer = if (it.currentPlayer == StatusTicTac4.PLAYER1) StatusTicTac4.PLAYER2 else StatusTicTac4.PLAYER1)

            it.copy()
        }

        if(aux != -1){
            _listColors[aux][columnIndex] = currentPlayerColor

            //_listColors.get(aux).set(columnIndex, currentPlayerColor)
        }

        switchPlayerColor()

    }

    private fun switchPlayerColor() {

        if (_ticTac4.value.currentPlayer == StatusTicTac4.PLAYER1){

            currentPlayerColor = _ticTac4.value.player1.playerColor

        }else{

            currentPlayerColor = _ticTac4.value.player2.playerColor

        }

    }

}