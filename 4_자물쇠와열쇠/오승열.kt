class 자물쇠와열쇠 {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val border = key.size - 1
        val size = lock.size

        var tmp: Array<IntArray> = key
        val rotateList = Array(4) {
            val new = Array(tmp.size) { IntArray(tmp.size) { 0 } }
            tmp.forEachIndexed { index1, ints ->
                ints.forEachIndexed { index2, i ->
                    new[index2][index1] = i
                }
            }

            new.forEachIndexed { index, ints -> new[index] = ints.reversedArray() }
            tmp = new

            new
        }

        val range = 0 until border + lock.size
        range.forEach { y ->
            range.forEach { x ->
                rotateList.any { key ->
                    val temp = Array(lock.size) { index -> lock[index].clone() }
                    key.forEachIndexed { _y, ints ->
                        if (y + _y >= border && y + _y < border + size) {
                            ints.forEachIndexed { _x, i ->
                                if (x + _x >= border && x + _x < border + size) {
                                    temp[y + _y - border][x + _x - border] += i
                                }
                            }
                        }
                    }
                    temp.all { ints -> ints.all { it == 1 } }
                }.let { if (it) return true }
            }
        }

        return false
    }
}
