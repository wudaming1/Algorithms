package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.graph.Digraph

/**
 * 有向图中环检测算法,基于深度优先
 */
class DirectedCycle(graph: Digraph<Int>) {
    private val marked = BooleanArray(graph.vertex())
    private val onStack = BooleanArray(graph.vertex())
    var hasCycle = false
    private set

    init {
        for (v in 0 until graph.vertex()) {
            if (!marked[v]) {
                dfs(graph, v)
            }
        }
    }


    private fun dfs(graph: Digraph<Int>, v: Int) {
        marked[v] = true
        onStack[v] = true
        for (s in graph.adj(v)) {
            if (onStack[s]) {
                hasCycle = true
                return
            } else {
                if (!marked[s])
                    dfs(graph, s)
            }
        }
        onStack[v] = false

    }


}