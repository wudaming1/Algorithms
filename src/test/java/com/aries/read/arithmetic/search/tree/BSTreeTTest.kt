package com.aries.read.arithmetic.search.tree

import com.aries.read.ResUtil
import org.junit.Test
import java.util.regex.Pattern

class BSTreeTTest {
    @Test
    fun testTinyCounter(){
        getMaxCount("search/tinyTale.txt",1)
    }

    @Test
    fun testMediumCounter(){
        //算法 235页，给出的答案与分隔符有关，没找到合适的匹配模式。
        getMaxCount("search/tale.txt",8)
    }

    @Test
    fun testLargeCounter(){
        //算法 235页，给出的答案与分隔符有关，没找到合适的匹配模式。52分20秒
        getMaxCount("search/leipzig1m.txt",10)
    }


    /**
     * @param path 文件路径
     * @param length 长度小于等于这个值的单词都会被忽略
     */
    private fun getMaxCount(path:String,length: Int){
        val st = BSTreeT<String, Int>()
        val scanner = ResUtil.getScanner(this, path)
        val delimit = Pattern.compile(",|\\.| |\\r|\\n|\\s|--|-|!|\\?")
        scanner.useDelimiter(delimit)
        while (scanner.hasNext()) {
            val s = scanner.next()
            if (s.length >= length) {
                st.put(s, (st.get(s)?:0)+1)
            }
        }
        var max = "max"
        st.put(max,0)
        val time = System.currentTimeMillis()
        for (word in st.keys()){
            if (st.get(max)!! < st.get(word)!!){
                max = word
            }
        }
        println("$max:${st.get(max)}")
        println("business:${st.get("business")}")
        println("查询耗时：${System.currentTimeMillis() -time}")
    }
}