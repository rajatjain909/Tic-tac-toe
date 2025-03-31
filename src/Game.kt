import kotlin.random.Random

// The game board, represented as a 3x3 grid
var board = arrayListOf<ArrayList<String>>()

/**
 * Main function to run the Tic-Tac-Toe game.
 */
fun main() {
    // Initialize the board with empty strings
    for (i in 0..2) {
        val row = arrayListOf<String>()
        for (j in 0..2) {
            row.add("") // Empty space represents an unoccupied cell
        }
        board.add(row)
    }

    printBoard() // Display the empty board

    var continueGame = true
    do {
        // Player's turn
        println("Please enter a position between 1,1 to 3,3")
        val input = readlnOrNull() ?: ""
        var x: Int
        var y: Int
        try {
            val positions = input.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()

            var skipRound = false

            // Check if the selected position is already occupied
            if (board[x - 1][y - 1] != "") {
                println("Position is already taken, try again")
                skipRound = true
            } else {
                board[x - 1][y - 1] = "X" // Mark player move
                println("\nPlayer played -->>")
                printBoard()
            }

            if (!skipRound) {
                val playerWon = checkWinner(true) // Check if player won
                if (playerWon) {
                    println("\n\n\uD83C\uDF89\uD83E\uDD73\uD83C\uDF8A\uD83C\uDF81\uD83C\uDF89\uD83E\uDD73\uD83C\uDF8A\uD83C\uDF81")
                    println("You won. Congratulations!!!")
                    continueGame = false
                }

                val boardFull = checkBoardFull() // Check if the board is full (tie condition)
                if (!playerWon && boardFull) {
                    println("\n\nIt's a tie!")
                    continueGame = false
                }

                if (continueGame) {
                    // Computer's turn
                    placeComputerMove()
                    println("\nComputer move -->>")
                    printBoard()
                    val computerWon = checkWinner(false) // Check if computer won
                    if (computerWon) {
                        println("Computer won.")
                        continueGame = false
                    }
                }
            }

        } catch (e: Exception) {
            println("Invalid input, please try again")
        }
    } while (continueGame)
}

/**
 * Prints the current state of the Tic-Tac-Toe board.
 */
private fun printBoard() {
    println("-------------")
    for (i in 0..2) {
        for (j in 0..2) {
            when (board[i][j]) {
                "X" -> print("| X ") // Player's move
                "O" -> print("| O ") // Computer's move
                else -> print("|   ") // Empty cell
            }
        }
        println("|")
        println("-------------")
    }
}

/**
 * Checks if a player or the computer has won the game.
 * @param player Boolean flag indicating whether to check for the player (true) or computer (false).
 * @return Boolean indicating whether the given player has won.
 */
private fun checkWinner(player: Boolean): Boolean {
    var won = false
    val checkSymbol = if (player) "X" else "O"

    for (i in 0..2) {
        // Check horizontal wins
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol) {
            won = true
        }

        // Check vertical wins
        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol) {
            won = true
        }
    }

    // Check diagonal wins
    if ((board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol) ||
        (board[0][2] == checkSymbol && board[1][1] == checkSymbol && board[2][0] == checkSymbol)) {
        won = true
    }

    return won
}

/**
 * Checks if the Tic-Tac-Toe board is full.
 * @return Boolean indicating whether the board is full.
 */
private fun checkBoardFull(): Boolean {
    var boardFull = true
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == "") {
                boardFull = false
                break // Exit early if an empty space is found
            }
        }
    }
    return boardFull
}

/**
 * Places the computer's move in an empty position on the board.
 */
private fun placeComputerMove() {
    var i: Int
    var j: Int

    do {
        i = Random.nextInt(3) // Generate random row index
        j = Random.nextInt(3) // Generate random column index
    } while (board[i][j] != "") // Ensure the position is empty

    board[i][j] = "O" // Place computer's move
}
