package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat

class Word(val user: String, val answer: String)

class WordListViewAdapter(val context: Context, val words: MutableList<Word>): BaseAdapter() {
    private val Yellow = mutableListOf<Char>()
    private val Gray = mutableListOf<Char>()
    private val Green = mutableListOf<Char>()

    private val yellowAdapter = YellowAdapter(context, Yellow)
    private val grayAdapter = GrayAdapter(context, Gray)
    private val greenAdapter = GreenAdapter(context, Green)

    override fun getCount(): Int {
        return words.size
    }

    override fun getItem(position: Int): Any {
        return words.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater =
            LayoutInflater.from(context)
        val view: View =inflater.inflate(R.layout.sub_wordlist,null)

        val UsercharArray = words.get(i).user.toCharArray()
        val AnswercharArray = words.get(i).answer.toCharArray()

        val textViews = arrayOf(
            view.findViewById<TextView>(R.id.textViewWord1),
            view.findViewById<TextView>(R.id.textViewWord2),
            view.findViewById<TextView>(R.id.textViewWord3),
            view.findViewById<TextView>(R.id.textViewWord4),
            view.findViewById<TextView>(R.id.textViewWord5)
        )
        for (a in 0..4) {
            if (UsercharArray[a] == AnswercharArray[a]) {
                textViews[a].setText(UsercharArray[a].toString())
                textViews[a].setTextColor(ContextCompat.getColor(context, R.color.textgreen))
                textViews[a].setBackgroundColor(ContextCompat.getColor(context, R.color.green))

            } else if (AnswercharArray.contains(UsercharArray[a])) {
                textViews[a].setText(UsercharArray[a].toString())
                textViews[a].setTextColor(ContextCompat.getColor(context, R.color.textyellow))
                textViews[a].setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))

            } else {
                textViews[a].setText(UsercharArray[a].toString())
                textViews[a].setTextColor(ContextCompat.getColor(context, R.color.textgray))
                textViews[a].setBackgroundColor(ContextCompat.getColor(context, R.color.gray))

            }
        }

        return view
    }
}


