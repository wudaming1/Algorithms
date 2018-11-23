package com.aries.read.arithmetic.search

/**
 * 符号表
 * 规则：
 * 1.不允许有重复的key。
 * 2.键不能为空
 * 3.值不能为空
 */
interface ST<Key,Value> {

    /**
     * 添加数据
     */
    fun put(key: Key,value: Value)

    /**
     * 查询[key]对应的value
     */
    fun get(key: Key):Value?

    /**
     * 删除[key]对应的数据
     */
    fun delete(key: Key):Value?

    fun contains(key: Key) = get(key) == null

    fun isEmpty():Boolean = size() == 0

    fun size():Int

    /**
     * 返回所有key
     */
    fun keys():Iterator<Key>
}