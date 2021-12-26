package com.example.tilesapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class TilesView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
//  Проверка, что мы отрисовали все, чтобы не перерерисовать все по действию
    var flag = true
//  Задаем количество плиток
    val columns = 4
    val rows = 4
//  Расстояние между плитками
    val outline = 10
    var widthField = 0
    var heightField = 0
//  Создаем массив плиток из объектов класса Tiles
    val tiles = Array(columns) {
        arrayOfNulls<Tile>(
            rows
        )
    }
    var darkColor = Color.GRAY
    var lightColor = Color.YELLOW //заранее определяем цвета
    override fun onDraw(canvas: Canvas) {

//      Получение параметров экрана
        widthField = canvas.width
        heightField = canvas.height

//      Получение размеров нашей плитки
        val tilesWidth = widthField / columns
        val tilesHeight = heightField / rows

//      Задаем наши цвета: Желтый
        val light = Paint()
        light.color = lightColor
//      Задем наши цвета: Черный
        val dark = Paint()
        dark.color = darkColor

//      Стилизуем наши плитки
        light.style = Paint.Style.FILL
        dark.style = Paint.Style.FILL

//      Задаем координаты плиток
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                val left = j * tilesWidth
                val top = i * tilesHeight
                val right = left + tilesWidth
                val bottom = top + tilesHeight

//              Создаем плитку
                val tile = Rect()
                tile[left + outline, top + outline, right - outline] = bottom - outline
                var color: Int

//              Рандомное распределение плиток по полю
                if (flag) {
                    color = if (Math.random() > 0.5) {
                        canvas.drawRect(tile, light)
                        1
                    } else {
                        canvas.drawRect(tile, dark)
                        0
                    }
//                  Создание объекта с полученными данными
                    tiles[i][j] = Tile(left, top, right, bottom, color)
                } else {
                    color = tiles[i][j]!!.color
                    color = if (color == 0) {
                        canvas.drawRect(tile, light)
                        1
                    } else {
                        canvas.drawRect(tile, dark)
                        0
                    }
                }
            }
        }
//      Ставим флаг, чтобы не отрисовывалось все заново
        if (flag) flag = false
//      Рисуем!
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
//      Получение координаты события
        val x = event.x.toInt()
        val y = event.y.toInt()

//      Определеяем тип события
        if (event.action == MotionEvent.ACTION_DOWN) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {

//                  Определяем плитку, которую коснулся пользователь
                    if (tiles[i][j]!!.left < x && tiles[i][j]!!.right > x && tiles[i][j]!!.top < y && tiles[i][j]!!.bottom > y) {
                        for (ii in 0 until rows) {
                            for (jj in 0 until columns) {

//                              Инвертируем цвет плитки
                                if (ii == i || jj == j) {
                                    if (tiles[ii][jj]!!.color == 1) tiles[ii][jj]!!.color =
                                        0 else tiles[ii][jj]!!.color = 1
                                }
                            }
                        }
                        break
                    }
                }
            }

//          Создаем счетчик выигрыша, который будет считать количество плиток одинакового цвета
            var winSum = 0
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    //проходим по массиву и получаем сумму
                    winSum += tiles[i][j]!!.color
                }
            }
//          Если сумма равна общему количеству плиток, то пользователь победил
            if (winSum == 0 || winSum == columns * rows) {
                val toast = Toast.makeText(
                    context,
                    "Поздравляю с победой!", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
//      Перерисовываем экран
        invalidate()
        return true
    }
}