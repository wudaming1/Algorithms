package com.aries.read.arithmetic.graph.arith

/**
 *
 */
class QuickUnion(n:Int):UnionFind {

    /**
     * 联通分量id数组,数组的值代表联通分量的值
     */
    private val id:IntArray = IntArray(n)

    /**
     * 联通分量数
     */
    private var count  = n
    init {
        id.forEachIndexed{ index, _ -> id[index] = index }
    }
    override fun count(): Int {
        return count
    }


    override fun union(p: Int, q: Int) {
        val pId = find(p)
        val qId = find(q)
        if (pId == qId)
            return
        count--
        id[qId] = pId
    }

    /**
     * 最佳时间复杂度O(1)
     * 最差时间复杂度O(n)
     */
    override fun find(p: Int): Int {
        var target = p
        while (target != id[target]) target = id[target]
        return target

    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }
}