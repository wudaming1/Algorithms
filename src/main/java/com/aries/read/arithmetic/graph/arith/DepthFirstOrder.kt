package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.common.Stack
import com.aries.read.arithmetic.graph.Digraph
import com.aries.read.arithmetic.graph.IGraph

/**
 * 深度优先搜索排序
 */
class DepthFirstOrder(graph: Digraph<Int>) {

    private val marked = BooleanArray(graph.vertex())
    //所有顶点的前序排序
    private val pre = Queue<Int>()
    //所有顶点的后序排序
    private val post = Queue<Int>()
    //所有顶点的逆后序排序，拓扑排序
    private val reversePost = Stack<Int>()

    init {
        for (v in 0 until graph.vertex()){
            if (!marked[v])
                dfs(graph, v)
        }
    }

    private fun dfs(graph:IGraph,v:Int){
        pre.enqueue(v)
        marked[v] = true
        for (s in graph.adj(v)){
            if (!marked[s]){
                dfs(graph,s)
            }
        }
        post.enqueue(v)
        reversePost.push(v)
    }

    fun pre(): Iterator<Int> {
        return pre.iterator()
    }

    fun post(): Iterator<Int> {
        return post.iterator()
    }

    fun reversePost(): Iterator<Int> {
        return reversePost.iterator()
    }
}