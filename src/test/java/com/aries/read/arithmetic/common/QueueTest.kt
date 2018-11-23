package com.aries.read.arithmetic.common

import com.aries.read.ResUtil
import org.junit.Test

class QueueTest {

    /**
     * 测试平插入和删除
     */
    @Test
    fun testEnqueueAndDeQueue() {
        val array = ResUtil.readIntArrayFile(this, "bag1.txt")
        val queue = Queue<Int>()
        array.forEach { queue.enqueue(it) }

        queue.forEach {
            System.out.print(it)
            System.out.print('-') }
        System.out.println()

        while (!queue.isEmpty()){
            System.out.print(queue.dequeue())
            System.out.print('-')
        }



    }
}