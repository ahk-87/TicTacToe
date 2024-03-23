package tictactoe

class TicTacToe {

    val grid = List(3) { MutableList(3) { ' ' } }
    var player = 'X'

    fun play(input: String): Boolean {
        val x: Int
        val y: Int
        try {
            val ins = input.split(" ").map { it.toInt() - 1 }
            x = ins[0]
            y = ins[1]
        } catch (e: Exception) {
            println("You should enter numbers!")
            return false
        }
        if (x > 2 || y > 2) {
            println("Coordinates should be from 1 to 3!"); return false
        }
        if (grid[x][y] != ' ') {
            println("This cell is occupied! Choose another one!"); return false
        }

        grid[x][y] = player
        printGrid()

        val w = checkWinner(player)
        if (w) {
            println("$player wins"); return w
        }
        if (grid.all { ' ' !in it }) {
            println("Draw")
            return true
        }
        player = if (player == 'X') 'O' else 'X'
        return false
    }

    fun checkWinner(c: Char): Boolean {
        var win: Boolean
        for (i in 0..2) {
            // horizontal checking
            win = grid[i].count { it == c } == 3
            if (win) return win
            // vertical checking
            win = grid[0][i] == c && grid[1][i] == c && grid[2][i] == c
            if (win) return win
        }
        win = grid[0][0] == c && grid[1][1] == c && grid[2][2] == c
        if (win) return win
        win = grid[0][2] == c && grid[1][1] == c && grid[2][0] == c
        return win
    }

    fun printGrid() {
        println("---------")
        for (i in 0..2) {
            println("| ${grid[i].joinToString(" ")} |")
        }
        println("---------")
    }
}

fun main() {
    val game = TicTacToe()
    game.printGrid()
    var endGame = false
    while (!endGame) {
        endGame = game.play(readln())
    }
}