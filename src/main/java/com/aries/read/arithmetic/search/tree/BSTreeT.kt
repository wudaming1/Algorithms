package com.aries.read.arithmetic.search.tree

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.search.SST

/**
 * 二叉查找树.
 * 最佳情况查找时间复杂度logN，底数是2。
 * 尝试用循环实现。未完成！
 */
class BSTreeT<Key : Comparable<Key>, Value> : SST<Key, Value> {

    private var root: Node<Key, Value>? = null
    private var size = 0

    override fun min(): Key? {
        return root?.findMin()?.key
    }

    override fun max(): Key? {
        return root?.findMax()?.key
    }

    override fun floor(key: Key): Key? {
        if (root == null)
            return null
        return floor(root!!, key)?.key
    }

    private fun floor(node: Node<Key, Value>, key: Key): Node<Key, Value>? {
        if (key > node.key) {
            val right = node.right ?: return node
            return floor(right, key) ?: node
        }
        if (key < node.key) {
            val left = node.left ?: return null
            return floor(left, key)
        }
        return node
    }

    override fun ceiling(key: Key): Key? {
        if (root == null)
            return null
        return ceiling(root!!, key)?.key
    }

    private fun ceiling(node: Node<Key, Value>, key: Key): Node<Key, Value>? {
        if (key > node.key) {
            val right = node.right ?: return node
            return ceiling(right, key) ?: node
        }
        return node
    }

    override fun rank(key: Key): Int {
        val queue = Queue<Key>()
        root?.apply { keys(this, queue, this.findMin().key, key) }
        return queue.size()
    }

    override fun select(k: Int): Key? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMax(): Value? {
        val node = root?.findMax() ?: return null
        val value = node.value
        val replace = node.deleteFormTree()
        if (replace?.parent == null){
            root = replace
        }
        return value
    }

    override fun deleteMin(): Value? {
        val node = root?.findMin() ?: return null
        val value = node.value
        val replace = node.deleteFormTree()
        if (replace?.parent == null){
            root = replace
        }
        return value
    }

    override fun size(lo: Key, hi: Key): Int {
        val queue = Queue<Key>()
        root?.apply { keys(this, queue, lo, hi) }
        return queue.size()
    }

    override fun put(key: Key, value: Value) {
        if (root == null) {
            root = Node(key, value)
        } else {
            var cursor = root
            while (cursor != null) {
                when {
                    key > cursor.key -> {
                        if (cursor.right == null) {
                            val node = Node(key, value)
                            cursor.right = node
                            node.parent = cursor
                            size++
                        }
                        cursor = cursor.right


                    }
                    key < cursor.key -> {
                        if (cursor.left == null) {
                            val node = Node(key, value)
                            cursor.left = node
                            node.parent = cursor
                            size++
                        }
                        cursor = cursor.left
                    }
                    else -> {
                        cursor.value = value
                        return
                    }
                }
            }
        }
    }

    private fun put(x:Node<Key,Value>?,key: Key,value: Value):Node<Key,Value>{
        if (x == null) return Node(key,value)
        val cmp = key.compareTo(x.key)
        when{
            cmp > 0 -> x.right = put(x.right,key, value)
            cmp < 0 -> x.left = put(x.left,key, value)
            else -> x.value = value
        }
        x.size = size(x.left) + size(x.right) + 1
        return x
    }

    private fun size(node: Node<Key, Value>?):Int{
        if (node == null) return  0
        return node.size
    }

    override fun get(key: Key): Value? {
        return getNode(key)?.value
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
        val replace = node.deleteFormTree()
        if (replace?.parent == null){
            root = replace
        }
        size--
        return value
    }

    override fun size(): Int {
        return size
    }

    override fun keys(lo: Key, hi: Key): Iterator<Key> {
        val queue = Queue<Key>()
        root?.apply { keys(this, queue, lo, hi) }
        return queue.iterator()

    }

    override fun keys(): Iterator<Key> {
        val queue = Queue<Key>()
        root?.apply {
            keys(this, queue, this.findMin().key, this.findMax().key)
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


    private class Node<Key, Value>(val key: Key, var value: Value,var size:Int = 1) {
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
        var parent: Node<Key, Value>? = null

        /**
         * 删除当前节点后的替代节点
         */
        fun deleteFormTree(): Node<Key, Value>? {
            val replaceNode = findReplaceNode()
            if (replaceNode == null) {
                if (parent?.left == this)
                    parent?.left = null
                else
                    parent?.right = null
            } else {
                replaceWith(replaceNode)
            }
            return replaceNode

        }

        /**
         * @param node 替换当前节点的新节点
         */
        fun replaceWith(node: Node<Key, Value>) {

            val nodeParent = node.parent ?: return
            if (nodeParent.left == node) {
                nodeParent.left = node.right
                node.right?.parent = nodeParent
            } else {
                nodeParent.right = node.right
                node.right?.parent = nodeParent
            }


            val isLeftChild = parent?.left == this
            if (left != node)
                node.left = left
            if (right != node)
                node.right = right
            node.parent = parent
            if (isLeftChild) {
                parent?.left = node
            } else {
                parent?.right = node
            }
            parent = null
            left = null
            right = null
        }


        /**
         * 查找左子树的最大值，或者右子树的最小值
         */
        private fun findReplaceNode(): Node<Key, Value>? {
            if (right != null) {
                return right?.findMax()
            }

            if (left != null) {
                return left?.findMin()
            }
            return null
        }

        fun findMax(): Node<Key, Value> {
            var cursor = this
            while (cursor.right != null) {
                cursor = cursor.right!!
            }
            return cursor
        }

        fun findMin(): Node<Key, Value> {
            var cursor = this
            while (cursor.left != null) {
                cursor = cursor.left!!
            }
            return cursor
        }

//        fun childrenCount():Int{
//            var cursor:Node<Key,Value>? = this
//            while (cursor!= null){
//
//            }
//        }
    }

}