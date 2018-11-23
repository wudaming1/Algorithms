package com.aries.read.arithmetic.graph

import java.io.File
import java.io.InputStream

/**
 * 图处理工具类
 * 部分方法需要区别对待有向图和无向图。
 */
object Graphs {

    /**
     * 计算graph中v的度数
     */
    fun degree(graph: IGraph,v:Int):Int{
        var degree = 0
        val iterable = graph.adj(v)
        for (x in iterable){
            degree++
        }
        return degree
    }

    /**
     * 计算graph中所有顶点的最大度数
     */
    fun maxDegree(graph: IGraph):Int{
        var max = 0
        for (x in 0 until graph.vertex()){
            val d = degree(graph,x)
            if (d > max)
                max = d
        }

        return max
    }

    fun avgDegree(graph: IGraph):Double{
        return 2.0 * graph.edge() / graph.vertex()
    }

    fun numberOfSelfLoops(graph: IGraph):Int{
        var count  = 0
        for (i in 0 until graph.vertex()){
            graph.adj(i).forEach{
                if (i == it) count++
            }
        }
        return count/2
    }

    /**
     * 生成无向图
     */
    fun createGraph(file:File):IGraph{
        val inStream = file.inputStream()
        return Graph<Int>(inStream)
    }

    /**
     * 生成有向图
     */
    fun createDigraph(inputStream: InputStream):Digraph<Int>{
        return Digraph<Int>(inputStream)
    }
}