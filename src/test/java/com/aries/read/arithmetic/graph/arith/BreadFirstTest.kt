package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import com.aries.read.arithmetic.graph.Graphs
import org.junit.Test

/**
 * 广度优先算法测试用例。
 */
class BreadFirstTest {

    @Test
    fun tinyPathTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val paths = BreadFirstPaths(graph,0)
        println(paths.hasPathTo(1))
        println(paths.hasPathTo(7))
        println(paths.hasPathTo(9))

        paths.pathTo(3).forEach {
            print("-$it-")
        }
    }


    @Test
    fun mediumPathTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"mediumG.txt"))
        val paths = BreadFirstPaths(graph,0)
        println(paths.hasPathTo(1))
        println(paths.hasPathTo(7))
        println(paths.hasPathTo(9))

        paths.pathTo(3).forEach {
            print("-$it-")
        }
    }

    @Test
    fun largePathTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"largeG.txt"))
        val paths = BreadFirstPaths(graph,0)
        println(paths.hasPathTo(1))
        println(paths.hasPathTo(7))
        println(paths.hasPathTo(9))

        paths.pathTo(3).forEach {
            print("-$it-")
        }
    }

    @Test
    fun tinySearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val paths = BreadFirstSearch(graph,0)
        println(paths.connected(1))
        println(paths.connected(7))
        println(paths.connected(9))

        println(paths.count())
        println(paths.isConnectedGraph())

    }

    @Test
    fun mediumSearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"mediumG.txt"))
        val paths = BreadFirstSearch(graph,0)
        println(paths.connected(1))
        println(paths.connected(7))
        println(paths.connected(9))

        println(paths.count())
        println(paths.isConnectedGraph())

    }

    @Test
    fun largeSearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"largeG.txt"))
        val paths = BreadFirstSearch(graph,0)
        println(paths.connected(1))
        println(paths.connected(7))
        println(paths.connected(9))

        println(paths.count())
        println(paths.isConnectedGraph())

    }

    @Test
    fun tinyCCTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val cc = BreadFirstCC(graph)
        printCC(cc)

    }

    @Test
    fun largeCCTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"largeG.txt"))
        val cc = BreadFirstCC(graph)
        printCC(cc)

    }

    private fun printCC(cc:BreadFirstCC){
        println(cc.count)
        println(cc.connected(0,1))
        println(cc.connected(0,7))
        println(cc.id(0))
        println(cc.id(8))
        println(cc.id(10))
    }
}