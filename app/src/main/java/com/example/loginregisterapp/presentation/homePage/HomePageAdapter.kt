package com.example.loginregisterapp.presentation.homePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
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
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(FitCenter(), RoundedCorners(16))
        holder.itemView.apply {
            val name = findViewById<TextView>(R.id.last_run_name_text)
            name.text = run.name
            val km = findViewById<TextView>(R.id.last_run_km_text)
            km.text = run.km.toString() + " km"
            val imageHolder = findViewById<ImageView>(R.id.last_run_img)
            Glide.with(context)
                .load(run.image)
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(imageHolder)


        }
    }

    override fun getItemCount(): Int {
        return runs.size
    }
}