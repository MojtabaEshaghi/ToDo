package com.duke.todo.ui.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.duke.todo.R
import com.duke.todo.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    private var _bindig: FragmentUpdateBinding? = null
    private val binding get() = _bindig!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root


        setHasOptionsMenu(true)

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_meun, menu)
    }


}