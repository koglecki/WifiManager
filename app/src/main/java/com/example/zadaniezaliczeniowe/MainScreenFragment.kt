package com.example.zadaniezaliczeniowe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.zadaniezaliczeniowe.databinding.MainScreenFragmentBinding

class MainScreenFragment : Fragment() {

    private var _binding: MainScreenFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainScreenFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.read.setOnClickListener {
            //findNavController().navigate(R.id.action_mainScreenFragment_to_readListFragment)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}