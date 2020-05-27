class 승열 {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        val stack = Stack<Int>()

        moves.map { it - 1 }.forEach { column ->
            for (index in 0 until board.size) {
                val doll = board[index][column]

                if (doll != 0) {
                    board[index][column] = 0
                    if (stack.check(doll)) answer += 2
                    else stack.add(doll)
                    break
                }
            }
        }

        return answer
    }

    private fun Stack<Int>.check(doll: Int): Boolean {
        val result = isNotEmpty() && peek() == doll
        if (result) pop()
        return result
    }
}
