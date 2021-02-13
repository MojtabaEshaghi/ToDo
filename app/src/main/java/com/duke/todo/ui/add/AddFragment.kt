package com.duke.todo.ui.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.duke.todo.R
import com.duke.todo.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val bining get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = bining.root




        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_frgment_menu, menu)
    }


}