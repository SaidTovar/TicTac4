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

    }

    fun setPlay(x: Int, y: Int){

        gameBoard[x][y] = StatusTicTac4.PLAYER1

        compruebaWin()

    }

    fun checkWinner(): StatusTicTac4 {

        // Verificar filas y columnas
        for (i in 0 until 6) {
            for (j in 0 until 7) {
                // Filas
                if (j + 3 < 7 && checkLine(gameBoard[i][j], gameBoard[i][j + 1], gameBoard[i][j + 2], gameBoard[i][j + 3])) {
                    return gameBoard[i][j]
                }
                // Columnas
                if (i + 3 < 6 && checkLine(gameBoard[i][j], gameBoard[i + 1][j], gameBoard[i + 2][j], gameBoard[i + 3][j])) {
                    return gameBoard[i][j]
                }
                // Diagonales \
                if (i + 3 < 6 && j + 3 < 7 && checkLine(gameBoard[i][j], gameBoard[i + 1][j + 1], gameBoard[i + 2][j + 2], gameBoard[i + 3][j + 3])) {
                    return gameBoard[i][j]
                }
                // Diagonales /
                if (i + 3 < 6 && j - 3 >= 0 && checkLine(gameBoard[i][j], gameBoard[i + 1][j - 1], gameBoard[i + 2][j - 2], gameBoard[i + 3][j - 3])) {
                    return gameBoard[i][j]
                }
            }
        }

        // Verificar empate
        if (gameBoard.any { row -> row.any { cell -> cell == StatusTicTac4.NONE } }) {
            return StatusTicTac4.NONE
        }

        return StatusTicTac4.EMPATE // Empate
    }

    fun checkLine(vararg cells: StatusTicTac4): Boolean {
        return cells.all { it != StatusTicTac4.NONE && it == cells[0] }
    }


}

enum class StatusTicTac4(){
    PLAYER1, PLAYER2, EMPATE, NONE
}