package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import org.junit.Test

class MSTTest {

    @Test
    fun tinyTest(){
        val edgeWeightGraph = EdgeWeightGraph(ResUtil.getInStream(this,"ewg/tinyEWG.txt"))
        println(edgeWeightGraph.edge())
    }
}