var board = arrayListOf<ArrayList<String>>()

fun main() {

    for (i in 0..2) {
        val row = arrayListOf<String>()
        for (j in 0..2) {
            row.add("")
        }
        board.add(row)
    }

    printBoard()

    var continueGame = true
    do {
        println("Please enter a position between 1,1 to 3,3")
        val input = readlnOrNull() ?: ""
        var x: Int
        var y: Int
        try {
            val positions = input.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()

            var skipRound = false

            if (board[x - 1][y - 1] != "") {
                println("Position is already taken, try again")
                skipRound = true
            } else {
                board[x - 1][y - 1] = "X"
                printBoard()
            }

            if (!skipRound) {
                val playerWon = checkWinner(true)
                if (playerWon) {
                    println("\n\n\uD83C\uDF89\uD83E\uDD73\uD83C\uDF8A\uD83C\uDF81\uD83C\uDF89\uD83E\uDD73\uD83C\uDF8A\uD83C\uDF81")
                    println("You won. Congratulations!!!")
                    continueGame = false
                }

                val boardFull = checkBoardFull()
                if (!playerWon && boardFull) {
                    println("\n\nIt's a tie!")
                    continueGame = false
                }
            }

        } catch (e: Exception) {
            println("Invalid input, please try again")
        }
    } while (continueGame)
}

private fun printBoard() {
    println("-------------")
    for (i in 0..2) {
        for (j in 0..2) {
            when (board[i][j]) {
                "X" -> print("| X ")
                "O" -> print("| O ")
                else -> print("|   ")
            }
        }
        println("|")
        println("-------------")
    }
}

private fun checkWinner(player: Boolean): Boolean {
    //player = true; check if player has won the game
    //player = false; check if computer has won the game
    var won = false
    val checkSymbol = if (player) "X" else "O"

    for (i in 0..2) {
        //horizontal wins
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol) {
            won = true
        }

        //vertical wins
        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol) {
            won = true
        }

        //diagonal wins
        if ((board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol) || (board[0][2] == checkSymbol && board[1][1] == checkSymbol && board[2][0] == checkSymbol)) {
            won = true
        }

    }
    return won
}

private fun checkBoardFull(): Boolean {
    var boardFull = true
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == "") {
                boardFull = false
                break
            }
        }
    }
    return boardFull
}