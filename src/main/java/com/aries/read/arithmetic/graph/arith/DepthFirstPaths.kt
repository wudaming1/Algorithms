package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Stack
import com.aries.read.arithmetic.graph.IGraph

/**
 * 虽然可以找到一条路径，但是并不保证是最短的一条。
 */
class DepthFirstPaths(graph: IGraph, private val v:Int) {
    private val marked = BooleanArray(graph.vertex())
    private val edgeTo = IntArray(graph.vertex())


    init {
        dfs(graph,v)
    }

    private fun dfs(graph: IGraph, v: Int){
        marked[v] = true
        for (e in graph.adj(v)){
            if (!marked[e]){
                edgeTo[e] = v
                dfs(graph,e)
            }
        }
    }

    fun hasPathTo(v: Int): Boolean {
        return marked[v]
    }

    fun pathTo(v: Int):Iterable<Int>{
        val stack = Stack<Int>()
        if (marked[v]){
            var current = v
            while (current != this.v){
                stack.push(current)
                current = edgeTo[current]
            }

            stack.push(this.v)
        }
        return stack
    }
}