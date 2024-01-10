package com.tovars.conecta4

import android.util.Log
import androidx.compose.ui.graphics.Color

class TicTac4(var x: Int = 6, var y: Int = 7, var player1: Player = Player("said", Color.Green), var winplayer: () -> (Unit)) {

    //val gameBoard: MutableList<MutableList<Color>> = MutableList(6) { MutableList(7) { Color.White } }

    val gameBoard = Array(6) { Array(7){StatusTicTac4.NONE} }


    var currentStatusTicTac4 = StatusTicTac4.NONE

    fun compruebaWin(){

        Log.d("Tictac4", "")

        currentStatusTicTac4 = checkWinner()

        when (currentStatusTicTac4){

            StatusTicTac4.PLAYER1 -> {

                winplayer()

            }
            StatusTicTac4.PLAYER2 -> {

            }
            StatusTicTac4.EMPATE -> {

            }
            StatusTicTac4.NONE -> {

            }
        }

        Log.d("Tictac4", ""+currentStatusTicTac4)

    }

    private fun win() {
        TODO("Not yet implemented")
    }

    fun setPlay(x: Int, y: Int){

        gameBoard[x][y] = StatusTicTac4.PLAYER1

        compruebaWin()

    }

    fun checkWinner(): StatusTicTac4 {

        // Verificar filas y columnas
/*

        for (i in 0 until 4) {
            if (gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][0] != StatusTicTac4.NONE) {
                return gameBoard[i][0]
            }
            if (gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[0][i] != StatusTicTac4.NONE) {
                return gameBoard[0][i]
            }
        }
*/

        //Log.d("Tictac4", "${gameBoard[0].size}")

        for (i in gameBoard.indices) {

            for (j in 0 .. (gameBoard.size-3)){

                if (gameBoard[i][0+j] == gameBoard[i][1+j] &&
                    gameBoard[i][1+j] == gameBoard[i][2+j] &&
                    gameBoard[i][2+j] == gameBoard[i][3+j] &&
                    gameBoard[i][0+j] != StatusTicTac4.NONE) {
                    return gameBoard[i][0+j]
                }

            }

        }

        for (i in gameBoard[0].indices){

            for (j in 0 until (gameBoard[0].size-4)){

                if (gameBoard[0+j][i] == gameBoard[1+j][i] &&
                    gameBoard[1+j][i] == gameBoard[2+j][i] &&
                    gameBoard[2+j][i] == gameBoard[3+j][i] &&
                    gameBoard[0+j][i] != StatusTicTac4.NONE
                ) {

                    return gameBoard[0+j][i]
                }

            }

        }

        // Verificar empate
        for (row in gameBoard) {
            for (cell in row) {
                if (cell == StatusTicTac4.NONE) {
                    return StatusTicTac4.NONE
                }
            }
        }

        return StatusTicTac4.EMPATE // Empate
    }

}

enum class StatusTicTac4(){
    PLAYER1, PLAYER2, EMPATE, NONE
}