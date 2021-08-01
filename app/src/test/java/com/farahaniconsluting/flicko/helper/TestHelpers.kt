package com.farahaniconsluting.flicko.helper

import com.google.gson.Gson
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

object TestHelpers {
    fun readTestResourceFile(fileName: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }

    fun <T> loadData(fileName: String, kClass: Class<T>): T? {
        return try {
            val stringFormattedData = readTestResourceFile(fileName)
            val gson = Gson()
            gson.fromJson(stringFormattedData, kClass)
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * Returns Mockito.eq() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 *
 * Generic T is nullable because implicitly bounded by Any?.
 */
fun <T> eq(obj: T): T = Mockito.eq(obj)

/**
 * Returns Mockito.any() as nullable type to avoid java.lang.IllegalStateException when
 * null is returned.
 */
fun <T> any(): T = Mockito.any()

/**
 * Returns ArgumentCaptor.capture() as nullable type to avoid java.lang.IllegalStateException
 * when null is returned.
 */
fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

/**
 * Helper function for creating an argumentCaptor in kotlin.
 */
inline fun <reified T : Any> argumentCaptor(): ArgumentCaptor<T> =
    ArgumentCaptor.forClass(T::class.java)

inline fun <reified T> lambdaMock(): T = Mockito.mock(T::class.java)
