package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat

class YellowAdapter(val context: Context, val Yellow : List<Char>): BaseAdapter() {
    override fun getCount(): Int {
        return Yellow.size
    }

    override fun getItem(position: Int): Any {
        return Yellow.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater =
            LayoutInflater.from(context)
        val view: View =inflater.inflate(R.layout.sub_letterlist,parent, false)

        val textViews = view.findViewById<TextView>(R.id.textViewLetter)

        textViews.setText(Yellow[position].toString())
        textViews.setTextColor(ContextCompat.getColor(context, R.color.textyellow))
        textViews.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))

        return view
    }
}