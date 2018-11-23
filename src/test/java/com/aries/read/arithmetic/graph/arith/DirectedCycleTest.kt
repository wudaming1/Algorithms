package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import com.aries.read.arithmetic.graph.Graphs
import org.junit.Assert
import org.junit.Test

class DirectedCycleTest {

    /**
     * 有向无环图
     */
    @Test
    fun tinyTest(){
        val graph = Graphs.createDigraph(ResUtil.getInStream(this,"tinyDAG.txt"))
        val directedCycle = DirectedCycle(graph)
        Assert.assertEquals(false,directedCycle.hasCycle)
    }

    /**
     * 有向有环图
     */
    @Test
    fun tiny2Test(){
        val graph = Graphs.createDigraph(ResUtil.getInStream(this,"tinyDG.txt"))
        val directedCycle = DirectedCycle(graph)
        Assert.assertEquals(true,directedCycle.hasCycle)
    }
}