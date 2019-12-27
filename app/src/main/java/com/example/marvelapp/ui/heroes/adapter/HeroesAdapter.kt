package com.example.marvelapp.ui.heroes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.domain.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_heroes.view.*

class HeroesAdapter(
    private val character: MutableList<Result>

):
    RecyclerView.Adapter<HeroesAdapter.ViewHolder>(){

    private lateinit var onHeroesDetailClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_heroes))
    }

    override fun onBindViewHolder(holder: HeroesAdapter.ViewHolder, position: Int) {
        holder.bind(character[position])
    }

    override fun getItemCount() = character.size

    fun updateList(character: MutableList<com.example.marvelapp.domain.model.Result>){
        this.character.clear()
        this.character.addAll(character)
        notifyDataSetChanged()
    }

    fun receiveClick(onHeroesDetailClick: (Int) -> Unit){
        this.onHeroesDetailClick = onHeroesDetailClick
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private lateinit var char: com.example.marvelapp.domain.model.Result

        fun bind(result: com.example.marvelapp.domain.model.Result){
            this.char = result

            Picasso.get()
                .load(result.thumbnail.path + "." + result.thumbnail.extension)
                .fit()
                .into(itemView.image_thumbnail)

            itemView.image_thumbnail.setOnClickListener {
                onHeroesDetailClick.invoke(this.adapterPosition)
            }


        }

    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}