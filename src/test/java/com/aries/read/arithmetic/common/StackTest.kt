package com.aries.read.arithmetic.common

import com.aries.read.ResUtil
import org.junit.Test

class StackTest {


    @Test
    fun testInAndOut(){
        val list = ResUtil.readStringArrayFile(this,"stack.txt")
        val stack = Stack<String>()

        list.forEach {
            if (it == "-"){
                stack.pop()
            }else{
                stack.push(it)
            }
            System.out.println(stack.toString())
        }
    }
}