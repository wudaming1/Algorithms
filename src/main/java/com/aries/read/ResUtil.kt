package com.aries.read

import com.aries.read.arithmetic.common.Queue
import com.aries.read.arithmetic.graph.Graph
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.net.URL
import java.util.*

fun println(s:String){
    System.out.println(s)
}

object ResUtil {

    private fun getResFile(any: Any, path:String):URL{
        return any.javaClass.classLoader.getResource(path)
    }

    fun getFile(any: Any,path:String):File{
        return File(ResUtil.getResFile(any,path).path)
    }

    fun getInStream(any: Any, path:String):InputStream{
        return getResFile(any, path).openStream()
    }

    fun getResFileAsReader(any: Any,path:String): FileReader {
        val url = getResFile(any,path)
        val file = url.file
        return FileReader(file)
    }

    fun getScanner(any: Any,path: String): Scanner{
        val stream = getInStream(any, path)
        return Scanner(stream)
    }

    fun readStringArrayFile(any: Any,path:String):List<String>{
        val reader = getResFileAsReader(any,path)
        return reader.readLines()

    }

    fun readIntArrayFile(any: Any,path:String):IntArray{
        val list = readStringArrayFile(any, path)
        return list.map { it.toInt() }.toIntArray()

    }

    fun readStringQueueFromFile(any: Any,path:String):Queue<String>{
        val stringArray = readStringArrayFile(any,path)
        val queue = Queue<String>()
        stringArray.forEach {
            queue.enqueue(it)
        }
        return queue
    }



}