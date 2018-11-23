package com.aries.read.arithmetic.search

import com.aries.read.arithmetic.search.tree.BSTree
import com.aries.read.arithmetic.search.tree.BSTreeR
import org.junit.Before
import org.junit.Test


/**
 * 符号表轨迹测试
 */
class BehaviorTest {
    private val input = arrayOf('S','E','A','R','C','H','E','X','A','M','P','L','E')

    @Before
    fun before(){
        println("数据大小：${input.size}\n")
    }

    @Test
    fun testST(){
        val st = LinkST<Char,Int>()
        input.forEachIndexed { index, c ->
            st.put(c,index)
        }
        for (key in st.keys()){
            println("$key--${st.get(key)}")
        }

    }

    @Test
    fun testSST(){
        val st = LinkedSST<Char,Int>()
        input.forEachIndexed { index, c ->
            st.put(c,index)
        }
        printST(st)
    }

    @Test
    fun testBSTree(){
        val st = BSTree<Char,Int>()

        input.forEachIndexed { index, c ->
            st.put(c,index)
        }
        printST(st)
    }

    @Test
    fun testBSTreeR(){
        val st = BSTreeR<Char,Int>()

        input.forEachIndexed { index, c ->
            st.put(c,index)
        }
        printST(st)
    }


    @Test
    fun testBSST(){
        val st = BSST()
        val input = arrayOf("S","E","A","R","C","H","E","X","A","M","P","L","E")
        input.forEachIndexed { index, c ->
            st.put(c,index)
        }
        for (key in st.keys("A","R")){
            println("$key--${st.get(key)}")
        }

        println("----")
        print(st.toString())
        println("----")
        println(st.delete("A"))
        println("----")
        println(st.delete("R"))
        println("----")
        println(st.delete("X"))


        print(st.toString())
    }


    private fun printST(st:SST<Char,Int>){
        for (key in st.keys('A','R')){
            println("$key--${st.get(key)}")
        }

        println("----")
        for (key in st.keys()){
            println("$key--${st.get(key)}")
        }


        println("----")
        for (key in st.keys()){
            print("$key--")
        }

        println("\n----")
        println("删除S:${st.delete('S')},剩余大小：${st.size()}")
        for (key in st.keys()){
            print("$key--")
        }
        println("\n----")
        println("删除A:${st.delete('A')},剩余大小：${st.size()}")
        for (key in st.keys()){
            print("$key--")
        }
        println("\n----")
        println("删除M:${st.delete('M')},剩余大小：${st.size()}")
        for (key in st.keys()){
            print("$key--")
        }
        println("\n----")
        println("删除R:${st.delete('R')},剩余大小：${st.size()}")
        for (key in st.keys()){
            print("$key--")
        }

        println("\n----")
        println("删除X:${st.delete('X')},剩余大小：${st.size()}")
        for (key in st.keys()){
            print("$key--")
        }



        println("\n----")

        for (key in st.keys()){
            println("$key--${st.get(key)}")
        }
    }
}