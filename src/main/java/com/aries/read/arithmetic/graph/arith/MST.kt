package com.aries.read.arithmetic.graph.arith

interface MST {
    /**
     * 最小生成树的所有边
     */
    fun edges():Iterable<Int>

    /**
     * 最小生成树的权值
     */
    fun weight():Double
}