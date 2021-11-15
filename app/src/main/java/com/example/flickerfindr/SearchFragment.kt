package com.example.flickerfindr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.flickerfindr.adapter.FlickrListAdapter
import com.example.flickerfindr.databinding.FragmentSearchBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val viewModel: FlickrViewModel by activityViewModels<FlickrViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.photosGrid.adapter = FlickrListAdapter { flickrPhoto ->
            viewModel.setSelectedImage(flickrPhoto)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.textField.editText?.doOnTextChanged { inputText, _, _, _ ->
//            viewModel.getSearchResults(inputText.toString())
        }
        binding.searchButton.setOnClickListener {
            viewModel.getSearchResults(binding.textField.editText?.text.toString())
        }

        binding.newestChip.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSearchNewest(isChecked)
        }
        binding.oldestChip.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSearchNewest(!isChecked)
        }
        binding.localChip.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSearchLocal(isChecked)
        }
        binding.globalChip.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setSearchLocal(!isChecked)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}