package com.example.a222

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a222.databinding.FragmentSecondBinding
import kotlin.random.Random


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var startingHP = 5

    private lateinit var wordToGuess: String
    var alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray()

private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentSecondBinding.inflate(inflater, container, false)
      return binding.root
//set up logic

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = binding.spinWheelButton
        val button2: Button=binding.buttonSecond
        val button3: Button =binding.buttonFullWord
        val text: TextView = binding.SpinNumber
        val text1: TextView = binding.HP
        val text2: TextView = binding.showAlphabet
        button2.visibility=View.GONE
        button3.visibility=View.GONE



        button.setOnClickListener {
text.text = spinningWheel()
            button2.visibility=View.VISIBLE
            button3.visibility=View.VISIBLE
            button.visibility=View.GONE}

        button2.setOnClickListener { guessALetter()
            button.visibility=View.VISIBLE
            button2.visibility=View.GONE
            button3.visibility=View.GONE}

        button3.setOnClickListener { guessAWord()
            button.visibility=View.VISIBLE
            button3.visibility=View.GONE
            button2.visibility=View.GONE}

                text1.text = getRandomWord()

    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun spinningWheel():String {
        val editText = binding.USERTEXT
        var input = editText.text
        var wheel = roll()


        if (startingHP<1){
        gameLost()
        }


        when (wheel) {
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

    private fun roll(): Int {
        return (1..6).random()
    }

    fun guessAWord(){
        // print("Guess an Animal")
        val editText = binding.USERTEXT
        val input = editText.text
        val textToUser: TextView = binding.textToUser
        if (input.toString() == wordToGuess)
        {
            gameWon()
            //println("you guessed $input")
            //println("you guessed wrong try again the word was $wordToGuess")
        }else{
            //println("you guessed $input")
            //println("you were right u win the word was $wordToGuess")
        textToUser.text ="you guessed wrong spin again."
            startingHP--
        }
    }

    fun guessALetter() {
       // println("guess a letter :")
        val editText = binding.USERTEXT
        val input = editText.text

        //var input = readLine()
        if(wordToGuess.contains("$input", ignoreCase = true))
        {
        gameWon()
        }
        else{
            gameLost()
        }
    }


    fun getRandomWord():String{
        val randomIndex = Random.nextInt(0, GameConstants.words.size)
        wordToGuess = GameConstants.words[randomIndex]

      //  println(wordToGuess)
     //   println(randomIndex)

    return wordToGuess

    }

    object GameConstants{
        val words = listOf(
            "dog",
            "cat",
            "fish")

    }

   fun storeValues(){
       val editText = binding.USERTEXT
       val input = editText.text
       var numberList = input.map { it.digitToInt() }
   }

    fun gameLost(){
    findNavController().navigate(R.id.action_SecondFragment_to_looserFragment)
}

    fun gameWon(){
        findNavController().navigate(R.id.action_SecondFragment_to_winFragment)
    }
}








