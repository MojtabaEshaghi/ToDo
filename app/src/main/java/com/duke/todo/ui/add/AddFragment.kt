package com.duke.todo.ui.add

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.duke.todo.R
import com.duke.todo.data.viewModel.ToDoViewModel
import com.duke.todo.databinding.FragmentAddBinding
import com.duke.todo.utils.eToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment(), AddListener {
    private var _binding: FragmentAddBinding? = null
    private val bining get() = _binding!!
    private val TAG = "jojo"
    private lateinit var todoViewModel: ToDoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = bining.root

        todoViewModel = ViewModelProvider(this).get(ToDoViewModel::class.java)
        todoViewModel.addListener = this
        bining.spinnerPrioritiesAddFr.onItemSelectedListener = todoViewModel.SpinnerListener


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_frgment_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_add) {


            todoViewModel.title = (bining.edtTitleAddFr as EditText).text?.toString()
            todoViewModel.description = (bining.edtDesAddFr as EditText).text?.toString()
            todoViewModel.priorites = bining.spinnerPrioritiesAddFr.selectedItem.toString()
            todoViewModel.insertData()

        }

        return super.onOptionsItemSelected(item)


    }

    override fun onStarted() {

    }

    override fun onSuccess() {
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    override fun onFailure(msg: String) {
        eToast(msg, requireContext())
    }


}