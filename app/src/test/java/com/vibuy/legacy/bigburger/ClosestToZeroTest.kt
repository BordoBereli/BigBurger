package com.vibuy.legacy.bigburger

import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * Find the closest float array value to given a float number.
 */
class ClosestToZeroTest {
    private val arr =
        floatArrayOf(7f, -10f, 13f, 8f, 4f, -7.2f, -12f, -3.7f, 3.5f, -9.6f, 6.5f, -1.7f)

    @Test
    fun closestToZero() {
        val closest = closestSmaller(arr, 0f)
        assertEquals(-1.7f, closest)
    }

    private fun closestSmaller(arr: FloatArray, number: Float) : Float {
        // Insert all array elements into a TreeSet
        val ts = TreeSet<Float>()
        for (i in arr.indices)
            ts.add(arr[i])

        return printClosestToZero(ts, number)
    }

    private fun printClosestToZero(ts: TreeSet<Float>, number: Float) : Float {
        val smallerDist = Math.abs(number - ts.lower(number))
        val biggerDist = Math.abs(number - ts.higher(number))

        var closest = if (smallerDist < biggerDist) { ts.lower(number) }
                      else { ts.higher(number) }

        if (closest == null)
            print((-1).toString())
        else
            print(closest.toString())

        return closest

    }
}
