package com.example.flickerfindr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.flickerfindr.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: FlickrViewModel by activityViewModels<FlickrViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//            val savedPhoto = PhotoSaver.savePhoto(binding.cardFlickrPhoto)
//            refreshGallary(savedPhoto)
//            Toast.makeText(context, "Attempted to save photo", Toast.LENGTH_LONG).show()

        }
    }

//    private fun refreshGallary(file: File) {
//        val i = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
//        i.data = Uri.fromFile(file)
//        context?.sendBroadcast(i)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}