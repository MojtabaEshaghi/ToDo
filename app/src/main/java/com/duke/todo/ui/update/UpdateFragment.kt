package com.duke.todo.ui.update

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.duke.todo.R
import com.duke.todo.data.db.entity.ToDoData
import com.duke.todo.data.viewModel.ToDoViewModel
import com.duke.todo.databinding.FragmentUpdateBinding
import com.duke.todo.utils.eToast
import com.duke.todo.utils.sToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment(), UpdateListener {
    private var _bindig: FragmentUpdateBinding? = null
    private val binding get() = _bindig!!
    private val arg by navArgs<UpdateFragmentArgs>()
    private val todoViewModel: ToDoViewModel by activityViewModels()
    private lateinit var todoModelUpDate: ToDoData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root


        todoViewModel.updateListener = this
        binding.edtTitleUpdateFr.setText(arg.readToDo.title)
        binding.edtDesUpdateFr.setText(arg.readToDo.description)
        binding.spinnerPrioritiesUpdateFr.onItemSelectedListener = todoViewModel.SpinnerListener
        binding.spinnerPrioritiesUpdateFr.setSelection(todoViewModel.parsePriority(arg.readToDo.priorities))
        todoModelUpDate = ToDoData(
            arg.readToDo.id,
            arg.readToDo.title,
            arg.readToDo.description,
            arg.readToDo.priorities
        )

        setHasOptionsMenu(true)

        return view

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_meun, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_delete -> confirmDeleteItem()
            R.id.menu_update -> updateTodo()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateTodo() {

        if (!(binding.edtTitleUpdateFr.text.toString()
                .isEmpty()) && !(binding.edtDesUpdateFr.text.toString().isEmpty())
        ) {
            todoModelUpDate = ToDoData(
                arg.readToDo.id,
                binding.edtTitleUpdateFr.text.toString(),
                binding.edtDesUpdateFr.text.toString(),
                todoViewModel.parsePriority(binding.spinnerPrioritiesUpdateFr.selectedItem.toString())
            )


            todoViewModel.updateTodo(todoModelUpDate)
            sToast("item is updated",requireContext())


        }


    }

    private fun confirmDeleteItem() {

        val builer = AlertDialog.Builder(requireContext())
        builer.setPositiveButton("yes") { _, _ ->
            todoViewModel.deleteSingleItem(todoModelUpDate)
            eToast("deleted ${todoModelUpDate.title} ", requireContext())
        }
        builer.setNegativeButton("No") { _, _ -> }
        builer.setTitle("Delete ${todoModelUpDate.title} ? ")
        builer.setMessage("Are you sure you delete ${todoModelUpDate.title} ? ")
        builer.create().show()


    }

    override fun onStarted() {

    }

    override fun onSuccess() {

        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    override fun onFailure(s: String) {
        eToast(s, requireContext())
    }


}