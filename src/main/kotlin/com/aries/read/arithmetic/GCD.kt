package com.aries.read.arithmetic


/**
 * 欧几里得最大公约数算法
 */
object GCD {


    fun getGCD(x: Long, y: Long): Long {
        if (x < 0 || y < 0) {
            throw RuntimeException("只能求取正整数的公约数！x:$x,y:$y")
        }

        if (x == 0L) {
            return y
        }

        if (y == 0L) {
            return x
        }

        if (x == y) {
            return x
        }

        val a = Math.max(x, y)
        val b = Math.min(x, y)

        return getGCD(b, a % b)
    }

}