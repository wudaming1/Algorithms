package com.aries.read.arithmetic.sort

/**
 * 计算排序
 * [mStart]:数据取值范围最小值。
 * [mEnd]:数据取值范围最大值。
 */
class CountingSort(private val mStart: Int, private val mEnd: Int) : AbsSort<Int>() {

    private val mBuckets = IntArray(mEnd - mStart + 1) { 0 }

    override fun sort(a: Array<Int>) {
        a.forEach {
            mBuckets[it - mStart]++
        }

        for (i in 0..mBuckets.lastIndex) {
            if (i > 0)
                mBuckets[i] =mBuckets[i] + mBuckets[i-1]
        }

        val out = a.clone()
        a.forEach {
            var index =  mBuckets[it]
            out[index] = it
            mBuckets[it] = --index
        }

        //可以直接输出out。但是API中不带返回值。
        out.forEachIndexed { index, i ->
            a[index] = i
        }
    }

    /**
     * 一种方式
     */
    private fun writeBackTo(a: Array<Int>) {
        var i = 0
        mBuckets.forEachIndexed { index, j ->
            var count = j
            while (count > 0) {
                count--
                a[i++] = index
            }
        }
    }

    fun sort2(a: Array<Int>) {
        a.forEach {
            mBuckets[it - mStart]++
        }
        writeBackTo(a)
    }

}