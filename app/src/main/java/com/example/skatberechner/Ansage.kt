package com.example.skatberechner

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.animation.AnimationUtils
import android.widget.ImageButton

class Ansage {

    var ansagegeklickt = arrayOf(
        //1: keine 2:Hand 3: Ouvert 4: Schneider 5: Schwarz
        arrayOf(1,2,3,4,5,6),
        arrayOf(1,0,0,0,0,6)
    )

    var activity: MainActivity
    var context: Context
    var statusClass: StatusClass
    var calc: Calculation

    var ansageKeine: ImageButton
    var ansageHand: ImageButton
    var ansageOuvert: ImageButton
    //var ansageSchneider: ImageButton
    //var ansageSchwarz: ImageButton
    var angesagtangesagt: ImageButton

    var statusKeineSchneiderSchwarz: Int = 0

    constructor(activity: MainActivity, context: Context, statusClass: StatusClass){
        this.activity = activity
        this.context = context
        this.statusClass = statusClass

        ansageKeine = activity.findViewById<ImageButton>(R.id.KeineAnsage)
        ansageHand = activity.findViewById<ImageButton>(R.id.HandAnsage)
        ansageOuvert = activity.findViewById<ImageButton>(R.id.OuverteAnsage)
        //ansageSchneider = activity.findViewById<ImageButton>(R.id.SchneiderAnsage)
        //ansageSchwarz = activity.findViewById<ImageButton>(R.id.SchwarzAnsage)
        angesagtangesagt = activity.findViewById(R.id.AnsageAnsage)

        ansageKeine.setOnClickListener {
            //animationSelectedAnsage(ansageKeine,1)
            if(statusKeineSchneiderSchwarz <2){
                statusKeineSchneiderSchwarz++
                keineSchneiderSchwarz(ansageKeine)
            }else{
                statusKeineSchneiderSchwarz = 0
                keineSchneiderSchwarz(ansageKeine)
            }
        }

        ansageHand.setOnClickListener {
            animationSelectedAnsage(ansageHand,2)
        }

        ansageOuvert.setOnClickListener {
            animationSelectedAnsage(ansageOuvert,3)
        }
       /* ansageSchneider.setOnClickListener {
            animationSelectedAnsage(ansageSchneider,4)
        }
        ansageSchwarz.setOnClickListener {
            animationSelectedAnsage(ansageSchwarz,5)
        }

        */

        angesagtangesagt.setOnClickListener {
            animationSelectedAnsage(angesagtangesagt,6)
        }

        var calc = Calculation(activity, statusClass)
        this.calc = calc
    }
//ist nicht rassistisch, sondern nur die Feldnamen xD
    private fun keineSchneiderSchwarz(selectedCart: ImageButton){
        var image = when(statusKeineSchneiderSchwarz){
            0 -> R.drawable.ansage_keine
            1 -> R.drawable.schneider_ansage
            2 -> R.drawable.schwarz_ansage
            else -> null
        }

        if(image != null){
            selectedCart.setImageResource(image)
        }
    }

    private fun animationSelectedAnsage(selectedCart: ImageButton, x:Int){
        val animationAnsageIn = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        val animationAnsageOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        for(i in 0..5){
            var image_dark = when(i+1){
                1 -> R.drawable.ansage_keine_dunkel
                2 -> R.drawable.ansage_hand_dunkel
                3 -> R.drawable.ansage_ouvert_dunkel
                4 -> R.drawable.schneider_ansage_dunkel
                5 -> R.drawable.schwarz_ansage_dunkel
                6 -> R.drawable.ansage_angesagt_dunkel

                else -> null
            }
            var selectedCard_delete = when(i+1){
                1 -> ansageKeine
                2 -> ansageHand
                3 -> ansageOuvert
                //4 -> ansageSchneider
                //5 -> ansageSchwarz
                6 -> angesagtangesagt
                else -> null
            }

            if (selectedCard_delete != null) {
                selectedCard_delete.startAnimation(animationAnsageOut)
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


        if(clicked(x)){
            var image = when(x){
                1 -> R.drawable.ansage_keine
                2 -> R.drawable.ansage_hand
                3 -> R.drawable.ansage_ouvert
                //4 -> R.drawable.schneider_ansage
                //5 -> R.drawable.schwarz_ansage
                6 -> R.drawable.ansage_angesagt
                else -> null
            }
            selectedCart.startAnimation(animationAnsageIn)
            if(image != null){
                selectedCart.setImageResource(image)
            }
        }else{

            var image = when(x){
                1 -> R.drawable.ansage_keine_dunkel
                2 -> R.drawable.ansage_hand_dunkel
                3 -> R.drawable.ansage_ouvert_dunkel
                //4 -> R.drawable.schneider_ansage_dunkel
                //5 -> R.drawable.schwarz_ansage_dunkel
                6 -> R.drawable.ansage_angesagt_dunkel
                else -> null
            }

            selectedCart.startAnimation(animationAnsageOut)
            if(image != null){
                selectedCart.setImageResource(image)
            }
        }

    }

    //Setzt Matrix auf das, was geklickt wurde + übergibt statusClasse + Aktuallisiert TextView
    private fun clicked(x: Int): Boolean{
        if (ansagegeklickt[1][x-1] == 0) {
            justOnGameOption()
            ansagegeklickt[1][x-1] = 1
            statusClass.set_Ansage_Matrix(ansagegeklickt)
            calc.createTextView()
            return true

        } else {
            ansagegeklickt [1][x-1] = 0
            statusClass.set_Ansage_Matrix(ansagegeklickt)
            calc.createTextView()
            return false
        }
    }

    private fun justOnGameOption(){
        for (i in 0..5){
            ansagegeklickt[1][i]=0
        }
    }

}