package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import com.aries.read.arithmetic.graph.Graphs
import org.junit.Test

/**
 * 基于深度优先原理算法的测试用例，由于深度优先算法递归调用会导致栈溢出，
 * 所以largeG.txt的输入无法测试。
 */
class DepthFirstTest {

    @Test
    fun tinySearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val depthFirstSearch = DepthFirstSearch(graph,0)
        printSearchResult(depthFirstSearch)
    }

    @Test
    fun mediumSearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"mediumG.txt"))
        val depthFirstSearch = DepthFirstSearch(graph,0)
        printSearchResult(depthFirstSearch)
    }

    @Test
    fun largeSearchTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"largeG.txt"))
        val depthFirstSearch = DepthFirstSearch(graph,0)
        printSearchResult(depthFirstSearch)
    }

    private fun printSearchResult(depthFirstSearch:DepthFirstSearch){
        println(depthFirstSearch.connected(7))
        println(depthFirstSearch.connected(2))
        println(depthFirstSearch.isConnectedGraph())
        println(depthFirstSearch.count())
    }

    @Test
    fun tinyPathTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val depthFirstPaths = DepthFirstPaths(graph,0)
        printPathResult(depthFirstPaths)
    }

    @Test
    fun mediumPathTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"mediumG.txt"))
        val depthFirstPaths = DepthFirstPaths(graph,0)
        printPathResult(depthFirstPaths)
    }

    private fun printPathResult(paths: DepthFirstPaths){
        println(paths.hasPathTo(1))
        println(paths.hasPathTo(7))
        println(paths.hasPathTo(9))

        paths.pathTo(3).forEach {
            print("-$it-")
        }
    }

    @Test
    fun tinyCCTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"tinyG.txt"))
        val cc = CC(graph)
        printCC(cc)
    }

    @Test
    fun mediumCCTest(){
        val graph = Graphs.createGraph(ResUtil.getFile(this,"mediumG.txt"))
        val cc = CC(graph)
        printCC(cc)
    }


    private fun printCC(cc:CC){
        println(cc.count)
        println(cc.connected(0,1))
        println(cc.connected(0,7))
        println(cc.id(0))
        println(cc.id(8))
        println(cc.id(10))
    }
}