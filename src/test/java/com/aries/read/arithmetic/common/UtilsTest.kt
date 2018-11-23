package com.aries.read.arithmetic.common

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {
    @Test
    fun binarySearch() {
        val input = arrayOf('A','C','E','R','S')
        val index = Utils.binarySearch(input,'C')
        println(index)
    }

    @Test
    fun binaryRank() {
        val input = Array<Int>(3, { i -> (i + 1) * 3 })
        //测试值0，1，3，5，6，7，9，10
        assertEquals(0, Utils.binaryRank(input, 0))
        assertEquals(0, Utils.binaryRank(input, 1))
        assertEquals(1, Utils.binaryRank(input, 3))
        assertEquals(1, Utils.binaryRank(input, 5))
        assertEquals(2, Utils.binaryRank(input, 6))
        assertEquals(2, Utils.binaryRank(input, 7))
        assertEquals(3, Utils.binaryRank(input, 9))
        assertEquals(3, Utils.binaryRank(input, 10))
        //边界测试
        assertEquals(0, Utils.binaryRank(input, 10,1,2))
        //边界测试
        assertEquals(1, Utils.binaryRank(input, 10,1,1))
        //边界测试
        assertEquals(0, Utils.binaryRank(Array<Int>(0, { i -> (i + 1) * 3 }), 10))
    }


    @Test
    fun rangeTest(){
        for (index in 0 until 3)
            print("$index-")


        println()
        for (index in 0..3)
            print("$index-")

        println()
        for (index in 3 downTo 0)
            print("$index-")
    }


}