package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.graph.IGraph

/**
 * 基于深度优先算法的连通分量算法
 */
class CC(graph: IGraph) {


    /**
     * 标记算法的标记数组。
     */
    private val marked = BooleanArray(graph.vertex())
    /**
     * 下标代表顶点，对应值代表连通分量标识
     */
    private val id = IntArray(graph.vertex())
    /**
     * 连通分量数量
     */
    var count = 0
        private set


    init {
        for (s in 0 until graph.vertex()) {
            if (!marked[s]) {
                //每当程序运行到这里，代表是一个全新的连通分量的遍历开始。
                count++
                marked[s] = true
                id[s] = count
                //深度优先算法的入口
                dfs(graph, s)
            }
        }
    }

    /**
     * 深度优先标记算法,每次递归完成代表遍历完成一个连通分量
     * 递归调用可能会导致栈溢出，其方法深度与虚拟机分配的栈空间大小和递归方法的栈帧大小有关。
     * 在1000000顶点和7586063条边时，栈溢出。
     */
    private fun dfs(graph: IGraph, v: Int) {
        for (s in graph.adj(v)) {
            if (!marked[s]) {
                id[s] = count
                marked[s] = true
                dfs(graph, s)
            }
        }

    }

    /**
     * 顶点v与顶点w是否连通
     */
    fun connected(v: Int, w: Int): Boolean {
        return id[v] == id[w]
    }

    /**
     * 顶点v的连通分量标识
     */
    fun id(v: Int): Int {
        return id[v]
    }

}