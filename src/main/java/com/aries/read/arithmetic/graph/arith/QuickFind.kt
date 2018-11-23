package com.aries.read.arithmetic.graph.arith

/**
 * 联通性查找算法。这个算法的union方法的时间复杂度非常高
 */
class QuickFind(n:Int):UnionFind {
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

    /**
     * 连接两个节点。
     * 在两个节点不连接的情况下，时间复杂度O(n)
     */
    override fun union(p:Int,q:Int){
        val pId = id[p]
        val qId = id[q]
        if (pId == qId)
            return
        count--
        for (i in 0 until id.size){
            if (id[i] == qId)
                id[i] = pId
        }

    }

    /**
     * 找到p所在的分量的标识，时间复杂度O(1)
     */
    override fun find(p:Int):Int = id[p]


    /**
     * q与p是否联通
     */
    override fun connected(p:Int,q:Int):Boolean{
        return find(p) == find(q)
    }

    override fun count(): Int {
        return count
    }

}