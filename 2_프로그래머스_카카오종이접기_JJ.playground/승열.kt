import java.util.*
import kotlin.math.pow

class 종이접기 {
    fun solution(n: Int): IntArray {
        var preList = LinkedList<Int>()

        (0 until n).forEach { i ->
            val index = 2.toDouble().pow(i.toDouble()).toInt()
            val newList = LinkedList<Int>()
            var isDown = false
            (0 until index).forEach { _ ->
                newList.add(if (isDown) 1 else 0)
                if (preList.isNotEmpty()) newList.add(preList.pollFirst())
                isDown = !isDown
            }
            preList = newList
        }

        return preList.toIntArray()
    }
}
