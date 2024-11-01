package com.works.odev8.ui.AddPerson

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.works.odev8.MainActivity
import com.works.odev8.R
import com.works.odev8.databinding.FragmentAddPersonBinding
import com.works.odev8.models.Person


class AddPersonFragment : Fragment() {

    private lateinit var telefoneList: LinearLayout
    private lateinit var buttonAddTelefone: Button
    private var _binding: FragmentAddPersonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonBinding.inflate(inflater, container, false)

        val nameEditText = binding.nameEditText
        val groupSpinner = binding.groupSpinner
        val categoriaSpinner = binding.categoriaGrupo
        val phoneEditText = binding.primaryTelefone
        val btnSave = binding.saveButton

        telefoneList = binding.telefoneList
        buttonAddTelefone = binding.btnAddTelefone

        buttonAddTelefone.setOnClickListener {
            addTelefoneField()
        }

        btnSave.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val group = groupSpinner.selectedItem.toString()
            val categoria = categoriaSpinner.selectedItem.toString()

            val person = Person(null, group, name, categoria, phone)
            fun SaveData() {

                var thread: Thread?
                thread = Thread {
                    val db = MainActivity.db
                    db.personDao().addPerson(person)
                }
                thread.start()
            }
            AlertDialog.Builder(requireContext())
                .setTitle("Confirmar")
                .setMessage("Você deseja adicionar esse contato?")
                .setPositiveButton("Sim") { _, _ ->
                    SaveData()
                    findNavController().popBackStack()
                    Snackbar.make(
                        requireView(),
                        "Essa pessoa foi adicionada com sucesso",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }
                .setNegativeButton("Não", null)
                .show()
        }
        return binding.root
    }

    private fun addTelefoneField() {
        val telefoneFieldView = LayoutInflater.from(requireContext()).inflate(R.layout.row_telephone, telefoneList, false)
        val removeButton = telefoneFieldView.findViewById<Button>(R.id.btnDelTelephone)

        removeButton.setOnClickListener {
            telefoneList.removeView(telefoneFieldView)
        }

        telefoneList.addView(telefoneFieldView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
