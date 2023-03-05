package com.example.giphyapp.presentation.mainFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.giphyapp.R
import com.example.giphyapp.data.api.NetworkResult
import com.example.giphyapp.databinding.FragmentMainBinding
import com.example.giphyapp.presentation.mainFragment.recView.GifAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var gifListAdapter: GifAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecView()
        setTextChangeListener()
        controlRecView(view)
        observeTilViewModel()

        binding.searchButton.setOnClickListener {
            val query = binding.edTextDesc.text.toString()
            if (viewModel.checkCorrectInput(query)) {
                viewModel.getListOfGifs(query)
            }
        }

    }

    private fun setRecView() {
        gifListAdapter = GifAdapter()
        viewModel.gifList.observe(viewLifecycleOwner) {
            gifListAdapter.submitList(it.data)
        }
        with(binding.recyclerView) {
            adapter = gifListAdapter
        }
        setItemClickListener()
    }

    fun controlRecView(view: View) {
        viewModel.gifList.observe(viewLifecycleOwner) {
            val state = it ?: NetworkResult.Loading()
            gifListAdapter.submitList(it.data)

            when (state) {
                is NetworkResult.Success -> {
                    binding.recyclerView.isVisible = true
                    binding.progressBarr.isVisible = false
                    if (it.data?.isEmpty() == true)
                        Toast.makeText(view.context, "Not found", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Error -> {
                    binding.recyclerView.isVisible = false
                    binding.progressBarr.isVisible = false
                    Toast.makeText(view.context, "Error", Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading -> {
                    binding.recyclerView.isVisible = false
                    binding.progressBarr.isVisible = true
                }
            }
        }
    }

    private fun setItemClickListener() {
        gifListAdapter.onItemClickListener = { position ->
            val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(position)
            findNavController().navigate(direction)
        }
    }

    private fun setTextChangeListener() {
        binding.edTextDesc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputDesc()
            }

        })
    }

    private fun observeTilViewModel() {
        viewModel.errorInput.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.uncorrect_input)
            } else {
                null
            }
            binding.tilDesc.error = message
        }
    }
}