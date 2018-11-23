package com.aries.read.arithmetic.search

import com.aries.read.arithmetic.common.Stack

/**
 * 链表方式无序符号表
 * 核心是[getNode]方法
 * 插入、查找、删除都依赖于该方法。
 * 在含有N个键值对的表中，为命中查找和插入都需要N次比较
 * 向一个空表插入N个不同键需要~NxN/2次比较。
 */
class LinkST<Key, Value> : ST<Key, Value> {

    private var headNode: Node<Key, Value>? = null
    private var size = 0

    override fun put(key: Key, value: Value) {
        val find = getNode(key)
        find?.apply {
            this.value = value
            return
        }

        val node = Node(key, value)
        node.next = headNode
        headNode = node
        size++
    }

    /**
     * 命中情况：平均每次查找比较[size]/2次
     * 未命中情况：每次查找比较[size]次
     */
    private fun getNode(key: Key):Node<Key, Value>?{
        var cursor = headNode
        while (cursor != null){
            if (cursor.key == key)
                return cursor

            cursor = cursor.next
        }
        return null
    }

    override fun get(key: Key): Value? {
        return getNode(key)?.value
    }

    /**
     * 及时删除
     */
    override fun delete(key: Key): Value? {
        var cursor = headNode
        var previous : Node<Key, Value>? = null
        while (cursor != null){
            if (cursor.key == key) {
                size--
                previous?.next = cursor.next
                return cursor.value
            }
            previous = cursor
            cursor = cursor.next
        }
        return null
    }

    override fun size(): Int {
        return size
    }

    override fun keys(): Iterator<Key> {
        val iterable = STIterable()
        var cursor = headNode
        while (cursor != null){
            iterable.add(cursor.key)
            cursor = cursor.next
        }
        return iterable.iterator()
    }

    private class Node<Key, Value>(val key: Key, var value: Value) {
        var next: Node<Key, Value>? = null
    }

    private inner class STIterable : Iterable<Key> {
        private val stack =Stack<Key>()
        override fun iterator(): Iterator<Key> {
            return stack.iterator()
        }

        fun add(key: Key){
            stack.push(key)
        }


    }
}