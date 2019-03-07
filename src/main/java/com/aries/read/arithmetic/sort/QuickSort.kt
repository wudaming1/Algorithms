package com.aries.read.arithmetic.sort

class QuickSort<T : Comparable<T>> : AbsSort<T>() {

    override fun sort(a: Array<T>) {
        val start = 0
        val end = a.lastIndex
        sortInternal(a, start, end)

    }

    private fun sortInternal(a: Array<T>, start: Int, end: Int) {
        val partitionIndex = partition(a, start, end)
        if (partitionIndex - 1 > start)
            sortInternal(a, start, partitionIndex - 1)
        if (partitionIndex + 1 < end)
            sortInternal(a, partitionIndex + 1, end)

    }

    private fun partition(a: Array<T>, start: Int, end: Int): Int {
        if (start >= end)
            return start
        val partitionElement = a[start]
        var i = start
        var j = end + 1
        while (true) {
            while (++i < j) {
                if (a[i] >= partitionElement)
                    break
            }

            while (--j > i) {
                if (a[j] <= partitionElement)
                    break

            }
            swap(a, i, j)
            if (i > j)
                break
        }
        swap(a, start, j)
        return j

    }
}