package com.aries.read.arithmetic.sort

import java.util.*


class HeapSort<T : Comparable<T>> : AbsSort<T>() {

    private var mSize = 0
    private var mInternalArray: Array<Any?>? = null

    override fun sort(a: Array<T>) {
        mSize = a.size + 1
        mInternalArray = Array(mSize) { index ->
            if (index == 0) null else a[index - 1]
        }

        heapify()

        outputTo(a)
    }

    /**
     * 堆化，生成大顶堆
     */
    private fun heapify() {
        for (i in (mSize - 1) / 2 downTo 1) {
            sink(i)
        }

    }

    /**
     * 下沉
     */
    private fun sink(index: Int) {
        val maxChildIndex = maxChildIndex(index)
        if (maxChildIndex != -1) {
            val parent = mInternalArray!![index] as T
            val child = mInternalArray!![maxChildIndex] as T
            if (child < parent) {
                swap(mInternalArray as Array<T>, index, maxChildIndex)
                sink(maxChildIndex)
            }
        }
    }

    /**
     * 上浮
     */
    private fun swin(index: Int) {
        if (index == 1)
            return
        val parentIndex = index / 2
        val child = mInternalArray!![index] as T
        val parent = mInternalArray!![parentIndex] as T
        if (child < parent) {
            swap(mInternalArray as Array<T>, index, parentIndex)
            swin(parentIndex)
        }
    }

    /**
     * @return -1表示没有孩子。
     */
    private fun maxChildIndex(index: Int): Int {
        val leftChildIndex = 2 * index
        val rightChildIndex = 2 * index + 1
        if (leftChildIndex > mSize - 1)
            return -1
        else if (rightChildIndex > mSize - 1)
            return leftChildIndex
        else {
            val leftChild = mInternalArray!![leftChildIndex] as T
            val rightChild = mInternalArray!![rightChildIndex] as T
            if (leftChild > rightChild)
                return leftChildIndex
            else
                return rightChildIndex
        }

    }

    private fun delete(): T? {
        var max: T? = null
        if (mSize > 1) {
            max = mInternalArray!![1] as T
            mInternalArray!![1] = null
            swap(mInternalArray as Array<T>, 1, mSize - 1)
            mSize--
            sink(1)

        }
        return max
    }

    private fun outputTo(a: Array<T>) {
        var i = 0
        while (mSize > 1) {
            val element = delete()
            if (element != null)
                a[i++] = element
        }
    }
}