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

    constructor(activity: MainActivity, statusClass: StatusClass){
        this.activity = activity
        this.statusClass = statusClass

        createTextView()
    }

    fun update_Reiz_Number(){

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

        var mitx : Int = 0
        var gamex:Int = 8

        if(Buben_Matrix[1][0] == 1){
            mitx = detection_Buben(1)
        }else{
            mitx = detection_Buben(0)
        }

        gamex = detextion_Gamemode()

        var result = mitx * gamex
        return result.toString()
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
            if(i == 4){
                gamex = 1
                break
            }
        }

        return gamex
    }

    private fun set_Buben_checked(bubenchecked : Array<Array<Int>>){

    }



}