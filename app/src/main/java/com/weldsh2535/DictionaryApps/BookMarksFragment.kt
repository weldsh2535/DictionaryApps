package com.weldsh2535.DictionaryApps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.weldsh2535.DictionaryApps.databinding.BookmarkFragmentBinding

class BookMarksFragment : Fragment() {

    private lateinit var binding: BookmarkFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookmarkFragmentBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvBookMarks.text = "Book marks "
    }
}