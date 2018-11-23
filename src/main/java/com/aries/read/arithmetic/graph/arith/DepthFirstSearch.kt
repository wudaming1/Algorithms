package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.graph.IGraph


/**
 * 图的连通性算法。
 * 深度优先算法在java中不可避免栈溢出，所以只能做个理论算法。
 * 功能：
 * 1、图[graph]中与[s]连通的顶点数量，包括s本身。
 * 2、判断任意顶点与[s]的连通性。
 */
class DepthFirstSearch(private val graph: IGraph, private val s: Int) {
    private val marked = BooleanArray(graph.vertex())
    private var count = 0


    init {
        dfs(graph, s)
    }

    /**
     * 深度优先标记算法,每次递归完成代表遍历完成一个连通分量
     * 递归调用可能会导致栈溢出，其方法深度与虚拟机分配的栈空间大小和递归方法的栈帧大小有关。
     * 在1000000顶点和7586063条边时，栈溢出。
     */
    private fun dfs(graph: IGraph, v: Int) {
        marked[v] = true
        count++
        for (s in graph.adj(v)) {
            if (!marked[s]) {
                dfs(graph, s)
            }
        }

    }

    /**
     * 与[s]连通的节点的数量
     */
    fun count() = count

    /**
     * [w]节点是否与[s]节点连通
     */
    fun connected(w: Int): Boolean {
        if (w > marked.size - 1)
            throw RuntimeException("该图没有{$w}节点")

        return marked[w]
    }

    /**
     * 是否是连通图
     */
    fun isConnectedGraph(): Boolean {
        return count == marked.size
    }

}