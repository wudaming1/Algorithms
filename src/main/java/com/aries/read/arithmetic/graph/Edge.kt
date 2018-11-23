package com.aries.read.arithmetic.graph

/**
 * 不可变对象
 * 加权边
 */
class Edge(private val v: Int, private val w: Int, val weidght: Double) : Comparable<Edge> {


    fun either() = v

    fun other(s: Int): Int {
        return if (s == v) w else v
    }

    override fun compareTo(other: Edge): Int {
        return when {
            weidght > other.weidght -> 1
            weidght < other.weidght -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        return "($v,$w)-$weidght"
    }
}