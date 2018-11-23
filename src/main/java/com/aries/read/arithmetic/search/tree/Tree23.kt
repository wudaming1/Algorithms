package com.aries.read.arithmetic.search.tree

import com.aries.read.arithmetic.search.SST

/**
 * 二三树对应的红黑树实现
 */
class Tree23<Key : Comparable<Key>, Value> : SST<Key, Value> {

    override fun min(): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun max(): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun floor(key: Key): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ceiling(key: Key): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun rank(key: Key): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun select(k: Int): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMax(): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMin(): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(lo: Key, hi: Key): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(key: Key, value: Value) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: Key): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(key: Key): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keys(lo: Key, hi: Key): Iterator<Key> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun keys(): Iterator<Key> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class Node<Key, Value>(val key: Key, var value: Value, var isRed: Boolean = true, var size: Int = 1) {
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
    }
}