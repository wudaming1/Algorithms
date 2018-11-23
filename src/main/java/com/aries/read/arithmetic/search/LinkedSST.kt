package com.aries.read.arithmetic.search

import com.aries.read.arithmetic.common.Queue

/**
 * 基于双链表的有序符号表
 * 向一个空表插入N个不同键需要比较次数小于NxN/2。
 */
class LinkedSST<Key : Comparable<Key>, Value> : SST<Key, Value> {


    private var headNode: Node<Key, Value>? = null
    private var tailNode: Node<Key, Value>? = null
    private var size = 0

    override fun put(key: Key, value: Value) {
        var cursor = headNode
        while (cursor != null) {
            val result = cursor.key.compareTo(key)
            when {
                result == 0 -> {
                    cursor.value = value
                    return
                }
                (result < 0) -> cursor = cursor.next
                else -> {
                    val newNode = Node(key, value)
                    newNode.insertBefore(cursor)
                    if (headNode == cursor)
                        headNode = newNode
                    size++
                    return
                }
            }
        }
        //链表尾部插入
        val newNode = Node(key, value)
        if (headNode == null) {
            headNode = newNode
        }
        tailNode?.setNextNode(newNode)
        tailNode = newNode
        size++
    }

    override fun get(key: Key): Value? {
        val node = getNode(key)
        return node?.value
    }

    override fun delete(key: Key): Value? {
        val node = getNode(key)
        if (node == headNode) {
            headNode = node?.next
        }
        if (node == tailNode){
            tailNode = node?.prev
        }
        node?.delete()
        return node?.value
    }


    /**
     * 命中情况：平均每次查找比较[size]/2次
     * 未命中情况：不太好考虑平均低于[size]
     */
    private fun getNode(key: Key): Node<Key, Value>? {
        var cursor = headNode
        while (cursor != null) {
            val result = cursor.key.compareTo(key)
            when {
                result == 0 -> return cursor
                result < 0 -> cursor = cursor.next
                else -> return null
            }
        }
        return null
    }

    override fun min(): Key? {
        return headNode?.key
    }

    override fun max(): Key? {
        return tailNode?.key
    }

    override fun floor(key: Key): Key? {
        var cursor = headNode
        while (cursor != null) {
            val result = cursor.key.compareTo(key)
            when {
                result == 0 -> return cursor.key
                result < 0 -> cursor = cursor.next
                else -> return cursor.prev?.key
            }
        }
        return null
    }

    override fun ceiling(key: Key): Key? {
        var cursor = headNode
        while (cursor != null) {
            val result = cursor.key.compareTo(key)
            when {
                result == 0 -> return cursor.key
                result < 0 -> cursor = cursor.next
                else -> return cursor.key
            }
        }
        return null
    }

    override fun rank(key: Key): Int {
        var cursor = headNode
        var count = 0
        while (cursor != null) {
            val result = cursor.key.compareTo(key)
            when {
                result < 0 -> {
                    cursor = cursor.next
                    count++
                }
                else -> return count
            }
        }
        return count
    }

    override fun select(k: Int): Key? {
        if (k > size)
            return null
        var mark = k - 1
        var node = headNode
        while (mark > 0) {
            mark--
            node = node?.next
        }
        return node?.key
    }

    override fun deleteMax(): Value? {
        val max = tailNode
        tailNode?.apply { delete(key) }
        return max?.value
    }

    override fun deleteMin(): Value? {
        val min = headNode
        headNode?.apply { delete(key) }
        return min?.value
    }

    override fun size(lo: Key, hi: Key): Int {
        var cursor = headNode
        var count = 0
        while (cursor != null){
            if (cursor.key < lo){
                cursor = cursor.next
                continue
            }
            if (cursor.key <= hi){
                cursor = cursor.next
                count++
                continue
            }
            return count
        }

        return count
    }


    override fun size(): Int {
        return size
    }

    override fun keys(lo: Key, hi: Key): Iterator<Key> {
        val iterable = SSTIterable()
        var cursor = headNode
        while (cursor != null) {
            if (cursor.key < lo){
                cursor = cursor.next
                continue
            }
            if (cursor.key <= hi){
                iterable.add(cursor.key)
                cursor = cursor.next
                continue
            }
            return iterable.iterator()

        }
        return iterable.iterator()
    }

    override fun keys(): Iterator<Key> {
        val iterable = SSTIterable()
        var cursor = headNode
        while (cursor != null) {
            iterable.add(cursor.key)
            cursor = cursor.next
        }
        return iterable.iterator()
    }

    private inner class SSTIterable : Iterable<Key> {
        private val queue = Queue<Key>()
        override fun iterator(): Iterator<Key> {
            return queue.iterator()
        }

        fun add(key: Key) {
            queue.enqueue(key)
        }

    }

    private class Node<Key, Value>(val key: Key, var value: Value) {
        var next: Node<Key, Value>? = null
        var prev: Node<Key, Value>? = null

        fun insertAfter(node: Node<Key, Value>) {
            val next = node.next
            node.setNextNode(this)
            this.setNextNode(next)

        }

        fun insertBefore(node: Node<Key, Value>) {
            val prev = node.prev
            node.setPrevNode(this)
            this.setPrevNode(prev)

        }

        fun setNextNode(node: Node<Key, Value>?) {
            next = node
            node?.prev = this
        }

        fun setPrevNode(node: Node<Key, Value>?) {
            prev = node
            node?.next = this
        }

        /**
         * 如果节点在链表中，删除这个节点
         * 必须前后都进行设置，以兼容空的情况
         */
        fun delete() {
            prev?.setNextNode(next)
            next?.setPrevNode(prev)
        }
    }
}