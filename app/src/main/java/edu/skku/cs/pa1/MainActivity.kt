package edu.skku.cs.pa1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import java.io.IOException
import java.util.ArrayList

class MainActivity : ComponentActivity() {
    private val words = mutableListOf<Word>()
    private val Yellow = mutableListOf<Char>()
    private val Gray = mutableListOf<Char>()
    private val Green = mutableListOf<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //read dictionary file
        val fiveLetterWords = mutableListOf<String>()
        try {
            applicationContext.assets.open("wordle_words.txt").use { inputStream ->
                val Words = inputStream.readBytes().toString(Charsets.UTF_8)
                Words.lines().forEach { line ->
                    if (line.length == 5) {
                        fiveLetterWords.add(line)
                    }
                }
            }
        } catch (e: IOException) {
            Log.e("MainActivity", "Error reading words file", e)
        }

        //create random answer
        val random = (0 until fiveLetterWords.size).random()
        val RandomWord = fiveLetterWords[random]
        val button = findViewById<Button>(R.id.button)
        val words = arrayListOf<Word>()

        button.setOnClickListener {

            val userinput = findViewById<EditText>(R.id.editTextWordle).text.toString()

            if (fiveLetterWords.contains(userinput)) {
                words.add(Word(userinput, RandomWord))

              val wordlistAdaptor = WordListViewAdapter(this, words)
              var mainList = findViewById<ListView>(R.id.WordList)
              mainList.adapter = wordlistAdaptor

              val USER = userinput.toCharArray()
              val RANDOM = RandomWord.toCharArray()
                for (a in 0..4) {

                    if (USER[a]==RANDOM[a]) { //green
                        if (!Green.contains(USER[a])) {
                            Green.add(USER[a])

                        }
                        if (Yellow.contains(USER[a])) {
                            Yellow.remove(USER[a])
                        }
                    }

                    else if (RANDOM.contains(USER[a])) {
                        if (RANDOM[a]!=USER[a]) { //yellow
                            if (!Yellow.contains(USER[a])) {
                                Yellow.add(USER[a])
                            }
                        }
                    }

                    else if (!RANDOM.contains(USER[a])) { //gray
                        if (!Gray.contains(USER[a])){
                            Gray.add(USER[a])

                        }
                    }
                }

                val yellowAdaptor = YellowAdapter(this, Yellow)
                var YellowList = findViewById<ListView>(R.id.LetterListYellow)
                YellowList?.adapter = yellowAdaptor

                val greenAdaptor = GreenAdapter(this, Green)
                var GreenList = findViewById<ListView>(R.id.LetterListGreen)
                GreenList?.adapter = greenAdaptor

                val grayAdaptor = GrayAdapter(this, Gray)
                var GrayList = findViewById<ListView>(R.id.LetterListGray)
                GrayList?.adapter = grayAdaptor

                findViewById<EditText>(R.id.editTextWordle).setText("")
            }

            else {
                Toast.makeText(this,"Word '"+userinput+"' not in dictionary!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}
