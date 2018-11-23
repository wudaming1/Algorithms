package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.common.Stack
import com.aries.read.arithmetic.graph.IGraph

/**
 * 在java中对应深度优先算法的优点
 * 1、没有递归调用的栈溢出风险
 */
class BreadFirstCC(graph: IGraph) {
    private val marked = BooleanArray(graph.vertex())
    private val id = IntArray(graph.vertex())
    var count = 0
    private set

    init {
        for (v in 0 until graph.vertex()){
            if (!marked[v]){
                count++
                bfs(graph, v)
            }
        }

    }

    private fun bfs(graph: IGraph, v: Int) {
        val queue = Queue<Int>()
        queue.enqueue(v)
        marked[v] = true
        while (!queue.isEmpty()) {
            val s = queue.dequeue()
            id[s] = count
            graph.adj(s).forEach {
                if (!marked[it]) {
                    marked[it] = true
                    queue.enqueue(it)
                }
            }

        }
    }


    /**
     * 连通分量数量
     */
    fun count() = count

    /**
     * [w]节点是否与[v]节点连通
     */
    fun connected(v:Int,w: Int): Boolean {
        return id[w] == id[v]
    }

    fun id(v:Int) = id[v]
}