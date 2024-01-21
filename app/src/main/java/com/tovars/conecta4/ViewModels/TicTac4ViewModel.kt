package com.tovars.conecta4.ViewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tovars.conecta4.Models.StatusTicTac4
import com.tovars.conecta4.Models.TicTac4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
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

    //private val _listColors = MutableStateFlow( MutableList(6){MutableList(7){Color.White} } )
    //val listColors  = _listColors.asStateFlow()

    private val _listColors = MutableList(6){MutableList(7){ MutableStateFlow<Color>(Color.White)}}
    val listColors: List<List<StateFlow<Color>>> = _listColors

    /*
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
     */

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
            //_listColors.value[aux][columnIndex] = currentPlayerColor

            _listColors[aux][columnIndex].value = currentPlayerColor

        // hay que asignar el valor completo, un alternativa seria usar la variable _gameBoard
                                // en vez de esta, ya que es mas facil asignar un nuevo estado copiandolo y desde la vista solo dependiendo el estado este cambie de color asi como el codigo de TODOAPP

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