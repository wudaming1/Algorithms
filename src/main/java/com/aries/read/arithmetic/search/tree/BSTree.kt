package com.aries.read.arithmetic.search.tree

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.search.SST

/**
 * 二叉查找树.
 * 最佳情况查找时间复杂度logN，底数是2。
 * 基于迭代查找方式。
 */
open class BSTreeR<Key : Comparable<Key>, Value> : SST<Key, Value> {

    protected var root: Node<Key, Value>? = null

    override fun min(): Key? {
        val node = root ?: return null
        return min(node).key
    }

    private fun min(node: Node<Key, Value>): Node<Key, Value> {
        val left = node.left ?: return node
        return min(left)
    }

    override fun max(): Key? {
        val node = root ?: return null
        return max(node).key
    }

    private fun max(node: Node<Key, Value>): Node<Key, Value> {
        val right = node.right ?: return node
        return max(right)
    }

    override fun floor(key: Key): Key? {
        return floor(root, key)?.key
    }

    private fun floor(node: Node<Key, Value>?, key: Key): Node<Key, Value>? {
        if (node == null) return null
        val cmp = key.compareTo(node.key)
        if (cmp > 0) {
            //在右子树找比key小的最大值,没有的话就是自己
            return floor(node.right, key) ?: node
        } else if (cmp < 0) {
            return floor(node.left, key)
        }
        return node
    }

    override fun ceiling(key: Key): Key? {
        return ceiling(root, key)?.key
    }

    private fun ceiling(node: Node<Key, Value>?, key: Key): Node<Key, Value>? {
        if (node == null) return null
        val cmp = key.compareTo(node.key)
        if (cmp > 0) {
            return ceiling(node.right, key)
        } else if (cmp < 0) {
            val t = ceiling(node.left, key)
            return t ?: node
        }
        return node
    }

    override fun select(k: Int): Key? {
        return select(root, k)?.key
    }

    private fun select(node: Node<Key, Value>?, k: Int): Node<Key, Value>? {
        if (node == null) return null
        val rank = size(node.left) + 1
        if (rank < k) {
            return select(node.right, k)
        } else if (rank > k) {
            return select(node.left, k)
        }
        return node
    }

    override fun rank(key: Key): Int {
        val node = floor(root, key)
        if (node != null && node.key == key) {
            return (node.left?.size ?: 0) + 1
        }
        return node?.size ?: 0

    }

    override fun put(key: Key, value: Value) {
        root = put(root, key, value)
    }

    private fun put(x: Node<Key, Value>?, key: Key, value: Value): Node<Key, Value> {
        if (x == null) return Node(key, value)
        val cmp = key.compareTo(x.key)
        when {
            cmp > 0 -> x.right = put(x.right, key, value)
            cmp < 0 -> x.left = put(x.left, key, value)
            else -> x.value = value
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }


    override fun get(key: Key): Value? {
        return get(root, key)
    }

    private fun get(x: Node<Key, Value>?, key: Key): Value? {
        if (x == null) return null
        val cmp = key.compareTo(x.key)
        return when {
            cmp > 0 -> get(x.right, key)
            cmp < 0 -> get(x.left, key)
            else -> x.value
        }
    }

    private fun getNode(key: Key): Node<Key, Value>? {
        var cursor = root
        while (cursor != null) {
            cursor = when {
                key > cursor.key -> {
                    cursor.right
                }
                key < cursor.key -> {
                    cursor.left
                }
                else -> {
                    return cursor
                }
            }
        }
        return null
    }

    override fun delete(key: Key): Value? {
        val node = getNode(key) ?: return null
        val value = node.value
        root = delete(root, key)
        return value
    }

    /**
     * @return 相对根节点
     * 在[node]为根节点的子树中删除[key]节点
     */
    private fun delete(node: Node<Key, Value>?, key: Key): Node<Key, Value>? {
        var head: Node<Key, Value> = node ?: return null
        val cmp = key.compareTo(head.key)
        when {
            cmp > 0 -> head.right = delete(head.right, key)
            cmp < 0 -> head.left = delete(head.left, key)
            else -> {
                val right = head.right
                if (head.left == null) return head.right
                if (right == null) return head.left
                val t = head
                head = min(right)
                t.right = deleteMin(right)
                head.left = t.left
                head.right = t.right
            }
        }
        resizeNode(head)
        return head
    }

    override fun deleteMax(): Value? {
        val node = root ?: return null
        val max = max(node)
        root = deleteMax(root)
        return max.value
    }

    /**
     * @return 相对根节点
     * 删除以[node]为根节点的子树的最大节点
     */
    private fun deleteMax(node: Node<Key, Value>?): Node<Key, Value>? {
        if (node == null) return null
        if (node.right == null) return node.left
        node.right = deleteMax(node.right)
        resizeNode(node)
        return node
    }

    override fun deleteMin(): Value? {
        val node = root ?: return null
        val min = min(node)
        root = deleteMin(root)
        return min.value

    }

    /**
     * @return 相对根节点
     * 删除以[node]为根节点的子树的最小节点
     */
    private fun deleteMin(node: Node<Key, Value>?): Node<Key, Value>? {
        if (node == null) return null
        if (node.left == null) return node.right
        node.left = deleteMin(node.left)
        resizeNode(node)
        return node
    }

    override fun size(): Int {
        return size(root)
    }

    override fun size(lo: Key, hi: Key): Int {
        return rank(hi) - rank(lo)
    }

    private fun size(node: Node<Key, Value>?): Int {
        if (node == null) return 0
        return node.size
    }

    override fun keys(lo: Key, hi: Key): Iterator<Key> {
        val queue = Queue<Key>()
        root?.apply { keys(this, queue, lo, hi) }
        return queue.iterator()

    }

    override fun keys(): Iterator<Key> {
        val queue = Queue<Key>()
        root?.apply {
            //root不空的情况下。max和min方法一定不空
            keys(this, queue, min()!!, max()!!)
        }
        return queue.iterator()
    }

    private fun keys(x: Node<Key, Value>, queue: Queue<Key>, lo: Key, hi: Key) {
        val left = x.left
        if (left != null && left.key >= lo) {
            keys(left, queue, lo, hi)
        }
        if (x.key in lo..hi) {
            queue.enqueue(x.key)
        }
        val right = x.right
        if (right != null && right.key <= hi) {
            keys(right, queue, lo, hi)

        }
    }

    private fun resizeNode(node: Node<Key, Value>) {
        node.size = size(node.left) + size(node.right) + 1
    }

    open class Node<Key, Value>(val key: Key, var value: Value, var size: Int = 1) {
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
    }

}