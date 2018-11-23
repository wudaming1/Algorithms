package com.aries.read.arithmetic.common

import java.lang.StringBuilder
import java.util.*

/**
 * 基于后进先出策略的集合类型
 */
class Stack<Item>:Iterable<Item> {

    //栈顶
    private var first: Node<Item>? = null
    private var count = 0

    fun push(item: Item) {
        val node = Node(item)
        node.next = first
        first = node
        count++
    }

    fun pop(): Item? {
        val item = first?.value
        first = first?.next
        count--
        return item
    }

    fun isEmpty() = count == 0

    fun size() = count

    override fun iterator(): Iterator<Item> {
        return StackIterator()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("{ ")
        forEach{
            sb.append(it.toString()).append(" ,")
        }
        sb.deleteCharAt(sb.lastIndex)
        sb.append("}")
        return sb.toString()
    }

    private inner class StackIterator : Iterator<Item> {
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

    private class Node<Item>(val value: Item) {
        var next: Node<Item>? = null
    }

}