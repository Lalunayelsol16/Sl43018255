package com.example.appsl43018255.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsl43018255.data.retrofit.CallApi
import com.example.appsl43018255.data.retrofit.MainRepository
import com.example.appsl48399119.databinding.FragmentGalleryBinding
import com.example.appsl43018255.ui.ViewModelFactory

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    lateinit var viewModel: GalleryViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = CallApi.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(this, ViewModelFactory(mainRepository))[GalleryViewModel::class.java]


        viewModel.movieList.observe(viewLifecycleOwner) {
            //adapter.setMovies(it)
            binding.rvPhotos.apply {
                this.adapter = AdapterPhotos(it)
                this.layoutManager = LinearLayoutManager(requireContext())
            }
            it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error de llamada de api", Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
               // binding.progressDialog.visibility = View.VISIBLE
            } else {
               // binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getPhotos()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GalleryFragment()
    }

}