package com.example.skatberechner

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton

class DesiredPlayArea {

    var context: Context
    var activity: MainActivity

    var spielGeklicked = arrayOf(
        arrayOf(1,2,3,4),
        arrayOf(0,0,0,0)
    )

    constructor(activity: MainActivity, context: Context){

        this.activity = activity
        this.context = context

        val spielEichel = activity.findViewById<ImageButton>(R.id.EichelSpiel)



        spielEichel.setOnClickListener{
            animationSelectedGame(spielEichel,1)
        }

    }


    private fun animationSelectedGame(selectedCard:ImageButton, x:Int) {
        val animationSpielIn = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        val animationSpielOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        if(clicked(x)) {
            var image = when(x){
                1 -> R.drawable.eichel
                2 -> R.drawable.skatbuben_2
                3 -> R.drawable.skatbuben_3
                4 -> R.drawable.skatbuben_4
                else -> null
            }
            selectedCard.startAnimation(animationSpielIn)
            if (image != null) {
                selectedCard.setImageResource(image)
            }

        }else {

            var image = when(x){
                1 -> R.drawable.eichel_dark
                2 -> R.drawable.skatbuben_2_dark
                3 -> R.drawable.skatbuben_3_dark
                4 -> R.drawable.skatbuben_4_dark
                else -> null
            }

            selectedCard.startAnimation(animationSpielOut)
            if (image != null) {
                selectedCard.setImageResource(image)
            }

        }
    }

    private fun clicked(Farbe:Int): Boolean {

        if (spielGeklicked[1][Farbe-1] == 0) {
            spielGeklicked[1][Farbe-1] = 1
            return true

        } else {
            spielGeklicked [1][Farbe-1] = 0
            return false
        }
    }

}