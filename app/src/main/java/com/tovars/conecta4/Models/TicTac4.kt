package com.tovars.conecta4.Models

import android.util.Log
import androidx.compose.ui.graphics.Color

data class TicTac4(var x: Int = 6, var y: Int = 7,
              var player1: Player = Player("said", Color.Green),
              var player2: Player = Player("tovar", Color.Yellow),
              var winplayer: () -> (Unit),
              var currentPlayer: StatusTicTac4 = StatusTicTac4.PLAYER1

) {

    val gameBoard = Array(x) { Array(y){ StatusTicTac4.NONE } }

    var currentStatusTicTac4 = StatusTicTac4.NONE

    fun compruebaWin(){

        currentStatusTicTac4 = checkWinner()

        when (currentStatusTicTac4){

            StatusTicTac4.PLAYER1 -> {

                winplayer()

            }
            StatusTicTac4.PLAYER2 -> {

                winplayer()

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

    fun setPlay(x: Int, y: Int): Int{

        val aux = compruebaEspacio(y)

        when (aux){

            -1 ->{
                //No hay espacio
            }
            else ->{
                gameBoard[aux][y] = currentPlayer
            }


        }


        compruebaWin()
        switchPlayer()

        return aux
    }

    fun switchPlayer() {

        if (currentPlayer == StatusTicTac4.PLAYER1){

            currentPlayer = StatusTicTac4.PLAYER2

        }else{

            currentPlayer = StatusTicTac4.PLAYER1

        }

    }

    fun compruebaEspacio(column: Int): Int{

        val rows = gameBoard.size

        for (j in 0 until rows) {

            if (gameBoard[rows - 1 - j][column] == StatusTicTac4.NONE){

                Log.d("Tictac4", "Hay espacio disponible en ${j}x${column}")

                //gameBoard[rows - 1 - j][column] = StatusTicTac4.PLAYER1

                return rows - 1 - j

            }

        }

        Log.d("Tictac4", "No hay espacio")

        return -1

    }

    fun checkWinner(): StatusTicTac4 {

        val rows = gameBoard.size
        val cols = gameBoard.first().size

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                // Filas
                if (j + 3 < cols && checkLine(*Array(4) { gameBoard[i][j + it] })) {
                    return gameBoard[i][j]
                }
                // Columnas
                if (i + 3 < rows && checkLine(*Array(4) { gameBoard[i + it][j] })) {
                    return gameBoard[i][j]
                }
                // Diagonales \
                if (i + 3 < rows && j + 3 < cols && checkLine(*Array(4) { gameBoard[i + it][j + it] })) {
                    return gameBoard[i][j]
                }
                // Diagonales /
                if (i + 3 < rows && j - 3 >= 0 && checkLine(*Array(4) { gameBoard[i + it][j - it] })) {
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