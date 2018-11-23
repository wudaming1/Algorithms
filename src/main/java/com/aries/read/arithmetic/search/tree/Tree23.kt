package com.aries.read.arithmetic.search.tree


/**
 * 2-3树对应的红黑树实现
 * 所以不改变树结构的操作都不需要重写
 */
class Tree23<Key : Comparable<Key>, Value> : BSTree<Key, Value>() {


    override fun deleteMax(): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMin(): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun put(key: Key, value: Value) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun delete(key: Key): Value? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private class Node<Key, Value>(key: Key, value: Value, size: Int = 1):BSTree.Node<Key, Value>(key, value, size) {
        var isRed: Boolean = true
    }
}