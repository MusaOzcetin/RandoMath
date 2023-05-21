package com.musaozcetin.randomath
import kotlin.random.Random

object QuestionGenerator {

    val countCorrect: String = "count_correct_answers"

    fun getQuestions(): ArrayList<Question> {

        val questionsList = ArrayList<Question> ()

            val randomNumbers = IntArray(96)

        // for random addition problems
        for (i in 0 .. 2) {
                randomNumbers[i] = Random.nextInt(1,1000)
                randomNumbers[i+1] = Random.nextInt(1,1000)

                val quest =
                    Question ("$i",
                    " ${randomNumbers[i]} " + "+" + " ${randomNumbers[i+1]} = " + "?",
                    randomNumbers[i] + randomNumbers[i+1],
                    randomNumbers[i] + randomNumbers[i+1] -5,
                    randomNumbers[i] + randomNumbers[i+1] +5,
                    randomNumbers[i] + randomNumbers[i+1] +10,
                    1
                )
            questionsList.add(quest)
        }

        // for random subtraction problems
        for (i in 3 .. 5) {
            randomNumbers[i] = Random.nextInt(1,1000)
            randomNumbers[i+1] = Random.nextInt(1,1000)

            val quest =
                Question ("$i",
                    " ${randomNumbers[i]} " + "-" + " ${randomNumbers[i+1]} = " + "?",
                    randomNumbers[i] - randomNumbers[i+1]-10,
                    randomNumbers[i] - randomNumbers[i+1],
                    randomNumbers[i] - randomNumbers[i+1] +5,
                    randomNumbers[i] - randomNumbers[i+1] +10,
                    2
                )
            questionsList.add(quest)
        }

        // for random divison problems
        for (i in 6 .. 8) {
            randomNumbers[i] = Random.nextInt(100,1000)
            do{
                randomNumbers[i+1] = Random.nextInt(2,10)
            } while (randomNumbers[i] % randomNumbers[i+1] !=0)

                val quest =
                    Question ("$i",
                        " ${randomNumbers[i]} " + "/" + " ${randomNumbers[i+1]} = " + "?",
                        randomNumbers[i] / randomNumbers[i+1]-2,
                        randomNumbers[i] / randomNumbers[i+1] -1,
                        randomNumbers[i] / randomNumbers[i+1],
                        randomNumbers[i] / randomNumbers[i+1] +2,
                        3
                    )
                questionsList.add(quest)

        }

        // for random multiplication problems
        for (i in 9 .. 11) {
            randomNumbers[i] = Random.nextInt(1,100)
            randomNumbers[i+1] = Random.nextInt(1,20)

            val quest =
                Question ("$i",
                    " ${randomNumbers[i]} " + "*" + " ${randomNumbers[i+1]} = " + "?",
                    randomNumbers[i] * randomNumbers[i+1] -10,
                    randomNumbers[i] * randomNumbers[i+1] -5,
                    randomNumbers[i] * randomNumbers[i+1] +5,
                    randomNumbers[i] * randomNumbers[i+1],
                    4
                )
            questionsList.add(quest)

        }

        questionsList.shuffle()
        return questionsList
    }

}