package com.example.marvelheroesapp.heroeslist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelheroesapp.R
import com.example.marvelheroesapp.data.remote.model.general.Result
import com.example.marvelheroesapp.utils.GeneralUtils
import kotlinx.android.synthetic.main.hero_item_layout.view.*

class HeroesListAdapter(private val heroes: ArrayList<Result>) :
    RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hero_item_layout, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount(): Int = heroes.size

    class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hero: Result) {
            itemView.apply {
                tvHeroItem.text = hero.name
                Glide.with(ivHeroItem.context)
                    .load(
                        GeneralUtils.getComposedImageUrl(
                            hero.thumbnail.path,
                            hero.thumbnail.extension
                        )
                    )
                    .into(ivHeroItem)
            }
        }
    }

    fun addHeroes(heroes: List<Result>) {
        this.heroes.apply {
            clear()
            addAll(heroes)
        }
    }
}