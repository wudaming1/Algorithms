package com.aries.read.arithmetic.search.tree

import org.junit.Before
import org.junit.Test


/**
 *  前：{4,4}, {2,2}, {1,1}, {3,3}, {8,8}, {6,6}, {5,5}, {7,7}, {10,10}, {9,9}
 *  中：{1,1}, {2,2}, {3,3}, {4,4}, {5,5}, {6,6}, {7,7}, {8,8}, {9,9}, {10,10}
 *  后：{1,1}, {3,3}, {2,2}, {5,5}, {7,7}, {6,6}, {9,9}, {10,10}, {8,8}, {4,4}
 *
 */
class TreeUtilsTest {


    private val tree = Tree23<Int, Int>()


    @Test
    fun testRestoreByPre() {
        val pre = Array<BSTree.Node<Int, Int>>(10, {
            BSTree.Node(0, 0)
        })

        pre[0] = newNode(4)
        pre[1] = newNode(2)
        pre[2] = newNode(1)
        pre[3] = newNode(3)
        pre[4] = newNode(8)
        pre[5] = newNode(6)
        pre[6] = newNode(5)
        pre[7] = newNode(7)
        pre[8] = newNode(10)
        pre[9] = newNode(9)

        val middle = Array(10, {
            newNode(it+1)
        })

        val list = TreeUtils.restoreFormPre(pre,middle)

        print(list)
    }

    @Before
    fun init() {
        (1..10).forEach {
            tree.put(it, it)
        }

    }

    @Test
    fun testPreTraverse() {
        val out = mutableListOf<BSTree.Node<Int, Int>>()
        TreeUtils.perTraverse(tree.root!!, out)
        print(out)
    }

    @Test
    fun testMiddleTraverse() {
        val out = mutableListOf<BSTree.Node<Int, Int>>()
        TreeUtils.inTraverse(tree.root!!, out)
        print(out)
    }

    @Test
    fun testPostTraverse() {
        val out = mutableListOf<BSTree.Node<Int, Int>>()
        TreeUtils.postTraverse(tree.root!!, out)
        print(out)
    }


    private fun newNode(value: Int): BSTree.Node<Int, Int> {
        return BSTree.Node(value, value)
    }

}