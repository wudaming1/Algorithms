package com.aries.read.arithmetic.graph.arith

import com.aries.read.ResUtil
import org.junit.Test

class UnionFindTest {

    @Test
    fun tinyTest(){
        val uf = UnionFind.fromFile(ResUtil.getFile(this,"tinyUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun mediumTest(){
        val uf = UnionFind.fromFile(ResUtil.getFile(this,"mediumUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun largeTest(){
        val uf = UnionFind.fromFile(ResUtil.getFile(this,"largeUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun QUtinyTest(){
        val uf = UnionFind.QUfromFile(ResUtil.getFile(this,"tinyUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun QUmediumTest(){
        val uf = UnionFind.QUfromFile(ResUtil.getFile(this,"mediumUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun QUlargeTest(){
        val uf = UnionFind.QUfromFile(ResUtil.getFile(this,"largeUF.txt"))
        System.out.println(uf.count())
    }


    @Test
    fun WQUtinyTest(){
        val uf = UnionFind.WQUfromFile(ResUtil.getFile(this,"tinyUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun WQUmediumTest(){
        val uf = UnionFind.WQUfromFile(ResUtil.getFile(this,"mediumUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun WQUlargeTest(){
        val uf = UnionFind.WQUfromFile(ResUtil.getFile(this,"largeUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun CWQUmediumTest(){
        val uf = UnionFind.CWQUfromFile(ResUtil.getFile(this,"mediumUF.txt"))
        System.out.println(uf.count())
    }

    @Test
    fun CWQUlargeTest(){
        val uf = UnionFind.CWQUfromFile(ResUtil.getFile(this,"largeUF.txt"))
        System.out.println(uf.count())
    }
}