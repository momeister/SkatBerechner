package com.example.skatberechner

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton

class DesiredPlayArea{

    var context: Context
    var activity: MainActivity
    var spielGeklicked = arrayOf(
        arrayOf(1,2,3,4,5,6),
        arrayOf(0,0,0,0,0,0)
    )

    val spielEichel: ImageButton
    val spielBlatt: ImageButton
    val spielHerz: ImageButton
    val spielSchell: ImageButton
    val spielGrand: ImageButton
    val spielNull: ImageButton

    private var statusClass: StatusClass
    private var calc : Calculation

    constructor(activity: MainActivity, context: Context, statusClass: StatusClass){

        this.activity = activity
        this.context = context

        this.statusClass = statusClass

        statusClass.set_Gamemode_Matrix(spielGeklicked)

        spielEichel = activity.findViewById<ImageButton>(R.id.EichelSpiel)
        spielBlatt = activity.findViewById<ImageButton>(R.id.BlattSpiel)
        spielHerz = activity.findViewById<ImageButton>(R.id.HerzSpiel)
        spielSchell = activity.findViewById<ImageButton>(R.id.SchellSpiel)

        spielGrand = activity.findViewById<ImageButton>(R.id.GrandeSpiel)
        spielNull = activity.findViewById<ImageButton>(R.id.NullSpiel)


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

        spielGrand.setOnClickListener {
            animationSelectedGame(spielGrand,5)
        }
        spielNull.setOnClickListener {
            animationSelectedGame(spielNull, 6)
        }


        var calc = Calculation(activity, statusClass)
        this.calc = calc

    }


    private fun animationSelectedGame(selectedCard:ImageButton, x:Int) {
        val animationSpielIn = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        val animationSpielOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        for(i in 0..5){
            var image_dark = when(i+1){
                1 -> R.drawable.eichel_dark
                2 -> R.drawable.blatt_dark
                3 -> R.drawable.herz_dark
                4 -> R.drawable.schell_dark
                5 -> R.drawable.grand_dunkel
                6 -> R.drawable.null_dunkel
                else -> null
            }
            var selectedCard_delete = when(i+1){
                1 -> spielEichel
                2 -> spielBlatt
                3 -> spielHerz
                4 -> spielSchell
                5 -> spielGrand
                6 -> spielNull
                else -> null
            }

            if (selectedCard_delete != null) {
                selectedCard_delete.startAnimation(animationSpielOut)
            }
            if (image_dark != null) {
                if (selectedCard_delete != null) {
                    selectedCard_delete.setImageResource(image_dark)
                }
                if (selectedCard_delete != null) {
                    selectedCard_delete.setBackgroundColor(Color.GRAY)
                }
            }

        }

        if(clicked(x)) {

            var image = when(x){
                1 -> R.drawable.eichel
                2 -> R.drawable.blatt
                3 -> R.drawable.herz
                4 -> R.drawable.schell
                5 -> R.drawable.grand
                6 -> R.drawable.null_game
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
                5 -> R.drawable.grand_dunkel
                6 -> R.drawable.null_dunkel
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
            justOnGameOption()
            spielGeklicked[1][Farbe-1] = 1
            statusClass.set_Gamemode_Matrix(spielGeklicked)
            calc.createTextView()
            return true

        } else {
            spielGeklicked [1][Farbe-1] = 0
            statusClass.set_Gamemode_Matrix(spielGeklicked)
            calc.createTextView()
            return false
        }
    }

    private fun justOnGameOption(){

        for(i in 0..5){
            spielGeklicked[1][i] = 0

        }
    }

}