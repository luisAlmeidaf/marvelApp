package com.example.marvelapp.ui.heroes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.marvelapp.R
import com.example.marvelapp.domain.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.heroes_detail_fragment.*

class HeroesDetailFragment: Fragment() {

    companion object {

        private const val ARG_HERO = "hero"

        fun newInstance(hero: Result) : HeroesDetailFragment {
            val detail = HeroesDetailFragment().apply {
                this.hero = hero
            }

            val bundle = Bundle()
            bundle.putSerializable(ARG_HERO, hero)
            detail.arguments = bundle

            return detail
        }
    }

    var hero: Result? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.heroes_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hero = (arguments?.getSerializable(ARG_HERO) as? Result)!!

        setupLayout()

    }

    private fun setupLayout() {

        Picasso.get()
            .load(hero!!.thumbnail.path + "." + hero!!.thumbnail.extension)
            .fit()
            .into(image_hero)
        text_name.text = hero!!.name
        if (hero!!.description.isNullOrEmpty()) text_description.text = "No description available" else text_description.text = hero!!.description
        text_comics_published.text =  prepareComicsForShow()
        text_number_series.text = hero?.comics?.items?.size.toString() + " Magazine appearances"

        ic_close.setOnClickListener {
            closeDetailFragment()
        }
    }

    private fun closeDetailFragment() {

        val manager: FragmentManager = this.fragmentManager!!
        var transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(
            R.id.container,
            HeroesFragment.newInstance(), "HeroesList"
        )
        transaction.commit()
        // expandedFragment.visibility = View.VISIBLE
    }

    private fun prepareComicsForShow(): String {
        var comicsPublished: String = ""
        hero?.comics?.items?.forEach {
            comicsPublished = comicsPublished + it.name + "\n"
        }

        return comicsPublished
    }
}