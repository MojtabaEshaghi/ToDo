package com.duke.todo.ui.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.duke.todo.R
import com.duke.todo.data.db.entity.Priorities
import com.duke.todo.data.viewModel.ToDoViewModel
import com.duke.todo.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private var _bindig: FragmentUpdateBinding? = null
    private val binding get() = _bindig!!
    private val arg by navArgs<UpdateFragmentArgs>()
    private lateinit var todoViewModel: ToDoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        todoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]



        binding.edtTitleUpdateFr.setText(arg.readToDo.title)
        binding.edtDesUpdateFr.setText(arg.readToDo.description)
        binding.spinnerPrioritiesUpdateFr.onItemSelectedListener = todoViewModel.SpinnerListener
        binding.spinnerPrioritiesUpdateFr.setSelection(parsePriority(arg.readToDo.priorities))

        setHasOptionsMenu(true)

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_meun, menu)
    }

    private fun parsePriority(prioritiy: Priorities): Int {

        return when (prioritiy) {
            Priorities.LOW -> 2
            Priorities.MEDIUM -> 1
            Priorities.HIGH -> 0
        }


    }


}