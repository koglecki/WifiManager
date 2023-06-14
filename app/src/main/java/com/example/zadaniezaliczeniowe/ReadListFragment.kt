package com.example.zadaniezaliczeniowe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zadaniezaliczeniowe.ReadViewModel
import kotlinx.coroutines.launch

class ReadListFragment : Fragment() {
//    private var _binding: ReadListFragmentBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var recyclerView: RecyclerView
//    private val viewModel: ViewModel by activityViewModels {
//        ReadViewModelFactory(
//            (activity?.application as WifiApplication).database.readDao()
//        )
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = ReadListFragmentBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        recyclerView = binding.recyclerView
//        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
//        val adapter = ReadListAdapter {
//            //val action =   ReadListFragment.actionItemListFragmentToItemDetailFragment(it.id)
//            //this.findNavController().navigate(R.id.action_readListFragment_to_mainScreenFragment)
//        }
//        binding.recyclerView.adapter = adapter
//
////        lifecycle.coroutineScope.launch {
////            viewModel.allReads().collect()
////        }
////        viewModel.allReads().observe(this.viewLifecycleOwner) { items ->
////            items.let {
////                adapter.submitList(it)
////            }
////        }
//
//
//
//    }
}