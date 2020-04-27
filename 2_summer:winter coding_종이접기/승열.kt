import java.util.*
import kotlin.math.pow

class 종이접기 {
    fun solution(n: Int): IntArray {
        var preList = LinkedList<Int>()

        (0 until n).forEach { i ->
            val index = 2.toDouble().pow(i.toDouble()).toInt()

            val newList = LinkedList<Int>()
            var isDown = false
            (0 until index).asSequence().map {
                if (isDown) newList.add(1)
                else newList.add(0)
                isDown = !isDown
            }.map {
                if (preList.isNotEmpty()) newList.add(preList.pollFirst())
            }.toList()

            preList = newList
        }

        return preList.toIntArray()
    }
}