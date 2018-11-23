package com.aries.read.arithmetic.graph.arith

import java.io.File
import java.util.*

/**
 * 连通性算法。算法本身带有数据结构，以数组的位置和值的对应关系表示链接关系。只能处理连通性问题。
 */
interface UnionFind {

    fun count():Int

    fun union(p:Int,q:Int)

    fun find(p:Int):Int

    fun connected(p:Int,q:Int):Boolean

    companion object {
        fun fromFile(file: File):UnionFind{
            val scanner = Scanner(file)
            val count  = scanner.nextInt()
            val uf = QuickFind(count)
            while (scanner.hasNextInt()){
                val p = scanner.nextInt()
                val q = scanner.nextInt()
                if (uf.connected(p,q))
                    continue
                uf.union(p,q)
            }
            return uf
        }


        fun QUfromFile(file: File):UnionFind{
            val scanner = Scanner(file)
            val count  = scanner.nextInt()
            val uf = QuickUnion(count)
            while (scanner.hasNextInt()){
                val p = scanner.nextInt()
                val q = scanner.nextInt()
                if (uf.connected(p,q))
                    continue
                uf.union(p,q)
            }
            return uf
        }

        fun WQUfromFile(file: File):UnionFind{
            val scanner = Scanner(file)
            val count  = scanner.nextInt()
            val uf = WeightedQuickUnion(count)
            while (scanner.hasNextInt()){
                val p = scanner.nextInt()
                val q = scanner.nextInt()
                if (uf.connected(p,q))
                    continue
                uf.union(p,q)
            }
            return uf
        }

        fun CWQUfromFile(file: File):UnionFind{
            val scanner = Scanner(file)
            val count  = scanner.nextInt()
            val uf = ComWeightedQuickUnion(count)
            while (scanner.hasNextInt()){
                val p = scanner.nextInt()
                val q = scanner.nextInt()
                if (uf.connected(p,q))
                    continue
                uf.union(p,q)
            }
            return uf
        }


    }

}