package com.farahaniconsulting.flicko.ui.photocollection.details

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.farahanconsulting.flicko.databinding.FragmentPhotoDetailsBinding
import com.farahaniconsulting.flicko.dto.PhotoItemDTO


class PhotoDetailsDialogFragment(val photoItem: PhotoItemDTO): DialogFragment() {

    private var _binding: FragmentPhotoDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false)
        binding.photoItem = photoItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
             toolbar.setNavigationOnClickListener { dismiss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "PhotoDetailsDialogFragment"

        fun newInstance(
            photoItem: PhotoItemDTO
        ): PhotoDetailsDialogFragment {
            return PhotoDetailsDialogFragment(photoItem)
        }
    }
}