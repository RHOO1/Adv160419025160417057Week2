package com.example.adv160419025week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set Player Name
        val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
        txtTurn.text = "$playerName's Turn"

        // set default value
        var userPoints = 0
        var ans = 0

        // function to generate new question
        fun NewQuestion() {
            val rndm = Random()
            val number1 = rndm.nextInt(99)
            val number2 = rndm.nextInt(99)
            ans = number1 + number2

            txtQuestion.text = "${number1} + ${number2}"
            txtAnswer.text?.clear()
        }

        NewQuestion()

        btnSubmit.setOnClickListener {
            val userAnswer = txtAnswer.text.toString()

            if (userAnswer.equals(ans.toString())) {
                userPoints++
                Toast.makeText(context, "Selamat jawaban anda benar, skor sekarang ${userPoints} points", Toast.LENGTH_SHORT).show()
                NewQuestion()
            }
            else {
                val action = GameFragmentDirections.actionResultFragment(userPoints)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}