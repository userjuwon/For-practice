package edu.skku.cs.pa1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat

class GrayAdapter(val context: Context, val Gray : List<Char>): BaseAdapter() {
    override fun getCount(): Int {
        return Gray.size
    }

    override fun getItem(position: Int): Any {
        return Gray.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater =
            LayoutInflater.from(context)
        val view: View =inflater.inflate(R.layout.sub_letterlist,parent, false)

        val textViews = view.findViewById<TextView>(R.id.textViewLetter)

        textViews.setText(Gray[position].toString())
        textViews.setTextColor(ContextCompat.getColor(context, R.color.textgray))
        textViews.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))

        return view
    }
}