package com.duke.todo.ui.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duke.todo.R
import com.duke.todo.data.adapter.MyRecyclerAdapter
import com.duke.todo.data.viewModel.ToDoViewModel
import com.duke.todo.databinding.FragmentListBinding
import com.duke.todo.utils.eToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(), ListListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val TAG = "jojo"
    private lateinit var listAdapter: MyRecyclerAdapter
    val todoViewModel: ToDoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root



        todoViewModel.listListener = this
        todoViewModel.getAllData()
        todoViewModel.whichMobile()


        listAdapter = MyRecyclerAdapter(todoViewModel.typeMobileBasedVersion)
        binding.listRecyclerFr.adapter = listAdapter
        binding.listRecyclerFr.hasFixedSize()

        todoViewModel.getAllData().observe(viewLifecycleOwner, {
            listAdapter.setList(it)
            todoViewModel.checkIsDataBaseEmpty(it)


        })

        todoViewModel.emptyDataBase.observe(viewLifecycleOwner, {
            if (it) {
                viewGone()
            } else {
                viewVisible()
            }

        })



        hideKeyboard()


        binding.fabListFr.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }



        setHasOptionsMenu(true)

        return view
    }

    private fun viewVisible() {
        binding.listRecyclerFr.visibility = View.VISIBLE
        binding.imgEmptyListFr.visibility = View.GONE
        binding.txtEmptyListFr.visibility = View.GONE
    }

    private fun viewGone() {
        binding.listRecyclerFr.visibility = View.GONE
        binding.imgEmptyListFr.visibility = View.VISIBLE
        binding.txtEmptyListFr.visibility = View.VISIBLE
    }

    private fun hideKeyboard() {

        val view: View? = activity?.currentFocus
        if (view != null) {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_frgment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_delete_all -> confirmDeleteAllItemDelete()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteAllItemDelete() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            todoViewModel.deleteAllItem()
            eToast("deleted all Items  ", requireContext())
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle(" Delete All Items ? ")
        builder.setMessage(" Are you sure you delete All Items ? ")
        builder.create().show()


    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onStarted() {


    }

    override fun onSuccess() {

    }


    override fun onFailure(message: String?) {
        binding.listRecyclerFr.visibility = View.GONE
        binding.imgEmptyListFr.visibility = View.VISIBLE
        binding.txtEmptyListFr.visibility = View.VISIBLE
    }




}