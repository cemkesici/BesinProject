package com.cem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cem.besinler.R
import com.cem.viewmodel.BesinDetayiViewModel

class BesinDetayFragment : Fragment() {
    private lateinit var viewModel:BesinDetayiViewModel
    private var besinId=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(BesinDetayiViewModel::class.java)
        viewModel.roomVerisiniAl()
        observeLiveData()

    }
    fun observeLiveData(){
        viewModel.besinLiveData.observe(viewLifecycleOwner, Observer { besin->
            besin?.let{
                view?.findViewById<TextView>(R.id.besinIsim)?.text=it.isim
                view?.findViewById<TextView>(R.id.besinKalori)?.text=it.kalori
                view?.findViewById<TextView>(R.id.besinKarbonhidrat)?.text=it.karbonhidat
                view?.findViewById<TextView>(R.id.besinYag)?.text=it.yag
                view?.findViewById<TextView>(R.id.besinProtein)?.text=it.protein
            }
        })
    }

}