package com.aries.read.arithmetic.search.tree

import java.util.*

object TreeUtils {


    /**
     * 前序+中序还原二叉树
     */
    fun <Key, Value> restoreFormPre(pre: Array<BSTree.Node<Key, Value>>, middle: Array<BSTree.Node<Key, Value>>): BSTree.Node<Key, Value>? {
        if (pre.isEmpty() || pre.size != middle.size)
            return null

        val root = pre[0]
        middle.forEachIndexed { index, node ->
            if (node.key == root.key) {
                root.left = restoreFormPre(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(middle, 0, index))
                root.right = restoreFormPre(Arrays.copyOfRange(pre, index + 1, pre.size), Arrays.copyOfRange(middle, index + 1, middle.size))
            }
        }
        return root
    }

    /**
     * 后序+中序还原二叉树
     */
    fun <Key, Value> restoreFormPost(post: Array<BSTree.Node<Key, Value>>, middle: Array<BSTree.Node<Key, Value>>): BSTree.Node<Key, Value>? {
        if (post.isEmpty() || post.size != middle.size)
            return null

        val root = post.last()
        middle.forEachIndexed { index, node ->
            if (node.key == root.key) {
                root.left = restoreFormPost(Arrays.copyOfRange(post, 0, index), Arrays.copyOfRange(middle, 0, index))
                root.right = restoreFormPost(Arrays.copyOfRange(post, index, post.size - 1), Arrays.copyOfRange(middle, index + 1, middle.size))
            }
        }

        return root
    }


    /**
     * 前序遍历
     * @param root 根节点
     * @param out 遍历输出结果
     */
    fun <Key, Value> perTraverse(root: BSTree.Node<Key, Value>, out: MutableList<BSTree.Node<Key, Value>>) {
        out.add(root)
        root.left?.apply {
            perTraverse(this, out)
        }
        root.right?.apply {
            perTraverse(this, out)
        }
    }

    /**
     * 中序遍历
     * @param root 根节点
     * @param out 遍历输出结果
     */
    fun <Key, Value> inTraverse(root: BSTree.Node<Key, Value>, out: MutableList<BSTree.Node<Key, Value>>) {
        root.left?.apply {
            inTraverse(this, out)
        }
        out.add(root)
        root.right?.apply {
            inTraverse(this, out)
        }
    }


    /**
     * 后序遍历
     * @param root 根节点
     * @param out 遍历输出结果
     */
    fun <Key, Value> postTraverse(root: BSTree.Node<Key, Value>, out: MutableList<BSTree.Node<Key, Value>>) {
        root.left?.apply {
            postTraverse(this, out)
        }
        root.right?.apply {
            postTraverse(this, out)
        }
        out.add(root)
    }

}