import java.util.*
import kotlin.collections.HashSet

class 불량사용자 {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val filtered = LinkedList<List<String>>()
        val set = HashSet<List<String>>()

        banned_id.forEach { bannedId ->
            filtered.add(
                user_id.filter {
                    if (it.length == bannedId.length) it.asSequence().zip(bannedId.asSequence()).all { (user, banned) -> banned == '*' || user == banned }
                    else false
                }
            )
        }

        fun find(picks: List<String>, nextList: List<List<String>>) {
            if (nextList.isNotEmpty())
                nextList[0].forEach {
                    if (!picks.contains(it))
                        if (nextList.size - 1 > 0) find(picks + it, nextList.takeLast(nextList.size - 1))
                        else set.add((picks + it).sorted())
                }
        }
        find(listOf(), filtered)

        return set.size
    }
}
