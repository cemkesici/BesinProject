package com.cem.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cem.adapter.BesinRecyclerAdapter
import com.cem.besinler.R
import com.cem.viewmodel.BesinListesiViewModel

class BesinListFragment : Fragment() {
    private lateinit var viewModel:BesinListesiViewModel
    private val recyclerBesinAdapter=BesinRecyclerAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(BesinListesiViewModel::class.java)
        viewModel.refreshData()
        val besinListRecycler=view.findViewById<RecyclerView>(R.id.recyclerView)
        besinListRecycler.layoutManager=LinearLayoutManager(context)
        besinListRecycler.adapter=recyclerBesinAdapter
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.besinler.observe(viewLifecycleOwner, Observer {besinler->
        besinler?.let{
            view?.findViewById<TextView>(R.id.textWarning)?.visibility=View.GONE
            view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility=View.GONE
            view?.findViewById<RecyclerView>(R.id.recyclerView)?.visibility=View.VISIBLE
            recyclerBesinAdapter.besinListesiniGuncelle(it)
        }
        })
        viewModel.besinHataMsg.observe(viewLifecycleOwner, Observer { hata->
            hata?.let{
                if(it){
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility=View.GONE
                    view?.findViewById<RecyclerView>(R.id.recyclerView)?.visibility=View.GONE
                    view?.findViewById<TextView>(R.id.textWarning)?.visibility=View.VISIBLE
                }
                else{
                    view?.findViewById<TextView>(R.id.textWarning)?.visibility=View.GONE
                }
            }
        })
        viewModel.besinYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->
            yukleniyor?.let{
                if(it){
                    view?.findViewById<RecyclerView>(R.id.recyclerView)?.visibility=View.GONE
                    view?.findViewById<TextView>(R.id.textWarning)?.visibility=View.GONE
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility=View.VISIBLE
                }
                else{
                    view?.findViewById<ProgressBar>(R.id.progressBar)?.visibility=View.GONE
                }
            }
        })
    }
}