package com.aries.read.arithmetic.search


/**
 * 有序符号表
 */
interface SST<Key:Comparable<Key>,Value>: ST<Key, Value> {

    fun min():Key?

    fun max():Key?

    /**
     * 小于等于[key]的最大键
     */
    fun floor(key: Key):Key?

    /**
     * 大于等于[key]的最小键
     */
    fun ceiling(key: Key):Key?

    /**
     * 小于[key]的键的数量
     */
    fun rank(key: Key):Int

    /**
     * 排名为[k]的键
     */
    fun select(k:Int):Key?

    fun deleteMax():Value?

    fun deleteMin():Value?

    /**
     * 在[[lo]..[hi]]之间键的数量
     */
    fun size(lo:Key,hi:Key):Int

    /**
     * 在[[lo]..[hi]]之间键集合，已排序。
     */
    fun keys(lo:Key,hi:Key):Iterator<Key>
}