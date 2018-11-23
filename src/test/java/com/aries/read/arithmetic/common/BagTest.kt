package com.aries.read.arithmetic.common

import com.aries.read.ResUtil
import org.junit.Test

class BagTest {


    /**
     * 测试平均值和标准差
     */
    @Test
    fun testMeanAndDev() {

        val array = ResUtil.readIntArrayFile(this, "bag1.txt")
        val bag = Bag<Int>()
        for (i in array)
            bag.add(i)

        var sum = 0.0
        for (item in bag){
            sum +=item
        }
        val average = sum / bag.count()

        System.out.println("average is :$average")

        var std = 0.0
        for (item in bag){
            std += (item - average) * (item - average)
        }

        //这里的标准差采用无偏估计，所以分子是bag.size()-1，而不是bag.size(）
        std = Math.sqrt(std/(bag.size()-1))

        System.out.println("std is :$std")
    }

}