package com.aries.read.arithmetic.common


fun <T> Array<T>.copyTo(target: Array<T>) {
    if (target.size < size)
        throw RuntimeException("新数组大小小于原数组！")

    this.forEachIndexed { index, t ->
        target[index] = t
    }

}


object Utils {

    /**
     * 数组二分查找
     * @param data 输入数组
     * @param key 目标值
     * @param iHi 查找范围上限
     * @param iLo 查找范围下限
     * @return [key]在数组中的下标。-1代表数据中没有[key]
     */
    fun <T : Comparable<T>> binarySearch(data: Array<T>, key: T, iHi: Int = data.size - 1, iLo: Int = 0): Int {
        var hi = iHi
        var lo = iLo
        var mid: Int
        while (lo <= hi) {
            mid = (lo + hi) / 2
            when {
                key < data[mid] -> hi = mid - 1
                key > data[mid] -> lo = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    /**
     * 数组二分查找排名
     * @param data 输入数组
     * @param key 目标值
     * @param iHi 查找范围上限
     * @param iLo 查找范围下限
     * @return [key]前面有多少个值。
     */
    fun <T : Comparable<T>> binaryRank(data: Array<T>, key: T, iHi: Int = data.size - 1, iLo: Int = 0): Int {
        if (iHi < iLo || data.isEmpty())
            return 0
        var hi = iHi
        var lo = iLo
        var mid: Int = iLo
        while (lo <= hi) {
            mid = (hi + lo) / 2
            when {
                key < data[mid] -> hi = mid - 1
                key > data[mid] -> lo = mid + 1
                else -> return mid - iLo + 1
            }
        }
        return mid - iLo + if (key < data[mid]) 0 else 1
    }


}