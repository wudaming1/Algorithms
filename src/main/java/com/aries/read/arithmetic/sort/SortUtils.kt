package com.aries.read.arithmetic.sort

/**
 * 包含一些简单排序算法和辅助方法
 */
object SortUtils {

    /**
     * 冒泡排序
     * 进行N次循环，每次循环将无序序列中的最小值移动到有序序列的末尾。
     */
    fun <T : Comparable<T>> bubbleSort(a: Array<T>) {
        for (i in 0..a.lastIndex) {
            for (j in 0 until a.lastIndex - i) {
                if (a[j] < a[j + 1])
                    exch(a, j, j + 1)
            }
        }
    }

    /**
     * 选择排序
     * 进行N次循环，每次循环将无序序列中的最小值放入有序序列的末尾。
     */
    fun <T : Comparable<T>> selectSort(a: Array<T>) {
        for (i in 0 until a.lastIndex) {
            var minIndex = i
            for (j in i + 1..a.lastIndex) {
                if (a[j] < a[minIndex])
                    minIndex = j
            }
            if (minIndex != i)
                exch(a, minIndex, i)
        }
    }

    /**
     * 插入排序
     * 进行N次循环，每次循环将无序序列中的第一个元素插入有序序列的合适位置。
     * 对部分有序的数组来排序比选择和冒泡要快。
     */
    fun <T : Comparable<T>> insertSort(a: Array<T>) {
        for (i in 0..a.lastIndex) {
            //比较时倒序比较会略微好一点。
            for (j in i-1 downTo 0) {
                if (a[i] < a[j]) {
                    insertBefore(a, j, i)
                }
            }
        }
    }


    /**
     * 将[array]数组中第[valueIndex]位置的元素插入到[insertIndex]位置，原来数据依次向后位移。
     * [valueIndex] 必须大于 [insertIndex]
     */
    private fun <T : Comparable<T>> insertBefore(array: Array<T>, insertIndex: Int, valueIndex: Int) {
        val insertValue = array[valueIndex]
        for (i in valueIndex downTo insertIndex) {
            if (i > insertIndex)
                array[i] = array[i - 1]
            else
                array[i] = insertValue

        }

    }

    fun <T> exch(a: Array<T>, i: Int, j: Int) {
        val temp = a[i]
        a[i] = a[j]
        a[j] = temp
    }

    fun <T : Comparable<T>> isSorted(a: Array<T>): Boolean {
        for (i in 1 until a.size) {
            if (a[i - 1] > a[i])
                return false
        }
        return true
    }

    fun <T : Comparable<T>> less(a: T, b: T): Boolean {
        return a < b
    }
}