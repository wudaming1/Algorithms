package com.aries.read.arithmetic.graph

import com.aries.read.arithmetic.common.Bag
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


/**
 * 有向图
 */
class Digraph<Item> : IGraph {

    //背包数组
    private val mBagList = mutableListOf<Bag<Int>>()
    private val vertexCount: Int
    private var edgeCount: Int = 0
    //反向图
    private var reverseDigraph: Digraph<Item>? = null

    /**
     * 创建含有v个顶点但是不含有边的图。
     */
    constructor(v: Int) {
        vertexCount = v
        for (x in 0 until vertexCount) {
            mBagList.add(x, Bag())
        }
    }


    constructor(stream: InputStream) {
        val reader = BufferedReader(InputStreamReader(stream))
        val scanner = Scanner(reader)
        vertexCount = scanner.nextInt()
        for (x in 0 until vertexCount) {
            mBagList.add(x, Bag())
        }
        scanner.nextInt()
        edgeCount = 0
        while (scanner.hasNextInt()) {
            addEdge(scanner.nextInt(), scanner.nextInt())
        }

    }


    override fun vertex(): Int {
        return vertexCount
    }

    override fun edge(): Int {
        return edgeCount
    }

    /**
     * 添加一条由顶点[v]指向顶点[w]的边
     */
    override fun addEdge(v: Int, w: Int) {
        mBagList[v].add(w)
        edgeCount++
    }

    /**
     * 由顶点v指向的节点集合
     */
    override fun adj(v: Int): Iterator<Int> {
        return mBagList[v].iterator()
    }

    /**
     * 反向图，反转所有边的方向。反向图有对称性。
     */
    fun reverse(): Digraph<Item> {
        if (reverseDigraph == null) {
            val digraph = Digraph<Item>(vertexCount)
            for (v in 0 until vertexCount) {
                for (w in adj(v)) {
                    digraph.addEdge(w, v)
                }
            }
            digraph.reverseDigraph = this
            reverseDigraph = digraph
        }
        return reverseDigraph!!
    }

    override fun toString(): String {
        return "图有${vertexCount}个顶点，${edgeCount}条边"
    }
}