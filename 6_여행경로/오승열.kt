import java.util.*

class 여행경로 {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val map = hashMapOf<String, List<Node>>()
        tickets.forEach { map[it[0]] = map.getOrDefault(it[0], listOf()) + Node(it[1]) }

        val list = LinkedList<Node>()
        fun search(name: String) {
            val tmp = map[name]?.sortedBy { it.name } ?: listOf()

            if (!tmp.all { it.visited }) {
                tmp.forEach {
                    if (!it.visited) {
                        it.visited = true
                        list.add(it)
                        if (list.size != tickets.size) search(it.name)
                    }
                }
            }
            if (list.size != tickets.size) list.pollLast()?.visited = false
        }

        search("ICN")
        return arrayOf("ICN") + list.map { it.name }.toTypedArray()
    }

    data class Node(val name: String, var visited: Boolean = false)
}
