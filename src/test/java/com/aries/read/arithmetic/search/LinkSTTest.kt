package com.aries.read.arithmetic.search

import com.aries.read.ResUtil
import org.junit.Test

/**
 * 符号表性能测试
 */
class LinkSTTest {

    @Test
    fun testST() {
        val minLength = 1
        val st = LinkST<String, Int>()
        val scanner = ResUtil.getScanner(this, "search/tinyTale.txt")
        while (scanner.hasNext()) {
            val s = scanner.next().trimEnd(',')
            if (s.length > minLength) {
                st.put(s, (st.get(s)?:0)+1)
            }
        }
        var max = "max"
        st.put(max,0)
        for (word in st.keys()){
            if (st.get(max)!! < st.get(word)!!){
                max = word
            }
        }
        println("$max:${st.get(max)}")
    }

    /**
     * 2s
     */
    @Test
    fun testTaleST() {
        val minLength = 8
        val st = LinkST<String, Int>()
        val scanner = ResUtil.getScanner(this, "search/tale.txt")
        while (scanner.hasNext()) {
            val s = scanner.next().trimEnd(',')
            if (s.length > minLength) {
                st.put(s, (st.get(s)?:0)+1)
            }
        }
        var max = "max"
        st.put(max,0)
        for (word in st.keys()){
            if (st.get(max)!! < st.get(word)!!){
                max = word
            }
        }
        println("$max:${st.get(max)}")
    }

    @Test
    fun testLeipzig1mST() {
        val minLength = 10
        val st = LinkST<String, Int>()
        val scanner = ResUtil.getScanner(this, "search/leipzig1m.txt")
        while (scanner.hasNext()) {
            val s = scanner.next().trimEnd(',')
            if (s.length > minLength) {
                st.put(s, (st.get(s)?:0)+1)
            }
        }
        var max = "max"
        st.put(max,0)
        for (word in st.keys()){
            if (st.get(max)!! < st.get(word)!!){
                max = word
            }
        }
        println("$max:${st.get(max)}")
    }

}