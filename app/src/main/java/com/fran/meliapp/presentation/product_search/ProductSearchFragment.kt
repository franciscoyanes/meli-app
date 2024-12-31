package com.fran.meliapp.presentation.product_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fran.meliapp.R
import com.fran.meliapp.databinding.FragmentProductSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductSearchFragment : Fragment() {

    private var _binding: FragmentProductSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Android Views has a bug that focuses Edit Text elements automatically.
        binding.productSearchEditText.clearFocus()
//        viewModel.setSearchActionEventListener { setSearchActionListener() }
//        viewModel.searchQuery.observe(viewLifecycleOwner) {
//
//        }
        setSearchActionEventListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Trigger for when the user invokes the Search Action for the Edit Text view.
    private fun setSearchActionEventListener() {
        binding.apply {
            productSearchEditText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (productSearchEditText.text.isNotEmpty()) {
                        val bundle = Bundle().apply {
                            putString("queryString", productSearchEditText.text.toString())
                        }
                        findNavController().navigate(
                            R.id.action_productSearchFragment_to_productListingFragment,
                            bundle
                        )
                    } else {
                        Toast.makeText(requireContext(),
                            "CAMPO DE BUSQUEDA VACIO", Toast.LENGTH_LONG).show()
                    }
                }
                true
            }
        }
    }
}
