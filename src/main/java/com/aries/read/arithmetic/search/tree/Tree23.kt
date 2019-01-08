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
     * 在以[node]为根节点的子树中删除最小键
     * @return 删除后的新子树根节点
     */
    private fun deleteMin(node: Node<Key, Value>): Node<Key, Value>? {
        val left = node.left ?: return null
        //保证没有两条连续的黑色左链接
        if (!isRed(left) && !isRed(left.left)) {
            moveRedLeft(node)
        }
        node.left = deleteMin(left)
        return balance(node)
    }

    /**
     * 在以[node]为根节点的树中删除[key]
     * @param [node]不属于2节点，[node]：红
     * @return 放入查找路径的节点
     */
    private fun delete(node: Node<Key, Value>, key: Key): Node<Key, Value>? {
        var h = node
        val cmp = key.compareTo(node.key)
        if (cmp < 0) {
            if (!isRed(node.left) && !isRed(node.left?.left)) {
                moveRedLeft(node)
            }
            node.left = delete(node.left!!, key)
        } else {
            if (isRed(node.left)) {
                //node是一个4节点时，可能出现这种情况！
                h = rotateRight(node)
            }
            //叶子节点，且红
            if (key.compareTo(h.key) == 0 && h.right == null)
                return null

            if (!isRed(h.right) && !isRed(h.right?.left))
                h = moveRedRight(h)

            if (key.compareTo(h.key) == 0) {
                val right = node.right ?: return null
                val min = min(right)
                node.key = min.key
                node.value = min.value
                node.right = deleteMin(right)
            } else {
                h.right = delete(h.right!!, key)
            }
        }
        return balance(h)
    }

    override fun delete(key: Key): Value? {
        val node = root ?: return null
        if (!isRed(node.left) && !isRed(node.right)) {
            node.isRed = true
        }

        val result = delete(node, key)
        root?.isRed = false
        return result?.value
    }

    //红黑树辅助方法
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
    private fun rotateRight(node: Node<Key, Value>): Node<Key, Value> {
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

    /**
     * 分裂或者合并4节点
     */
    private fun flipColor(node: Node<Key, Value>) {
        node.isRed = !node.isRed
        node.left?.isRed = !isRed(node.left)
        node.right?.isRed = !isRed(node.right)

    }

    /**
     * 在不改变树的平衡性的前提下，修复不符合（2-3）红黑树性质的节点
     */
    private fun balance(node: Node<Key, Value>): Node<Key, Value> {
        var parent = node
        //一次对于parent是红色情况，左旋后必定紧跟右旋，右旋后必定紧跟变色，但是左旋、右旋之间会各一个方法栈，以定位合适的旋转节点。
        //这个判断及旋转包含两种情况：2节点插入右节点，3节点插入右节点。
        if (!isRed(parent.left) && isRed(parent.right)) parent = rotateLeft(parent)
        if (isRed(parent.left?.left) && isRed(parent.left)) parent = rotateRight(parent)
        if (isRed(parent.left) && isRed(parent.right)) flipColor(parent)
        parent.size = size(parent.left) + size(parent.right) + 1
        return parent
    }

    /**
     * 调用这个方法的前提是
     * node：红
     * node.left：黑
     * node.left.left：黑
     * node.left节点是2节点的判断。
     */
    private fun moveRedLeft(node: Node<Key, Value>): Node<Key, Value> {
        var p = node
        flipColor(p)

        if (isRed(p.right?.left)) {
            //修复右子树
            //isRed(node.right?.left)保证node.right不空
            p.right = rotateRight(p.right!!)
            p = rotateLeft(p)
            flipColor(p)
        }
        return p
    }

    private fun moveRedRight(node: Node<Key, Value>): Node<Key, Value> {
        var p = node
        flipColor(p)
        if (isRed(p.left?.left)) {
            p = rotateRight(p)
            flipColor(p)
        }
        return p
    }
}