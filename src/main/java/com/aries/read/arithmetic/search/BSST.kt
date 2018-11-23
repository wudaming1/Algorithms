package com.aries.read.arithmetic.search

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.common.Utils
import com.aries.read.arithmetic.common.copyTo

/**
 * 基于数组的有序符号表，支持二分查找。
 * 向一个空表插入N个不同键需要比较次数~log(N)
 */
class BSST : SST<String, Int> {
    @Suppress("unchecked_cast")
    private var keys: Array<String> = Array(10, {" "}) 
    @Suppress("unchecked_cast")
    private var values: Array<Int> = Array<Int>(10, {0}) as Array<Int>
    private var size = 0


    override fun min(): String? {
        if (size == 0) {
            return null
        }
        return keys[0]
    }

    override fun max(): String? {
        if (size == 0) {
            return null
        }
        return keys[size - 1]
    }

    override fun floor(key: String): String? {
        val rank = rank(key)
        return if (rank == 0) null else keys[rank]
    }

    override fun ceiling(key: String): String? {
        val rank = rank(key)
        if (rank == size)
            return null
        if (key == keys[rank - 1])
            return key

        return keys[rank]
    }

    override fun rank(key: String): Int {
        if (size == 0)
            return 0
        return Utils.binaryRank(keys, key, size - 1)
    }

    override fun select(k: Int): String? {
        if (k < size)
            return keys[k]
        return null
    }

    override fun deleteMax(): Int? {
        if (size > 0) {
            size--
        }
        return values[size]
    }

    override fun deleteMin(): Int? {
        if (size == 0)
            return null
        val min = values[0]
        for (index in 0 until size - 1) {
            keys[index] = keys[index + 1]
            values[index] = values[index + 1]
        }
        size--
        return min
    }

    override fun size(lo: String, hi: String): Int {
        return rank(hi) - rank(lo)
    }

    override fun put(key: String, value: Int) {
        val index = Utils.binarySearch(keys, key, size - 1)
        if (index != -1) {
            keys[index] = key
            values[index] = value
        } else {
            if (keys.size <= size) {
                resize()
            }
            val rank = Utils.binaryRank(keys, key, size - 1)
            if (rank >= size) {
                keys[rank] = key
                values[rank] = value
            } else {
                moveArrayForward(keys, rank , size - 1)
                moveArrayForward(values, rank, size - 1)
                keys[rank] = key
                values[rank] = value
            }
            size++
        }
    }

    override fun get(key: String): Int? {
        val index = Utils.binarySearch(keys, key, size - 1)
        return if (index == -1) null else values[index]
    }

    override fun delete(key: String): Int? {
        val index = Utils.binarySearch(keys, key)
        return if (index == -1) {
            null
        } else {
            val value = values[index]
            moveArrayBackward(keys, index+1, size - 1)
            moveArrayBackward(values, index+1, size - 1)
            size--
            value
        }
    }

    override fun size(): Int {
        return size
    }

    @Suppress("unchecked_cast")
    private fun resize() {
        println("resize")
        val newKeys = Array<String>(size * 2, {" "}) 
        val newValues = Array(size * 2, {0}) 
        keys.copyTo(newKeys)
        values.copyTo(newValues)
        keys = newKeys
        values = newValues
    }

    /**
     * 闭区间
     * @param step 移动步数
     * @param lo 指数组下标
     *
     */
    private fun <T> moveArrayForward(data: Array<T>, lo: Int, hi: Int, step: Int = 1) {
        for (index in hi downTo lo) {
            data[index + step] = data[index]
        }

    }

    /**
     * 闭区间
     * @param step 移动步数
     * @param lo 指数组下标
     *
     */
    private fun <T> moveArrayBackward(data: Array<T>, lo: Int, hi: Int, step: Int = 1) {
        for (index in lo..hi) {
            data[index - step] = data[index]
        }

    }

    override fun keys(lo: String, hi: String): Iterator<String> {
        var loIndex = rank(lo)
        if (keys[loIndex] == lo) {
            loIndex--
        }
        var hiIndex = rank(hi)
        if (keys[hiIndex] == hi)
            hiIndex++
        val iterable = BSSTIterable()
        for (index in loIndex until hiIndex) {
            iterable.add(keys[index])
        }
        return iterable.iterator()
    }

    override fun keys(): Iterator<String> {
        val iterable = BSSTIterable()
        for (index in 0 until size) {
            iterable.add(keys[index])
        }
        return iterable.iterator()
    }

    private inner class BSSTIterable : Iterable<String> {
        private val queue = Queue<String>()
        override fun iterator(): Iterator<String> {
            return queue.iterator()
        }

        fun add(key: String) {
            queue.enqueue(key)
        }

    }

    override fun toString(): String {
        
        val sb = StringBuilder()
        for (i in 0 until size){
            sb.append(keys[i])
            sb.append("--")
            sb.append(values[i])
            sb.append("\n")
        }
        return sb.toString()
    }
}