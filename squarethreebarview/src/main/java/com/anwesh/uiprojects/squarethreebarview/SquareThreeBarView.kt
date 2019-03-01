package com.anwesh.uiprojects.squarethreebarview

/**
 * Created by anweshmishra on 02/03/19.
 */

import android.view.View
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.view.MotionEvent
import android.app.Activity
import android.graphics.Color

val nodes : Int = 5
val squares : Int = 3
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.8f
val foreColor : Int = Color.parseColor("#4CAF50")
val fillColor : Int = Color.parseColor("#212121")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawSTBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    val yGap : Float = (2 * size) / squares
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    save()
    translate(w / 2, gap * (i + 1))
    rotate(90f * sc2)
    save()
    translate(0f, -size)
    for (j in 0..(parts - 1)) {
        val scj : Float = sc1.divideScale(j, parts)
        save()
        scale(1f - 2 * j, 1f)
        for (k in 0..(squares - 1)) {
            val sck : Float = scj.divideScale(k, squares)
            save()
            translate(0f, yGap * j)
            drawRect(RectF(0f, 0f, size * sck, yGap), paint)
            restore()
        }
        restore()
    }
    restore()
    restore()
}