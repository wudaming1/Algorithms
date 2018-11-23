package com.aries.read.arithmetic

import org.junit.Assert
import org.junit.Test

class GCDTest{

    @Test
    fun testGCD(){
        val data = Array(4){ LongArray(3)}
        data[0] = longArrayOf(15,6,3)
        data[1] = longArrayOf(342,32,2)
        data[2] = longArrayOf(4695,975,15)
        data[3] = longArrayOf(0,3,3)
//        data[4] = longArrayOf(0,0,0)
//        data[5] = longArrayOf(-1,0,0)

        for (item in data){
            Assert.assertEquals("计算错误，[${item[0]},${item[1]},${item[2]}]",
                    item[2],GCD.getGCD(item[0], item[1]))
        }

    }
}