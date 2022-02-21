fun main(args: Array<String>) {

    var turn: Boolean = true //false for player O, true for player X
    var inGame: Boolean = true //false for game over
    var placement: Int
    var row: Int
    var col: Int
    val board = Array<String>(5) { _ -> "" }
    initBoard(board)
    drawBoard(board)

    gameLoop@while(inGame){
        println("Enter your placement (1-9): ")
        placement = readLine()!!.toInt()
        print("\n\n")

        when(placement){
            1 -> { row = 0; col = 0}
            2 -> { row = 0; col = 2}
            3 -> { row = 0; col = 4}
            4 -> { row = 2; col = 0}
            5 -> { row = 2; col = 2}
            6 -> { row = 2; col = 4}
            7 -> { row = 4; col = 0}
            8 -> { row = 4; col = 2}
            9 -> { row = 4; col = 4}
            else -> {   row = -1; col = -1
                        println("INVALID INPUT!")
                        continue@gameLoop
            }
        }
        if(isSquareAvailable(board, row, col)){
            placePiece(board, row, col, turn)
        }
        else{
            println("THE SQUARE IS ALREADY FULL! TRY ANOTHER ONE...")
            continue@gameLoop
        }

        drawBoard(board)


        if(checkWinner(board)){
            if(turn) println("PLAYER 'X' WON!")
            else println("PLAYER 'O' WON!")
            inGame = false
        }
        else if(checkDraw(board)){
            println("DRAW!")
            inGame = false
        }

        turn = !turn
    }

}
fun isSquareAvailable(board: Array<String>, row: Int, col: Int): Boolean{
    return board[row][col] == ' '
}

fun placePiece(board: Array<String>, row: Int, col: Int, turn: Boolean) {
    val ch = if(turn) 'X' else 'O'
    board[row] = board[row].substring(0, col) + ch + board[row].substring(col + 1)
}

fun drawBoard(board: Array<String>) {
    for(s in board) println(s)
}

fun initBoard(board: Array<String>) {
    board[0] = " | | "
    board[1] = "-----"
    board[2] = " | | "
    board[3] = "-----"
    board[4] = " | | "
}

fun checkWinner(board: Array<String>): Boolean{
    val series: MutableSet<Char> = mutableSetOf()

    //horizontal check
    for(i in 0..4 step 2){
        for(j in 0..4 step 2){
            series.add(board[i][j])
        }
        if(series.size == 1 && !series.contains(' '))return true
        else series.clear()
    }

    //vertical check
    for(i in 0..4 step 2){
        for(j in 0..4 step 2){
            series.add(board[j][i])
        }
        if(series.size == 1 && !series.contains(' '))return true
        else series.clear()
    }

    //diagonal check
    series.add(board[0][0])
    series.add(board[2][2])
    series.add(board[4][4])
    if(series.size == 1 && !series.contains(' '))return true
    else series.clear()

    series.add(board[0][4])
    series.add(board[2][2])
    series.add(board[4][0])
    if(series.size == 1 && !series.contains(' '))return true
    else series.clear()

    return false
}

fun checkDraw(board: Array<String>): Boolean{

    for(i in 0..4 step 2) {
        for (j in 0..4 step 2) {
            if (board[i][j] == ' ') return false
        }
    }
    return true
}