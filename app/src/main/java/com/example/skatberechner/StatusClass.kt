package com.example.skatberechner

class StatusClass {

    private var BubenMatrix : Array<Array<Int>> = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )
    private var GamemodeMatrix : Array<Array<Int>> = arrayOf(
        arrayOf(0,0,0,0),
        arrayOf(0,0,0,0)
    )

    private var AnsageMatrix : Array<Array<Int>> = arrayOf(
        arrayOf(0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0)
    )

    private var angesagt = 0

    fun get_Buben_Matrix() : Array<Array<Int>>{
        return BubenMatrix
    }
    fun set_Buben_Matrix(BubenMatrix: Array<Array<Int>>){
        this.BubenMatrix = BubenMatrix
    }

    fun get_Gamemode_Matrix() : Array<Array<Int>>{
        return GamemodeMatrix
    }
    fun set_Gamemode_Matrix(GamemodeMatrix : Array<Array<Int>>){
        this.GamemodeMatrix = GamemodeMatrix
    }

    fun get_Ansage_Matrix() : Array<Array<Int>>{
        return AnsageMatrix
    }
    fun set_Ansage_Matrix(AnsageMatrix : Array<Array<Int>>){
        this.AnsageMatrix = AnsageMatrix
    }
    fun get_Ansage():Int{
        return angesagt
    }
    fun set_Ansage(angesagt:Int){
        this.angesagt = angesagt
    }

}