package com.duke.todo.ui.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.duke.todo.R
import com.duke.todo.data.adapter.MyRecyclerAdapter
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.viewModel.ToDoViewModel
import com.duke.todo.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), ListListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: MyRecyclerAdapter
    private val todoViewModel: ToDoViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        listAdapter = MyRecyclerAdapter()

        todoViewModel.listListener = this
        todoViewModel.getAllData()


        binding.listRecyclerFr.adapter = listAdapter
        binding.listRecyclerFr.hasFixedSize()

        binding.fabListFr.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_frgment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onStarted() {

    }

    override fun onSuccess(res: LiveData<List<ToDoData>>) {
        res.observe(this, Observer {
            listAdapter.setList(it)
        })
    }

    override fun onFailure() {
        binding.listRecyclerFr.visibility = View.GONE
        binding.imgEmptyListFr.visibility = View.VISIBLE
        binding.txtEmptyListFr.visibility = View.VISIBLE

    }


}