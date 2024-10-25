package com.works.odev8.ui.PersonDetail

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.works.odev8.MainActivity
import com.works.odev8.R
import com.works.odev8.common.GlideImage
import com.works.odev8.databinding.FragmentPersonDetailBinding
import com.works.odev8.ui.Homepage.HomepageFragmentDirections

class PersonDetailFragment : Fragment() {

    private val argument: PersonDetailFragmentArgs by navArgs()

    private var _binding: FragmentPersonDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PersonDetailViewModel::class.java)
        _binding = FragmentPersonDetailBinding.inflate(inflater, container, false)
        val root = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personId = argument.id
        //Log.d("argumentId", argument.id.toString())
        viewModel.getSelectedPerson(personId)

        val fullNameTextView = binding.fullnameTextView
        val phoneTextView = binding.phoneTextView
        val groupTextView = binding.groupTextView
        val imgPersonDetail = binding.imgPersonDetail
        val btnDelete = binding.btnDelete
        val btnUpdate = binding.btnUpdate
        val categoriaTextView = binding.categoriaTextView


        viewModel.person.observe(viewLifecycleOwner) {

            val fullname = it.name
            fullNameTextView.text = fullname
            phoneTextView.text = "Phone number: " + it.phone
            groupTextView.text = "Group: " + it.groupName
            categoriaTextView.text = "Categoria: " + it.categoria


            val imageLink = when (it.groupName) {
                "Family" -> GlideImage.familyImg
                "School" -> GlideImage.schoolImg
                "Business" -> GlideImage.businessImg
                "Gamer" -> GlideImage.gamerImg
                "Friends" -> GlideImage.friendsImg
                else -> GlideImage.defaultImg //
            }
            Glide.with(this).load(imageLink).into(imgPersonDetail)

            val person = it

            btnDelete.setOnClickListener {
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirmation")
                    .setMessage("Are you sure you want to delete this person?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deletePerson(person)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton("No", null)
                    .show()

            }

            fun navigateToPersonUpdateFragment(id: Int) {
                val action =
                    PersonDetailFragmentDirections.actionPersonDetailFragmentToPersonUpdateFragment(
                        id
                    )
                findNavController().navigate(action)
            }

            btnUpdate.setOnClickListener {
                if (person.id != null) {
                    navigateToPersonUpdateFragment(person.id)
                }
            }
        }
    }
}