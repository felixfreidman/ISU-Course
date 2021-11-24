
    fun func(div: Int): Int {
        var denom = 1
        var remains = Array(div, {false})
        var rem = denom
        for (i in 0..div) {
            rem = rem % div
            if (rem == 0) return 0
            if (remains[rem]) return i
            else remains[rem] = true
            rem *= 10
        }
        return 0
    }

    fun main(argums: Array<String>) {
        var max_len = 0
        var max_div = 0
        var res = 0
        for (i in 2..1000) {
            res = func(i)
            if (res > max_len) {
                max_len = res
                max_div = i
            }
        }
        println(max_len)
        println(max_div)
    }