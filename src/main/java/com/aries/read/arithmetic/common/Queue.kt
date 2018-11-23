package com.aries.read.arithmetic.common

/**
 * 基于先进先出策略的集合类型
 */
class Queue<Item> : Iterable<Item> {

    private var last: Node<Item>? = null
    private var first: Node<Item>? = null
    private var count = 0

    fun enqueue(item: Item) {
        val node = Node(item)
        last?.next = node
        last = node
        if (first == null)
            first = node

        count++

    }

    /**
     * 删除队列头部的元素。
     * @return 返回被删除的元素，队列为空时返回空
     */
    fun dequeue(): Item {
        val target = first
        first = first?.next
        if (target != null)
            count--
        return target!!.value
    }

    override fun iterator(): Iterator<Item> {
        return QueueIterator()
    }

    fun isEmpty() = count == 0

    fun size() = count


    private inner class QueueIterator : Iterator<Item> {
        private var cursor :Node<Item>? = null

        override fun hasNext(): Boolean {
            return if (cursor==null){
                first != null
            }else{
                cursor?.next != null
            }
        }

        override fun next(): Item {
            cursor = if (cursor == null){
                first
            }else{
                cursor?.next
            }
            return cursor!!.value
        }

    }

    private class Node<Item>(val value: Item) {
        var next: Node<Item>? = null
    }

}