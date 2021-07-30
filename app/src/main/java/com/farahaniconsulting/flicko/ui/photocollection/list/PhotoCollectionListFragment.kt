package com.farahaniconsulting.flicko.ui.photocollection.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.farahanconsulting.flicko.databinding.FragmentPhotoCollectionListBinding
import com.farahaniconsulting.flicko.di.modules.ViewModelFactory
import com.farahaniconsulting.flicko.ui.photocollection.PhotoCollectionContract
import com.farahaniconsulting.flicko.ui.photocollection.PhotoCollectionViewModel
import com.farahaniconsulting.flicko.ui.photocollection.details.PhotoDetailsDialogFragment
import com.farahaniconsulting.flicko.ui.photocollection.list.adapter.PhotoCollectionListAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.lang.RuntimeException
import javax.inject.Inject

class PhotoCollectionListFragment : Fragment(),
    HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var viewModel: PhotoCollectionViewModel

    private lateinit var binding: FragmentPhotoCollectionListBinding

    private lateinit var photoCollectionListAdapter: PhotoCollectionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, vmFactory).get(PhotoCollectionViewModel::class.java)
    }

    private fun triggerSearch(searchItem: String) {
        viewModel.searchItem = searchItem
        viewModel.fetchPhotoCollection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoCollectionListBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.apply {
            photoCollectionListAdapter = PhotoCollectionListAdapter { photoItem ->
                PhotoDetailsDialogFragment.newInstance(photoItem = photoItem)
                    .show(parentFragmentManager, PhotoDetailsDialogFragment.TAG)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            uiViewModel = viewModel
        }
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            Timber.d("photo collection: $it")
            it?.let { render(it) }
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun render(viewState: PhotoCollectionContract.ViewState) {
        when(viewState.isLoading) {
            true -> binding.pageLoadingIndicator.visibility = View.VISIBLE
            false -> binding.pageLoadingIndicator.visibility = View.GONE
        }
        if (viewState.errorState != null) {
            showError(viewState.errorState.message.getText(requireContext()).toString())
            showListView(false)
        }

        if (viewState.activityData.isNotEmpty()) {
            showListView(true)
            photoCollectionListAdapter.submitList(viewState.activityData)
        }
    }

    private fun showListView(show: Boolean) {
        binding.photoCollectionListRv.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(errorMessage: String) =
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()

    interface OnFragmentInteractionListener

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    companion object {
    }
}