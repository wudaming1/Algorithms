package com.aries.read.arithmetic.graph.arith

import com.aries.read.arithmetic.common.Bag
import com.aries.read.arithmetic.graph.Edge
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class EdgeWeightGraph {

    private val mBagList = arrayListOf<Bag<Edge>>()
    private var vertexCount:Int
    private var mEdges= Bag<Edge>()

    constructor(stream:InputStream){
        val reader = BufferedReader(InputStreamReader(stream))
        val scanner = Scanner(reader)
        vertexCount = scanner.nextInt()
        for (x in 0 until vertexCount) {
            mBagList.add(x, Bag())
        }
        scanner.nextInt()
        while (scanner.hasNextInt()) {
            addEdge(scanner.nextInt(), scanner.nextInt(),scanner.nextDouble())
        }
    }

    fun vertex(): Int {
        return vertexCount
    }

    fun edge(): Int {
        return mEdges.size()
    }

    fun addEdge(v: Int, w: Int,weight:Double) {
        val edge = Edge(v,w,weight)
        mEdges.add(edge)
        mBagList[v].add(edge)
        mBagList[w].add(edge)
    }

    /**
     * 返回所有与v关联的边
     */
    fun adj(v: Int): Iterator<Edge> {
        return mBagList[v].iterator()
    }

    /**
     * 返回所有边
     */
    fun edges(v: Int): Iterator<Edge> {
        return mEdges.iterator()
    }
}