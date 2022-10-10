package com.aaronbgrant.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.aaronbgrant.criminalintent.databinding.ListItemCrimeBinding
import com.aaronbgrant.criminalintent.databinding.ListItemCrimePoliceBinding

enum class POLICE(val value: Int) {
    YES(1),
    NO(0);
}

open class CrimeHolder(
    private val binding: ViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

}

class NoPoliceCrimeHolder(
    private val binding: ListItemCrimeBinding
): CrimeHolder(binding) {
    fun bind(crime: Crime) {
        binding.crimeListTitle.text = crime.title
        binding.crimeListDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context, "${crime.title} clicked!",
                Toast.LENGTH_SHORT ).show()
        }
    }
}

class PoliceCrimeHolder(
    private val binding: ListItemCrimePoliceBinding
): CrimeHolder(binding) {
    fun bind(crime: Crime) {
        binding.crimeListTitle.text = crime.title
        binding.crimeListDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context, "${crime.title} clicked!",
                Toast.LENGTH_SHORT ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) : CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)

        if(viewType == POLICE.NO.value) {
            val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
            return NoPoliceCrimeHolder(binding)
        } else {
            val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
            return PoliceCrimeHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        if(holder is NoPoliceCrimeHolder) {
            (holder as NoPoliceCrimeHolder).bind(crime)
        } else {
            (holder as PoliceCrimeHolder).bind(crime)
        }

    }

    override fun getItemCount(): Int = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return when(crime.requiresPolice) {
            true -> POLICE.YES.value
            else -> POLICE.NO.value
        }
    }

}
