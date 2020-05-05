import java.util.*

class 무지의먹방라이브 {
    fun solution(food_remainTimes: IntArray, k: Long): Int {
        var remainTime = k
        var circuitAmount = 0
        val remainFoods = LinkedList(food_remainTimes.mapIndexed { index, amount -> Food(index + 1, amount) }).apply { sortBy { it.amount } }

        while (true) {
            if (remainFoods.isEmpty()) return -1
            val time = (remainFoods.peek().amount - circuitAmount).toLong() * remainFoods.size

            if (remainTime < time) {
                val index = (remainTime % remainFoods.size).toInt()
                return remainFoods.apply { sortBy { it.id } }[index].id
            }

            circuitAmount = remainFoods.poll().amount
            remainTime -= time
        }
    }

    data class Food(val id: Int, var amount: Int)
}
