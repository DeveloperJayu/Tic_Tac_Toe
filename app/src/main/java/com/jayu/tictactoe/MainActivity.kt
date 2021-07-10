package com.jayu.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var player = true
    private var turnCount = 0
    private var boardStatus = Array(3){IntArray(3)}

    private lateinit var board : Array<Array<Button>>
    private lateinit var turnLabel : TextView
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var btn3 : Button
    private lateinit var btn4 : Button
    private lateinit var btn5 : Button
    private lateinit var btn6 : Button
    private lateinit var btn7 : Button
    private lateinit var btn8 : Button
    private lateinit var btn9 : Button
    private lateinit var btnReset : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        turnLabel = findViewById(R.id.turnLabel)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnReset = findViewById(R.id.btnReset)

        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        btnReset.setOnClickListener {
            player = true
            turnCount = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
            }
        }

        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
        updateLabel("Player X Turn")
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn1 -> {
                updateValue(0, 0, player)
            }
            R.id.btn2 -> {
                updateValue(0,1,player)
            }
            R.id.btn3 -> {
                updateValue(0,2,player)
            }
            R.id.btn4 -> {
                updateValue(1,0,player)
            }
            R.id.btn5 -> {
                updateValue(1,1,player)
            }
            R.id.btn6 -> {
                updateValue(1,2,player)
            }
            R.id.btn7 -> {
                updateValue(2,0,player)
            }
            R.id.btn8 -> {
                updateValue(2,1,player)
            }
            R.id.btn9 -> {
                updateValue(2,2,player)
            }
        }
        turnCount++
        player = !player

        if (player){
            updateLabel("Player X Turn")
        }
        else{
            updateLabel("Player O Turn")
        }

        if (turnCount == 9){
            updateLabel("GAME DRAW")
        }

        checkWinner()

    }

    private fun checkWinner() {
        //Rows
        for(i in 0..2){
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] && boardStatus[i][0] != -1){
                if (boardStatus[i][0] == 1){
                    updateLabel("Player X Win")
                    break
                }
                else{
                    updateLabel("Player O Win")
                    break
                }
            }
        }

        //Columns
        for(i in 0..2){
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i] && boardStatus[0][i] != -1){
                if (boardStatus[0][i] == 1){
                    updateLabel("Player X Win")
                    break
                }
                else{
                    updateLabel("Player O Win")
                    break
                }
            }
        }

        //First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2] && boardStatus[0][0] != -1){
            if (boardStatus[0][0] == 1){
                updateLabel("Player X Win")
            }
            else{
                updateLabel("Player O Win")
            }
        }

        //Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0] && boardStatus[0][2] != -1){
            if (boardStatus[0][2] == 1){
                updateLabel("Player X Win")
            }
            else{
                updateLabel("Player O Win")
            }
        }
    }

    private fun updateLabel(text: String) {
        turnLabel.text = text
        if (text.contains("Win")){
            disableButton()
        }
    }

    private fun disableButton(){
        for (i in board){
            for (button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, column: Int, player: Boolean) {
        val text = if (player) "X" else "O"
        val value = if (player) 1 else 0

        board[row][column].apply{
            isEnabled = false
            setText(text)
        }
        boardStatus[row][column] = value
    }
}