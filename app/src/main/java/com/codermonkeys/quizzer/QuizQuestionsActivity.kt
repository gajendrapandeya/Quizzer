package com.codermonkeys.quizzer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.codermonkeys.quizzer.Constants.USER_NAME

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    //Widgets
    private lateinit var tvQuestion: TextView
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView
    private lateinit var btnSubmit: Button

    //Vars
    //List of questions collected from our Constant Singleton
    private val mQuestionsList = Constants.getQuestions()
    //Denotes the currentPosition in our question
    private var mCurrentPosition = 1
    //denotes the index/position of selected option
    private var mSelectedPosition = 0
    private var mCorrectAnswers = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(USER_NAME)

        initWidgets()
        setQuestion()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

    }

    private fun initWidgets() {
        tvQuestion = findViewById(R.id.tv_question)
        image = findViewById(R.id.iv_image)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question = mQuestionsList[mCurrentPosition - 1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList.size)
            btnSubmit.text = "Finish"
        else
            btnSubmit.text = "Submit"

        progressBar.progress = mCurrentPosition
        tvProgress.text = "${mCurrentPosition}/${progressBar.max}"

        tvQuestion.text = question.question
        image.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()

        mSelectedPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 ->
                tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 ->
                tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 ->
                tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 ->
                tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one ->
                selectedOptionView(tvOptionOne, 1)
            R.id.tv_option_two ->
                selectedOptionView(tvOptionTwo, 2)
            R.id.tv_option_three ->
                selectedOptionView(tvOptionThree, 3)
            R.id.tv_option_four ->
                selectedOptionView(tvOptionFour, 4)
            R.id.btn_submit -> {
                if (mSelectedPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList.size ->
                            setQuestion()
                        else -> {
                            val resultIntent = Intent(this, ResultActivity::class.java)
                            resultIntent.putExtra(USER_NAME, mUserName)
                            resultIntent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            resultIntent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size)
                            startActivity(resultIntent)
                        }
                    }
                } else {
                    val question = mQuestionsList[mCurrentPosition - 1]
                    if(question.correctAnswer != mSelectedPosition)
                        answerView(mSelectedPosition, R.drawable.wrong_option_border_bg)
                    else
                        mCorrectAnswers++
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList.size)
                        btnSubmit.text = "Finish"
                    else
                        btnSubmit.text = "Go to next question"

                    mSelectedPosition = 0
                }
            }
        }
    }
}