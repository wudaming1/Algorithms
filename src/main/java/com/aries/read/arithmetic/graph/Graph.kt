package com.aries.read.arithmetic.graph

import com.aries.read.arithmetic.common.Bag
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 * 临接表法表示的无向图
 */
class Graph<Item> : IGraph {

    //背包数组
    private val mBagList = mutableListOf<Bag<Int>>()
    private val vertexCount:Int
    private var edgeCount:Int = 0

    /**
     * 创建含有v个顶点但是不含有边的图。
     */
    constructor(v: Int){
        vertexCount = v
        for (x in 0 until vertexCount){
            mBagList.add(x,Bag())
        }
    }

    constructor(stream: InputStream){
        val reader = BufferedReader(InputStreamReader(stream))
        vertexCount = reader.readLine().toInt()
        for (x in 0 until vertexCount){
            mBagList.add(x,Bag())
        }
        reader.readLine().toInt()
        edgeCount = 0
        var edge = reader.readLine()
        while (edge != null){
            val vertexs = edge.split(" ")
            addEdge(vertexs[0].toInt(),vertexs[1].toInt())
            edge = reader.readLine()
        }

    }

    override fun vertex(): Int {
        return vertexCount
    }

    override fun edge(): Int {
        return edgeCount
    }

    override fun addEdge(v: Int, w: Int) {
        mBagList[v].add(w)
        mBagList[w].add(v)
        edgeCount++
    }

    override fun adj(v: Int): Iterator<Int> {
        return mBagList[v].iterator()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("顶点数：$vertexCount,边数：$edgeCount")
        mBagList.forEachIndexed { index, bag ->
            sb.append("\n顶点：$index：临接点：")
            val v = StringBuilder()
            bag.forEach {
                v.append("$it, ")
            }
            sb.append(v.dropLast(2))
        }

        return sb.toString()
    }
}