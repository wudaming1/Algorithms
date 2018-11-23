package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.common.Stack
import com.aries.read.arithmetic.graph.IGraph

/**
 * 在java中对应深度优先算法的优点
 * 1、没有递归调用的栈溢出风险
 * 2、可以找到最短路径
 */
class BreadFirstPaths(graph: IGraph, private val v: Int) {
    private val marked = BooleanArray(graph.vertex())
    private val edgeTo = IntArray(graph.vertex())


    init {
        bfs(graph, v)
    }

    private fun bfs(graph: IGraph, v: Int) {
        val queue = Queue<Int>()
        queue.enqueue(v)
        marked[v] = true
        while (!queue.isEmpty()) {
            val s = queue.dequeue()
            graph.adj(s).forEach {
                if (!marked[it]) {
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
}