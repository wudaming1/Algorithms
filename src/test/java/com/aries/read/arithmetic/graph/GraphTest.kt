package com.aries.read.arithmetic.graph

import com.aries.read.ResUtil
import org.junit.Test

class GraphTest {

    @Test
    fun createGraphFromTxt() {
        val graph = Graph<Int>(ResUtil.getInStream(this,"tinyG.txt"))
        System.out.println(graph)

    }
}