package org.wit.rateMyInstitute.ui.rate

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.wit.rateMyInstitute.R
import org.wit.rateMyInstitute.databinding.FragmentRatingBinding
import org.wit.rateMyInstitute.models.RatingModel
import org.wit.rateMyInstitute.ui.report.ReportViewModel
import org.wit.rateMyInstitute.ui.auth.LoggedInViewModel

class RatingFragment : Fragment() {


    private var _fragBinding: FragmentRatingBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val fragBinding get() = _fragBinding!!
    //lateinit var navController: NavController
    private lateinit var ratingViewModel: RatingViewModel

    private val loggedInViewModel : LoggedInViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _fragBinding = FragmentRatingBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_rating)
	    setupMenu()
        ratingViewModel = ViewModelProvider(this).get(RatingViewModel::class.java)
        ratingViewModel.observableStatus.observe(viewLifecycleOwner, Observer {
                status -> status?.let { render(status) }
        })

        setButtonListener(fragBinding)
        return root
    }

 private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_rate, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                return NavigationUI.onNavDestinationSelected(menuItem,
                    requireView().findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


//    companion object {
//        @JvmStatic
//        fun newInstance() =
//                DonateFragment().apply {
//                    arguments = Bundle().apply {}
//                }
//    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    //Uncomment this if you want to immediately return to Report
                    //findNavController().popBackStack()
                }
            }
            false -> Toast.makeText(context,getString(R.string.donationError),Toast.LENGTH_LONG).show()
        }
    }

    fun setButtonListener(layout: FragmentRatingBinding) {
        layout.ratingButton.setOnClickListener {
            var name = layout.instituteName.text.toString()
            var description = layout.instituteDescription.text.toString()
            var fee = layout.instituteFee.text.toString().toDouble()
            var rating = layout.instituteOverallRating.text.toString().toDouble()
            var gradRate = layout.instituteGradRate.text.toString().toInt()
            if (layout.instituteName.text.isEmpty() || layout.instituteDescription.text.isEmpty()
                || layout.instituteFee.text.isEmpty() || layout.instituteOverallRating.text.isEmpty()
                || layout.instituteGradRate.text.isEmpty())
                Toast.makeText(context,"Please enter all details!", Toast.LENGTH_LONG).show()
            else (ratingViewModel.addRating(RatingModel(name = name,description =
                description,fee = fee, overallRating = rating,gradRate = gradRate,
                email = loggedInViewModel.liveFirebaseUser.value?.email!!)))
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
        val reportViewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        reportViewModel.observableRatingsList.observe(viewLifecycleOwner, Observer {
        })
    }
}