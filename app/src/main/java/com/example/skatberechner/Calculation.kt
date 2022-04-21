package com.example.skatberechner

import android.widget.TextView
import org.w3c.dom.Text

abstract class Calculation : Update_Changes {

    var highestReizNumber = 0
    abstract var activity: MainActivity

    constructor(activity: MainActivity){
        this.activity = activity

        createTextView()
    }

    private fun showReizNumber(textView: TextView){
        textView.text = get_Reis_Number()
    }

    private fun createTextView(){
        var textView: TextView = activity.findViewById(R.id.ReizView)

        showReizNumber(textView)
    }

    private fun get_Reis_Number(): String {

        return highestReizNumber.toString()
    }

    override fun get_count_Bubes(): Int {
        TODO("Not yet implemented")
    }

    override fun get_gamemode(): Int {
        TODO("Not yet implemented")
    }

}