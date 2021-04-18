package com.codermonkeys.quizzer

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val questionOne = Question(
            1, "What is the name of this player?",
            R.drawable.rohit,
            "Virat Kohli",
            "Ms Dhoni",
            "Suresh Raina",
            "Rohit Sharma",
            4
        )
        questionsList.add(questionOne)

        val questionTwo = Question(
            2, "What is the name of this player?",
            R.drawable.sky,
            "Surya Kumar Yadav",
            "Hardick",
            "Krunal",
            "Rohit Sharma",
            1
        )
        questionsList.add(questionTwo)

        val questionThree = Question(
            3, "What is the name of this player?",
            R.drawable.quinti,
            "Quinton Dcock",
            "Beherendof",
            "Trent Boult",
            "Hardik",
            1
        )
        questionsList.add(questionThree)

        val questionFour = Question(
            4, "What is the name of this player?",
            R.drawable.krunal,
            "Joe Wilson",
            "Ajit Agarkar",
            "Krunal Pandya",
            "Kieron Pollard",
            3
        )
        questionsList.add(questionFour)

        val questionFive = Question(
            5, "What is the name of this player?",
            R.drawable.bumrah,
            "Harley Davison",
            "Mohit Suri",
            "Kuldeep Yadav",
            "Jasprit Bumrah",
            4
        )
        questionsList.add(questionFive)

        val questionSix = Question(
            6, "What is the name of this player?",
            R.drawable.hardik,
            "Hardik Pandya",
            "Ms Dhoni",
            "Suresh Raina",
            "Rohit Sharma",
            4
        )
        questionsList.add(questionSix)

        val questionSeven = Question(
            7, "What is the name of this player?",
            R.drawable.boult,
            "Lasith Maling",
            "Virat Kohli",
            "Suresh Raina",
            "Trent Boult",
            4
        )
        questionsList.add(questionOne)

        return questionsList
    }
}