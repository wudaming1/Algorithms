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
        for (i in 1..a.lastIndex) {
            //比较时倒序比较会略微好一点。
            for (j in i - 1 downTo 0) {
                if (a[i] > a[j]) {
                    insertBefore(a, j + 1, i)
                    break
                }
            }
        }
    }

    /**
     * 插入排序
     * 进行N次循环，每次循环将无序序列中的第一个元素插入有序序列的合适位置。
     * 对部分有序的数组来排序比选择和冒泡要快。
     * 将数组由小到大排序。
     */
    fun <T : Comparable<T>> insertSortRaw(a: Array<T>) {
        for (i in 1..a.lastIndex) {
            //让数组的前i+1个元素有序
            for (j in i downTo 1) {
                if (a[j] < a[j - 1]) {
                    exch(a, j - 1, j)
                } else {
                    break
                }
            }
        }
    }

    /**
     * 希尔排序
     * 插入排序的改进版本，实现在最坏情况下的时间复杂度是N的3/2次方。
     * 当h=1时，就是[insertSortRaw]。
     */
    fun <T : Comparable<T>> shellSort(a: Array<T>) {
        var h = 1
        val N = a.size
        while (3 * h < N) h = 3 * h + 1
        //h=1时，就是插入排序
        while (h >= 1) {
            //使数组H有序
            for (i in h..a.lastIndex) {
                //使得i前面部分的数组是H有序的。
                for (j in i downTo h) {
                    if (a[j] < a[j - h]) {
                        exch(a, j, j - h)
                    }else{
                        break
                    }
                }
            }
            h /= 3
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