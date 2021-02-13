package com.duke.todo.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duke.todo.R
import com.duke.todo.databinding.FragmentListBinding


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fabListFr.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        binding.layoutListFr.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_updateFragment)
        }

        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_frgment_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}