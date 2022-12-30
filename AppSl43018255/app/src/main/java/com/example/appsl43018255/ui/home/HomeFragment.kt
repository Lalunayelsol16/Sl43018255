package com.example.appsl43018255.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appsl43018255.data.retrofit.CallApi
import com.example.appsl43018255.data.retrofit.MainRepository
import com.example.appsl48399119.databinding.FragmentHomeBinding
import com.example.appsl43018255.ui.ViewModelFactory
import com.example.appsl43018255.ui.ViewModelFactoryTodos
import com.example.appsl43018255.ui.gallery.AdapterPhotos
import com.example.appsl43018255.ui.gallery.GalleryFragment
import com.example.appsl43018255.ui.gallery.GalleryViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    lateinit var viewModel: HomeViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitService = CallApi.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel = ViewModelProvider(this, ViewModelFactoryTodos(mainRepository))[HomeViewModel::class.java]


        viewModel.movieList.observe(viewLifecycleOwner) {
            //adapter.setMovies(it)
            binding.rvtodos.apply {
                this.adapter = AdapterTodos(it)
                this.layoutManager = LinearLayoutManager(requireContext())
            }

        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error de llamada de api", Toast.LENGTH_SHORT).show()
        }



        viewModel.getPhotos()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            GalleryFragment()
    }
}