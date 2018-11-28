package com.aries.read.arithmetic.search.tree


/**
 * 2-3树对应的红黑树实现
 * 所以不改变树结构的操作都不需要重写
 */
class Tree23<Key : Comparable<Key>, Value> : BSTree<Key, Value>() {


    override fun put(key: Key, value: Value) {
        root = put(root, key, value)
    }

    /**
     * @return 需要向上插入的节点
     */
    private fun put(node: Node<Key, Value>?, key: Key, value: Value): Node<Key, Value> {
        if (node == null)
            return Node(key, value)

        val cmp = key.compareTo(node.key)
        when {
            cmp > 0 -> node.right = put(node.right, key, value)
            cmp < 0 -> node.left = put(node.left, key, value)
            else -> node.value = value
        }
        return balance(node)
    }

    override fun deleteMax(): Value? {
        val max = max() ?: return null
        return delete(max)
    }


    override fun deleteMin(): Value? {
        val min = min() ?: return null
        return delete(min)
    }

    /**
     * @return 伪根节点
     */
    private fun deleteMin(node: Node<Key, Value>): Node<Key, Value>? {
        val left = node.left?:return null
        if ()
        node.left = deleteMin(left)

    }

    /**
     * @param [node]一定不是2节点，即本身是红色或者有红色子节点。
     */
    private fun delete(node: Node<Key, Value>?, key: Key): Node<Key, Value>? {
        if (node == null) return null
        val cmp = key.compareTo(node.key)

        when{
            cmp > 0 -> {

            }
        }

        if (cmp == 0){
            val min = min(node.right)
        }

        if (!isRed(node.left?.left) && !isRed(node.right?.left)){

        }


    }

    override fun delete(key: Key): Value? {
        return delete(root, key)?.value
    }

    /**
     * 空节点也是黑节点
     */
    private fun isRed(node: Node<Key, Value>?): Boolean {
        return node?.isRed ?: false
    }

    /**
     * 左旋[node]的右链接
     * 前提条件（2-3树同构红黑树不允许红色右链接）
     * 1、[node]是红色
     * 2、右链接是红色的。
     */
    private fun rotateLeft(node: Node<Key, Value>): Node<Key, Value> {
        val right = node.right!!
        //变化层级结构
        node.right = right.left
        right.left = node
        //调整颜色
        right.isRed = node.isRed
        node.isRed = true
        //重新计算树大小
        right.size = node.size
        resizeNode(node)
        return right
    }

    /**
     * 右旋[node]的左链接
     * 前提条件（红色节点与红色节点相连了）
     * 1、指向[node]节点的链接是红色的
     * 2、[node]的左链接是红色的。
     */
    private fun rotateRigth(node: Node<Key, Value>): Node<Key, Value> {
        val left = node.left!!
        //变化层级结构
        node.left = left.right
        left.right = node
        //调整颜色
        left.isRed = node.isRed
        node.isRed = true
        //重新计算树大小
        left.size = node.size
        resizeNode(node)
        return left
    }

    private fun flipColor(node: Node<Key, Value>) {
        node.isRed = node != root
        node.left?.isRed = false
        node.right?.isRed = false

    }

    /**
     * 在不改变树的平衡性的前提下，修复不符合（2-3）红黑树性质的节点
     */
    private fun balance(node: Node<Key, Value>): Node<Key, Value>{
        var parent = node
        //一次对于parent是红色情况，左旋后必定紧跟右旋，右旋后必定紧跟变色，但是左旋、右旋之间会各一个方法栈，以定位合适的旋转节点。
        //这个判断及旋转包含两种情况：2节点插入右节点，3节点插入右节点。
        if (!isRed(parent.left) && isRed(parent.right)) parent = rotateLeft(parent)
        if (isRed(parent.left?.left) && isRed(parent.left)) parent = rotateRigth(parent)
        if (isRed(parent.left) && isRed(parent.right)) flipColor(parent)
        parent.size = size(parent.left) + size(parent.right) + 1
        return parent
    }
}