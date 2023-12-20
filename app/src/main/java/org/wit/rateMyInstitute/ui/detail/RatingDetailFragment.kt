package org.wit.rateMyInstitute.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.wit.rateMyInstitute.databinding.FragmentRatingDetailBinding
import org.wit.rateMyInstitute.ui.auth.LoggedInViewModel
import org.wit.rateMyInstitute.ui.report.ReportViewModel
import timber.log.Timber


class RatingDetailFragment : Fragment() {

    private lateinit var detailViewModel: RatingDetailViewModel
    private val args by navArgs<RatingDetailFragmentArgs>()
    private var _fragBinding: FragmentRatingDetailBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val reportViewModel : ReportViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentRatingDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(RatingDetailViewModel::class.java)
        detailViewModel.observableRating.observe(viewLifecycleOwner, Observer { render() })

        fragBinding.editRatingButton.setOnClickListener {
            Timber.i("firebase user id == ${loggedInViewModel.liveFirebaseUser.value?.uid!!}")
            Timber.i("rating id == ${args.ratingid}")
            Timber.i("rating == ${fragBinding.ratingvm?.observableRating!!.value!!}")
            detailViewModel.updateRating(loggedInViewModel.liveFirebaseUser.value?.uid!!,
                args.ratingid, fragBinding.ratingvm?.observableRating!!.value!!)
            findNavController().navigateUp()
        }

        fragBinding.deleteRatingButton.setOnClickListener {
            reportViewModel.delete(loggedInViewModel.liveFirebaseUser.value?.email!!,
                detailViewModel.observableRating.value?.uid!!.toString())
            findNavController().navigateUp()
        }

        return root
    }

    private fun render() {
        //fragBinding.editMessage.setText("A Message")
        //fragBinding.editUpvotes.setText("0")
        fragBinding.ratingvm = detailViewModel
        Timber.i("Retrofit fragBinding.ratingvm == $fragBinding.ratingvm")
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.getRating(loggedInViewModel.liveFirebaseUser.value?.email!!,
            args.ratingid
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}