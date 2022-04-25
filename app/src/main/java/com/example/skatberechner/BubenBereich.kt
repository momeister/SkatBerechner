package com.example.skatberechner

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import com.example.skatberechner.Calculation as Calculation1

class BubenBereich : Update_Changes {

    var kartegeklickt = arrayOf(
        arrayOf(1,2,3,4),
        arrayOf(0,0,0,0)
    )
    var context: Context
    var activity: MainActivity

    private var statusClass: StatusClass
    private var calc: com.example.skatberechner.Calculation

    constructor(activity: MainActivity, context: Context){

        this.activity = activity
        this.context = context

        var statusClass = StatusClass()
        this.statusClass = statusClass

        statusClass.set_Buben_Matrix(kartegeklickt)

        var desiredPlayArea = DesiredPlayArea(activity,context, statusClass)
        var ansage = Ansage(activity,context,statusClass)

        val eichelBube = activity.findViewById<ImageButton>(R.id.EichelBubeFeld)
        val blattBube = activity.findViewById<ImageButton>(R.id.GruenerBubeFeld)
        val herzBube = activity.findViewById<ImageButton>(R.id.HerzBubeFeld)
        val schellBube = activity.findViewById<ImageButton>(R.id.SchellBubeFeld)


        eichelBube.setOnClickListener {
            animationSelectedBube(eichelBube,1)
        }
        blattBube.setOnClickListener {
            animationSelectedBube(blattBube,2)
        }
        herzBube.setOnClickListener {
            animationSelectedBube(herzBube,3)
        }
        schellBube.setOnClickListener {
            animationSelectedBube(schellBube,4)
        }

        var calc = com.example.skatberechner.Calculation(activity, statusClass)
        this.calc = calc
    }

    private fun clicked(Farbe:Int): Boolean {

        if (kartegeklickt[1][Farbe-1] == 0) {
            kartegeklickt[1][Farbe-1] = 1
            statusClass.set_Buben_Matrix(kartegeklickt)
            calc.createTextView()
            return true

        } else {
            kartegeklickt [1][Farbe-1] = 0
            statusClass.set_Buben_Matrix(kartegeklickt)
            calc.createTextView()
            return false
        }
    }

    private fun animationSelectedBube(selectedCard:ImageButton, x:Int){
        val animationBubeIn = AnimationUtils.loadAnimation(context,R.anim.fade_in)
        val animationBubeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)

        //1-Eichel, 2-Blatt, 3-Herz, 4-Schell
        if(clicked(x)) {
            var image = when(x){
                1 -> R.drawable.skatbuben_1
                2 -> R.drawable.skatbuben_2
                3 -> R.drawable.skatbuben_3
                4 -> R.drawable.skatbuben_4
                else -> null
            }
            selectedCard.startAnimation(animationBubeIn)
            if (image != null) {
                selectedCard.setImageResource(image)
            }

        }else {

            var image = when(x){
                1 -> R.drawable.skatbuben_1_dark
                2 -> R.drawable.skatbuben_2_dark
                3 -> R.drawable.skatbuben_3_dark
                4 -> R.drawable.skatbuben_4_dark
                else -> null
            }

            selectedCard.startAnimation(animationBubeOut)
            if (image != null) {
                selectedCard.setImageResource(image)
            }

        }
    }

    override var Bubencount: Int = 5

    override fun get_count_Bubes(): Int {
        TODO("Not yet implemented")
        var mitZahl = 0

        for(i in 0..3){
            if(kartegeklickt[0][1] == 1){
                if(kartegeklickt[i][1] == 1){
                    mitZahl++
                }else{
                    break
                }
            }else{
                if(kartegeklickt[i][1] == 0){
                    mitZahl++
                }else{
                    break
                }
            }
        }

        return mitZahl
    }

    override fun get_gamemode(): Int {
        TODO("Not yet implemented")
    }

    override fun set_count_Bubes(): Int {
        TODO("Not yet implemented")
    }

    override fun set_gamemode(): Int {
        TODO("Not yet implemented")
    }

}