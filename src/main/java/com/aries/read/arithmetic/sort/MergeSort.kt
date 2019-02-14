package com.aries.read.arithmetic.sort

/**
 * 归并排序
 * 没有线程安全，不能异步使用
 */
class MergeSort<T : Comparable<T>> : AbsSort<T>() {

    private lateinit var aux: Array<T>


    override fun sort(a: Array<T>) {
        aux = a.copyOf()
        sortInternal(a, 0, a.lastIndex)

    }

    /**
     * 分治思想
     */
    private fun sortInternal(a: Array<T>, lo: Int, hi: Int) {
        if (lo >= hi)
            return
        val mid = (lo + hi) / 2
        sortInternal(a, lo, mid)
        sortInternal(a, mid + 1, hi)
        merge(a, lo, mid, hi)
    }


    /**
     * 将a[lo]..a[mid] 和 a[mid+1]..a[hi]归并
     * 前提是a[lo]..a[mid] 和 a[mid+1]..a[hi]中的元素分别有序
     */
    private fun merge(a: Array<T>, lo: Int, mid: Int, hi: Int) {
        for (s in lo..hi)
            aux[s] = a[s]
        var i = lo //第一个区间的起点位置
        var j = mid + 1 //第二个区间的起点位置
        for (k in lo..hi) {
            when {
                i > mid ->          a[k] = aux[j++] //第一个区间的数据已经全部排完
                j > hi ->           a[k] = aux[i++] //第二个区间的数据已经全部排完
                aux[i] > aux[j] ->  a[k] = aux[j++]
                else ->             a[k] = aux[i++]
            }

        }

    }
}