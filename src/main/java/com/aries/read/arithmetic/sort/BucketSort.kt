package com.aries.read.arithmetic.sort

import java.util.*

/**
 * 桶排序算法,以Int类型做为示例。
 * 每个桶代表10个数
 * mBucket[0]:0，1，2，3，4，5，6，7，8，9
 * mBucket[1]:10，11，12，13，14，15，16，17，18，19
 *
 * @param digit 最大值是几位数
 */
class BucketSort(digit: Int) : AbsSort<Int>() {
    private val mBucketCount: Int = digit

    private val mBuckets = Array(mBucketCount){ _ -> mutableListOf<Int>()}


    override fun sort(a: Array<Int>) {
        for (element in a){
            mBuckets[getBucketIndex(element)].add(element)
        }

        mBuckets.forEach { it.sort() }

        writeBackTo(a)
    }

    private fun writeBackTo(a: Array<Int>){
        var i = 0
        mBuckets.forEach {
            it.forEach{ element ->
                a[i++] = element
            }
        }
    }

    private fun getBucketIndex(a:Int):Int{
        return a/10
    }


}