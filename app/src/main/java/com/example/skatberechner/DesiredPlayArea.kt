package com.example.skatberechner

import android.content.Context
import android.graphics.Color
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
        val spielBlatt = activity.findViewById<ImageButton>(R.id.BlattSpiel)
        val spielHerz = activity.findViewById<ImageButton>(R.id.HerzSpiel)
        val spielSchell = activity.findViewById<ImageButton>(R.id.SchellSpiel)



        spielEichel.setOnClickListener{
            animationSelectedGame(spielEichel,1)
        }
        spielBlatt.setOnClickListener {
            animationSelectedGame(spielBlatt,2)
        }
        spielHerz.setOnClickListener {
            animationSelectedGame(spielHerz,3)
        }
        spielSchell.setOnClickListener {
            animationSelectedGame(spielSchell,4)
        }

    }


    private fun animationSelectedGame(selectedCard:ImageButton, x:Int) {
        val animationSpielIn = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        val animationSpielOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        if(clicked(x)) {
            var image = when(x){
                1 -> R.drawable.eichel
                2 -> R.drawable.blatt
                3 -> R.drawable.herz
                4 -> R.drawable.schell
                else -> null
            }
            selectedCard.startAnimation(animationSpielIn)
            if (image != null) {
                selectedCard.setImageResource(image)
                selectedCard.setBackgroundColor(Color.WHITE)
            }
        }else {

            var image = when(x){
                1 -> R.drawable.eichel_dark
                2 -> R.drawable.blatt_dark
                3 -> R.drawable.herz_dark
                4 -> R.drawable.schell_dark
                else -> null
            }

            selectedCard.startAnimation(animationSpielOut)
            if (image != null) {
                selectedCard.setImageResource(image)
                selectedCard.setBackgroundColor(Color.GRAY)
            }
        }
    }

    private fun clicked(Farbe:Int): Boolean {

        if (spielGeklicked[1][Farbe-1] == 0) {
            //justOnGameOption()
            spielGeklicked[1][Farbe-1] = 1
            return true

        } else {
            spielGeklicked [1][Farbe-1] = 0
            return false
        }
    }

    private fun justOnGameOption(){
        var y = 99
        for(i in 0..3){
            if(spielGeklicked[1][i] == 1){
                spielGeklicked[1][i] = 0
            }
        }
    }

}