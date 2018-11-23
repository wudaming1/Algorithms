package com.aries.read.arithmetic.search.tree

class Tree234 {

    private var root: Node? = null


    fun insert(value: Int) {
        if (root == null) {
            root = Node(value)
        } else {
            val node = Node(value)
            insertRightPlace(node, root!!)
        }
    }

    private fun insertRightPlace(value: Node, n: Node) {
        when(value.compareTo(n)){
            1 -> {
                if (n.right == null){
                    n.right = value
                    value.parent = n
                }else{
                    insertRightPlace(value,n.right!!)
                }
            }
            0 ->{}
            -1 -> {
                if (n.left == null){
                    n.left = value
                    value.parent = n
                }else{
                    insertRightPlace(value,n.left!!)
                }
            }
        }
    }


    class Node(val value: Int) : Comparable<Node> {
        override fun compareTo(other: Node): Int {
            return when {
                value > other.value -> 1
                value == other.value -> 0
                else -> -1
            }
        }

        var isRead = true
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = null
    }
}