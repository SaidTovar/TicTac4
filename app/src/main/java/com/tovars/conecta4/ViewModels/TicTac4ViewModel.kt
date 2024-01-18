package com.tovars.conecta4.ViewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.tovars.conecta4.Models.StatusTicTac4
import com.tovars.conecta4.Models.TicTac4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

    fun contar(){
        _counter.value++
    }


    private fun winplayer() {

        _starvisibility.value = !_starvisibility.value

    }

    fun setPlay(rowIndex: Int, columnIndex: Int) {

        _ticTac4.value = _ticTac4.value.let {

            it.setPlay(rowIndex, columnIndex)

            it.copy(currentPlayer = switchPlayer())

        }

        switchPlayerColor()

    }

    fun switchPlayer(): StatusTicTac4 {

        if (_ticTac4.value.currentPlayer == StatusTicTac4.PLAYER1){

            return StatusTicTac4.PLAYER2

        }

        return StatusTicTac4.PLAYER1

    }

    private fun switchPlayerColor() {

        if (_ticTac4.value.currentPlayer == StatusTicTac4.PLAYER1){

            _currentPlayerColor.value = _ticTac4.value.player1.playerColor

        }

        _currentPlayerColor.value = _ticTac4.value.player2.playerColor

    }
    
    private val _currentPlayerColor = MutableStateFlow(Color.White)
    val currentPlayerColor = _currentPlayerColor.asStateFlow()

}