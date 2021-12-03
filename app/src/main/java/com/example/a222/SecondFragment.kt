package com.example.a222

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a222.databinding.FragmentSecondBinding
import com.example.a222.util.SpinRoll
import kotlin.random.Random


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    var startingHP = 5
    private lateinit var wordToGuess: String


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


// spin function and gone / visable buttons
        button.setOnClickListener {
text.text = spinningWheel()
            button2.visibility=View.VISIBLE
            button3.visibility=View.VISIBLE
            button.visibility=View.GONE
        }

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
        val wheel = SpinRoll.roll()
        var input = editText.text

       // var wheel = dice

        if (startingHP<=0){
        gameLost()
        }
        when (wheel) {
            1 -> {
                startingHP++; return ("You spun nr:$wheel, and you win a spin")
            }
            2 -> {
                startingHP ++; return ("You spun nr:$wheel, and you win a spin")
            }
            3 -> {
                 return ("You spun nr:$wheel, and you have $startingHP spins left")
            }
            4 -> {
                return ("You spun nr:$wheel,and you have $startingHP spins left")
            }
            5 -> {
                startingHP--; return ("You spun nr:$wheel,you loose a spin")
            }
            6 -> {
                startingHP--; return ("You spun nr:$wheel,you loose a spin")
            }
            else ->{
                return ("hi")
            }
        }
    }
/*
    private fun roll(): Int {
        return (1..6).random()
    }
*/
@SuppressLint("SetTextI18n")

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
        textToUser.text ="you guessed wrong the game is lost."
            gameLost()
        }
    }

    fun guessALetter() {
       // println("guess a letter :")
        val editText = binding.USERTEXT
        val input = editText.text
val textToUser: TextView= binding.textToUser
        //var input = readLine()
        if(wordToGuess.contains("$input", ignoreCase = true))
        {
        startingHP++
            textToUser.text= "You are right, The word contains a : $input"

        }
        else{
            startingHP--
            textToUser.text = "Your guess: $input, was wrong spin and try again "
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

    fun gameLost(){
    findNavController().navigate(R.id.action_SecondFragment_to_looserFragment)
}
    fun gameWon(){
        findNavController().navigate(R.id.action_SecondFragment_to_winFragment)
    }
    /*fun storeValues(){
        val editText = binding.USERTEXT
        val input = editText.text
        var numberList = input.map { it.digitToInt() }
    }*/
}








