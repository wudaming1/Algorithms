package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.common.Stack
import com.aries.read.arithmetic.graph.IGraph

/**
 * 在java中对应深度优先算法的优点
 * 1、没有递归调用的栈溢出风险
 */
class BreadFirstSearch(graph: IGraph, private val v: Int) {
    private val marked = BooleanArray(graph.vertex())
    private val edgeTo = IntArray(graph.vertex())
    private var count = 0

    init {
        bfs(graph, v)
    }

    private fun bfs(graph: IGraph, v: Int) {
        val queue = Queue<Int>()
        queue.enqueue(v)
        marked[v] = true
        count++
        while (!queue.isEmpty()) {
            val s = queue.dequeue()
            graph.adj(s).forEach {
                if (!marked[it]) {
                    count++
                    marked[it] = true
                    queue.enqueue(it)
                    edgeTo[it] = s
                }
            }

        }
    }

    fun hasPathTo(v: Int): Boolean {
        return marked[v]
    }

    fun pathTo(v: Int): Iterable<Int> {
        val stack = Stack<Int>()
        if (marked[v]) {
            var current = v
            while (current != this.v) {
                stack.push(current)
                current = edgeTo[current]
            }

            stack.push(this.v)
        }
        return stack
    }

    /**
     * 与[v]连通的节点的数量
     */
    fun count() = count

    /**
     * w节点是否与[v]节点连通
     */
    fun connected(w:Int):Boolean{
        if (w > marked.size-1)
            throw RuntimeException("该图没有{$w}节点")

        return marked[w]
    }

    /**
     * 是否是连通图
     */
    fun isConnectedGraph():Boolean{
        return count == marked.size
    }
}