package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import com.aries.read.arithmetic.graph.Graphs
import org.junit.Test

class DepthFirstOrderTest {

    @Test
    fun tinyTest(){
        val graph = Graphs.createDigraph(ResUtil.getInStream(this,"tinyDAG.txt"))
        val depthFirstOrder = DepthFirstOrder(graph)
        println("打印前序顺序")
        for (i in depthFirstOrder.pre()){
            print("-$i-")
        }
        println()

        println("打印后序顺序")
        for (i in depthFirstOrder.post()){
            print("-$i-")
        }

        println()
        println("打印拓扑顺序")
        for (i in depthFirstOrder.reversePost()){
            print("-$i-")
        }

    }
}