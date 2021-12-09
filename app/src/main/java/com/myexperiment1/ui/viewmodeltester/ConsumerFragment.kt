package com.myexperiment1.ui.viewmodeltester

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.myexperiment1.R
import com.myexperiment1.databinding.FragmentConsumerBinding
import com.myexperiment1.databinding.FragmentProducerBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConsumerFragment : Fragment() {
     private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentConsumerBinding
    private val sharedViewModel:SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConsumerBinding.inflate( inflater,container, false)
        binding.apply {
            this.viewModel =sharedViewModel
        }
        Log.d("TAG---",sharedViewModel.toString())

        sharedViewModel.liveText.observe(viewLifecycleOwner,{
            Log.d("TAG---","livetext changing$it")
//            binding.tvConsumerText.text = it
        })
        binding.setLifecycleOwner(viewLifecycleOwner)

        return binding.root
     }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConsumerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}