package com.example.marvelapp.ui.heroes.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.marvelapp.R
import com.example.marvelapp.domain.model.Result
import com.example.marvelapp.ui.heroes.adapter.HeroesAdapter
import kotlinx.android.synthetic.main.heroes_fragment.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import com.example.marvelapp.ui.heroes.viewmodel.HeroesViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject


class HeroesFragment :  Fragment(){

    companion object {
        fun newInstance() = HeroesFragment()
    }

    private var viewModel: HeroesViewModel = HeroesViewModel()
    private val adapter: HeroesAdapter by inject()
    private var heroesList: MutableList<Result> = mutableListOf()

    private var offset: Int = 0
    private var direction: Boolean = true

    private val onHeroesDetailClick = { position: Int ->
        openHeroDetail(position)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.heroes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //1.0 - Setup Layout
        setInitialLayout()

        //2.0 Make ViewModelCalls
        makeViewModelCalls()

        //3.0 Configure Observers
        configureObservers()
    }


    private fun setInitialLayout() {

        setShowcase()

        rv_characters.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_characters.itemAnimator = DefaultItemAnimator()
        rv_characters.adapter = adapter

        rv_characters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1) && (newState == SCROLL_STATE_DRAGGING)) {
                    updateOffset()
                }
            }
        })

    }

    private fun makeViewModelCalls() {

        lav_android.visibility = View.VISIBLE
        rv_characters.visibility = View.INVISIBLE

        viewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)

        viewModel.getHeroes(offset)
    }

    fun configureObservers(){

        viewModel.getHeroesList().observe(this, Observer { heroes ->
            heroes?.let {
                if (direction) rv_characters.scrollToPosition(offset)
                it.data.results.forEach {
                    heroesList.add(it)
                }
                adapter.updateList(heroesList)
                adapter.receiveClick(onHeroesDetailClick)
                lav_android.visibility = View.GONE
                rv_characters.visibility = View.VISIBLE
            }
        })

        viewModel.errorMessage().observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun updateOffset() {
        lav_android.visibility = View.VISIBLE
        rv_characters.visibility = View.INVISIBLE
        offset += 100
        viewModel.getHeroes(offset)
    }

    private fun openHeroDetail(position: Int) {
        val manager: FragmentManager = this.fragmentManager!!
        var transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(
            R.id.container,
            HeroesDetailFragment.newInstance(
                heroesList[position]
            ), "ProductListDialog"
        )
        transaction.commit()
    }

    private fun setShowcase() {

        Picasso.get()
            .load("https://pixelz.cc/wp-content/uploads/2017/11/avengers-age-of-ultron-uhd-4k-wallpaper.jpg")
            .fit()
            .into(image_hero)
    }

}