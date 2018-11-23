package com.aries.read.arithmetic.graph.arith

class WeightedQuickUnion(n:Int):UnionFind {

    /**
     * 联通分量id数组,数组的值代表联通分量的值
     */
    private val id:IntArray = IntArray(n)
    private val idSize = IntArray(n)

    /**
     * 联通分量数
     */
    private var count  = n
    init {
        id.forEachIndexed{ index, _ -> id[index] = index }
        idSize.forEachIndexed{ index, _ -> idSize[index] = 1 }
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
        if (idSize[pId]<idSize[qId]){
            id[pId] = qId
            idSize[pId] = idSize[pId] + idSize[qId]
        }else{
            id[qId] = pId
            idSize[qId] = idSize[pId] + idSize[qId]
        }
    }

    override fun find(p: Int): Int {
        var target = p
        while (target != id[target]) target = id[target]
        return target
    }

    override fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }
}