package com.aries.read.arithmetic.sort

interface Sorter {
    fun sort(array: Array<Any>)

    fun exch(array: Array<Any>,x: Int, y: Int) {
        val temp = array[x]
        array[x] = array[y]
        array[y] = temp
    }

    fun <T:Comparable<Any>> less(a:T,b:T)= a.compareTo(b)
}