package com.musaozcetin.randomath

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.musaozcetin.randomath.databinding.ActivityQuestionsBinding

class QuestionsActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityQuestionsBinding

    private var currentQuestion : Int = 1
    private var questionsList:ArrayList<Question>? = null
    private var selectedOption : Int = 0
    private var countCorrect: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        questionsList = QuestionGenerator.getQuestions()

        setQuestion()

        binding.opt1.setOnClickListener(this)
        binding.opt2.setOnClickListener(this)
        binding.opt3.setOnClickListener(this)
        binding.opt4.setOnClickListener(this)
        binding.submitButton.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question = questionsList!![currentQuestion-1]

        defaultOptionDesign()

        if(currentQuestion == questionsList!!.size){
            binding.submitButton.text = "COMPLETE"
        }else{
            binding.submitButton.text = "SUBMIT"
        }

        binding.questionText.text = question.question
        binding.opt1.text = question.optionOne.toString()
        binding.opt2.text = question.optionTwo.toString()
        binding.opt3.text = question.optionThree.toString()
        binding.opt4.text = question.optionFour.toString()

    }

    private fun defaultOptionDesign(){
        val options = ArrayList<TextView>()
        options.add(0, binding.opt1)
        options.add(1, binding.opt2)
        options.add(2, binding.opt3)
        options.add(3, binding.opt4)

        for (option in options){
            option.setTextColor(Color.parseColor("#FFFFF"))
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_design)
        }
    }
    override fun onClick(v:View?){
        when(v?.id){
            R.id.opt1 ->{
                selectedOptionDesign(binding.opt1, 1)
            }
            R.id.opt2 ->{
                selectedOptionDesign(binding.opt2, 2)
            }
            R.id.opt3 ->{
                selectedOptionDesign(binding.opt3, 3)
            }
            R.id.opt4 ->{
                selectedOptionDesign(binding.opt4, 4)
            }
            R.id.submitButton ->{
                if(selectedOption == 0){
                    currentQuestion++
                    when{
                        currentQuestion <= questionsList!!.size ->{
                            setQuestion()
                        }else ->{
                            binding.submitButton.setOnClickListener{
                                val intent = Intent(this, EndingActivity::class.java)
                                intent.putExtra(QuestionGenerator.countCorrect, countCorrect)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
                else{
                    val question = questionsList?.get(currentQuestion -1)
                    if (question!!.optionAnswer != selectedOption){
                        answerDesign(selectedOption, R.drawable.wrong_option_design)
                    }else{
                        countCorrect++
                    }
                    answerDesign(question.optionAnswer, R.drawable.correct_option_design)

                    if(currentQuestion == questionsList!!.size){
                        binding.submitButton.text = "COMPLETE"
                    }else{
                        binding.submitButton.text = "NEXT"
                    }
                    selectedOption = 0
                }
            }
        }
    }

    private fun answerDesign(answer: Int, drawableView: Int){
        when(answer){
            1 ->{
                binding.opt1.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 ->{
                binding.opt2.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 ->{
                binding.opt3.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 ->{
                binding.opt4.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOptionDesign(selected: TextView, selectedOptionNumber: Int){
        defaultOptionDesign()
        selectedOption = selectedOptionNumber

        selected.setTextColor(Color.parseColor("#000000"))
        selected.background = ContextCompat.getDrawable(this, R.drawable.selected_option_design)
    }
}