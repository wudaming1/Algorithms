package com.aries.read.arithmetic.graph

import java.io.InputStream

interface IGraph {

    /**
     * 顶点数
     */
    fun vertex(): Int

    /**
     * 边数
     */
    fun edge(): Int

    /**
     * 向图中添加一条边v-w
     */
    fun addEdge(v: Int, w: Int)

    /**
     * 和v相邻的所有顶点
     */
    fun adj(v: Int): Iterator<Int>

    companion object {

    }

}