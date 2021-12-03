package com.example.a222.util

class Player {
    companion object{

        var startingHP = 5

        fun spinningWheel():String {
           // val editText = binding.USERTEXT
            //var input = editText.text
            // var wheel = dice

            when (val wheel = SpinRoll.roll()) {
                1 -> {
                    startingHP++; return ("sss:,$wheel,dd $startingHP")
                }
                2 -> {
                    startingHP ++; return ("abc,$wheel,$startingHP")
                }
                3 -> {
                    startingHP--  ; return ("abc,$wheel,$startingHP")
                }
                4 -> {
                    startingHP ++ ; return ("abc,$wheel,$startingHP")
                }
                5 -> {
                    startingHP--; return ("abc,$wheel,$startingHP")
                }
                6 -> {
                    startingHP--; return ("abc,$wheel,$startingHP")
                }
                else ->{
                    return ("hi")
                }
            }
        }
    }


}