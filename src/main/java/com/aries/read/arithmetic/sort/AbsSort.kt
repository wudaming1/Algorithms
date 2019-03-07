package com.aries.read.arithmetic.sort

abstract class AbsSort<T : Comparable<T>> {


    abstract fun sort(a: Array<T>)


    fun swap(a: Array<T>, i: Int, j: Int) {
        SortUtils.exch(a, i, j)
    }
}