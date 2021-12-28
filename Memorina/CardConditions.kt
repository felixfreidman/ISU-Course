package com.example.memorina

import android.widget.ImageView

enum class CardConditions {
    OPENED,
    CLOSED,
    FINISHED
}

class Card(val id: Int, val neighbourId: Int, val view: ImageView, val frontImage: Int, val backImage: Int, var state: CardConditions) {
    // DEBUG
    override fun toString(): String {
        return "Card id ${id}, card imageRes ${frontImage}, card state ${state}"
    }
}