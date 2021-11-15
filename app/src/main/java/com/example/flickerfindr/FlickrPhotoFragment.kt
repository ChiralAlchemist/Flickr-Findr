package com.example.flickerfindr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.flickerfindr.databinding.FragmentFickrPhotoBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FlickrPhotoFragment : Fragment() {

    private var _binding: FragmentFickrPhotoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: FlickrViewModel by activityViewModels<FlickrViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFickrPhotoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}