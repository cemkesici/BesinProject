package com.cem.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cem.besinler.R
import com.cem.model.Besin
import com.cem.view.BesinListFragmentDirections

class BesinRecyclerAdapter(val besinList: ArrayList<Besin>): RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>() {
    class BesinViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.besi_recycler_row, parent, false)
        return BesinViewHolder(view)
    }
    override fun getItemCount(): Int {
        return besinList.size
    }
    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.textAdDetay).text=besinList.get(position).isim
        holder.itemView.findViewById<TextView>(R.id.textKaloriDetay).text=besinList.get(position).kalori
        //görsel kısmı eklenecek

        holder.itemView.setOnClickListener{
            val action= BesinListFragmentDirections.actionBesinListFragmentToBesinDetayFragment(0)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun besinListesiniGuncelle(yeniListe: List<Besin>){
        besinList.clear()
        besinList.addAll(yeniListe)
        notifyDataSetChanged()
    }
}