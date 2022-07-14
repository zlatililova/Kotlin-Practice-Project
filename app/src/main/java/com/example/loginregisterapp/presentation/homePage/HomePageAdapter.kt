package com.example.loginregisterapp.presentation.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.loginregisterapp.R
import com.example.loginregisterapp.data.lastActivityDisplay.LastRunData

class HomePageAdapter(
    val runs: ArrayList<LastRunData>
) : RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {
    class HomePageViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        return HomePageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.subfragment_last_run_home_page,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        val run = runs[position]
        holder.itemView.apply {
            val name = findViewById<EditText>(R.id.last_run_name_text)
             name.setText(run.name)
            val km = findViewById<EditText>(R.id.last_run_km_text)
            km.setText(run.km.toString() + " km")

        }
    }

    override fun getItemCount(): Int {
        return runs.size
    }
}