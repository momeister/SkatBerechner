package com.example.skatberechner

import android.widget.TextView
import org.w3c.dom.Text

class Calculation {

    var highestReizNumber = 0
    var activity: MainActivity

    private var statusClass: StatusClass

    private var Buben_Matrix : Array<Array<Int>> = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )

    private var Gamemode_Matrix = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )

    private var Ansage_Matrix =  arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )

    constructor(activity: MainActivity, statusClass: StatusClass){
        this.activity = activity
        this.statusClass = statusClass

        createTextView()
    }

    private fun showReizNumber(textView: TextView){
        textView.text = get_Reis_Number()
    }

    fun createTextView(){
        var textView: TextView = activity.findViewById(R.id.ReizView)

        showReizNumber(textView)
    }

    //Calculation
    private fun get_Reis_Number(): String {

        Buben_Matrix = statusClass.get_Buben_Matrix()
        Gamemode_Matrix = statusClass.get_Gamemode_Matrix()
        Ansage_Matrix = statusClass.get_Ansage_Matrix()

        var mitx : Int = 0
        var gamex:Int = 8
        var ansa: Int = 1

        if(Buben_Matrix[1][0] == 1){
            mitx = detection_Buben(1)
        }else{
            mitx = detection_Buben(0)
        }

        gamex = detextion_Gamemode()
        //ansa = detextion_Ansage() such was anderes

        ansa = calculationAnsageWert()

        var result = (mitx+ansa) * gamex

        if(gamex == 1){
            return "--"
        }

        return result.toString()
    }

    private fun calculationAnsageWert(): Int{
        var wert = when(Ansage_Matrix[0][1]){
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 6
            else -> null
        }

//angesagt
        var x = Ansage_Matrix[-1][1]
        if (x == 1 && (wert != 6 && wert != 0)){
            if (wert != null) {
                wert = wert*2
            }
        }

//hand
        if(Ansage_Matrix[1][1] == 1 && (wert != 6)){
            if (wert != null) {
                wert = wert+1
            }
        }

        return wert!!

    /*
        if (wert == 0){
            return 0
        }else if (wert == 1){
            return wert
        } else if (wert == 2){
            return 2
        }else if(wert == 6)
        return 0

 */
    }


    private fun detection_Buben(x:Int): Int{
        var i: Int = 0
        var withx:Int = 1


        while (Buben_Matrix[1][i] == x) {
            withx++
            i++
            if(i == 4){
                break
            }
        }

        return withx
    }

    private fun detextion_Gamemode():Int{
        var i: Int = 0
        var gamex:Int = 12

        while (Gamemode_Matrix[1][i] == 0) {
            gamex--
            i++
            if(i == 5){
                gamex = 1
                break
            }
        }

        if(gamex == 8){
            gamex = 24
        }

        return gamex
    }

    //Errechnet Ansage
    //Output für Multiplikation
    private fun detextion_Ansage():Int{

        //Was wurde ausgewählt (Außer Angesagt)
        for (i in 0..4){
            if(Ansage_Matrix[1][i] == 1){
                var ausgabe = when(i){
                    0 -> 0
                    1 -> 1
                    2 -> 6
                    3 -> 1
                    4 -> 2
                    else -> 0
                }
                return ausgabe
            }else{
                continue
            }
        }

        return 1
    }


}