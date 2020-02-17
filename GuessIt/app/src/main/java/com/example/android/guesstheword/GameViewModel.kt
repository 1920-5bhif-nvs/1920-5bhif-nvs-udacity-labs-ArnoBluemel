package com.example.android.guesstheword

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
    get() = _eventGameFinish

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
    get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    init {
        Log.i("GameViewModel", "GameViewModel created!")

        _score.value = 0

        _eventGameFinish.value = false
        resetList()
        nextWord()
    }

    override fun onCleared() {
        Log.i("GameViewModel", "GameViewModel destroyed!")
        super.onCleared()
    }

    /**
     * Resets the list of words and randomizes the order
     */
    fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            _eventGameFinish.value = true
        } else {
            _word.value = wordList.removeAt(0)
        }
        //updateWordText()
        //updateScoreText()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }
}