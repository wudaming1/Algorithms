package com.aries.read.arithmetic.common


class Bag<Item> : Iterable<Item> {

    //表头
    private var first: Node<Item>? = null
    private var count = 0

    fun add(item: Item) {
        val node = Node(item)
        node.next = first
        first = node
        count++
    }

    fun isEmpty(): Boolean = count == 0

    fun size(): Int = count

    override fun iterator(): Iterator<Item> {
        return BagIterator()
    }


    private class Node<Item>(val value: Item) {
        var next: Node<Item>? = null
    }

    private inner class BagIterator : Iterator<Item> {

        private var cursor: Node<Item>? = null

        override fun hasNext(): Boolean {
            return if (cursor == null) {
                first != null
            } else {
                cursor?.next != null
            }
        }

        override fun next(): Item {
            cursor = if (cursor == null) {
                first
            } else {
                cursor?.next
            }
            return cursor!!.value
        }

    }
}