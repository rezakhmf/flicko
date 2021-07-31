package com.farahaniconsulting.flicko.ui.photocollection.details

import android.app.Dialog
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.farahanconsulting.flicko.databinding.FragmentPhotoDetailsBinding
import com.farahaniconsulting.flicko.dto.PhotoItemDTO
import timber.log.Timber


class PhotoDetailsDialogFragment(val photoItem: PhotoItemDTO)
    : DialogFragment() {

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
        binding.imageWidthValueTextView.text = binding.headerImageView.maxWidth.toString()
        binding.descriptionValueTextView.text = Html.fromHtml(photoItem.description, Html.FROM_HTML_MODE_COMPACT)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
             toolbar.setNavigationOnClickListener { dismiss() }
        }

        view?.viewTreeObserver?.addOnWindowFocusChangeListener { hasFocus -> /*do your stuff here*/
            binding.imageWidthValueTextView.text = binding.headerImageView.maxWidth.toString()
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