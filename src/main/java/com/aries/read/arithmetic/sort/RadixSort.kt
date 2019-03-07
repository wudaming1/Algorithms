package com.aries.read.arithmetic.sort


/**
 * 基数排序
 * 基数：除开位置信息外的所有元素。
 * exp：数值-[1,2,3,4,5,6,7,8,9,0]
 *      字符串-字母表
 *
 * 这里是整数类型，基数很明显。
 *
 * @param mDigit 最大值是几位数
 */
class RadixSort(private val mDigit: Int) : AbsSort<Int>() {

    override fun sort(a: Array<Int>) {

        for (i in 0..mDigit) {
            sortByDigit(i, a)
        }

    }

    /**
     * 因为每次按位排序都是原地的，所以对于高位相同的元素，并不会打乱已经排好的前后顺序。
     * 数学归纳法可以证明从低位到高位排序后整体是有序的。
     */
    private fun sortByDigit(digit: Int, a: Array<Int>) {
        val bucket = Array(10) { mutableListOf<Int>() }
        a.forEach {
            bucket[getDigitNumber(digit, it)].add(it)
        }
        writeBackTo(bucket, a)

    }

    /**
     * 取出[a]在第[digit]位上的值
     */
    private fun getDigitNumber(a: Int, digit: Int): Int {
        val divisor = Math.pow(10.toDouble(), (digit - 1).toDouble())
        val division = a / divisor
        return (division % (divisor * 10)).toInt()
    }

    private fun writeBackTo(bucket: Array<MutableList<Int>>, a: Array<Int>) {
        var i = 0
        bucket.forEach {
            it.forEach { element ->
                a[i++] = element
            }
        }

    }
}