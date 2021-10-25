package com.example.calculatelistcard

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calculatelistcard.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FoodViewModel by activityViewModels()

    private val foodController : FoodController by lazy {
        FoodController(
            itemClickListener = {

            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        initEvent()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.foodEpoxyRecyclerView.setController(controller = foodController)
        binding.foodEpoxyRecyclerView.layoutManager = linearLayoutManager
    }

    private fun observe(){
        viewModel.foodLists.observe(viewLifecycleOwner){ list ->
            binding.swipeRefresh.isRefreshing = false
            foodController.submitData(list = list)
        }
    }

    private fun initEvent(){

        binding.viewCardMaterialCardView.setOnClickListener {
            findNavController().navigate(R.id.action_listItemFragment_to_viewCardFragment)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        }

        binding.searchInputText.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                val searchValue = binding.searchInputText.text.toString()
                viewModel.search(searchValue.toInt())
                return@OnEditorActionListener false
            }
            false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}