package br.univali.persistencia_tm2_pdm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddEditContactFragment extends Fragment {

    private EditText editTextName;
    private RecyclerView recyclerViewPhones;
    private PhoneAdapter phoneAdapter;
    private Button btnAddPhone, btnSaveContact, btnDeleteContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_contact, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        recyclerViewPhones = view.findViewById(R.id.recyclerViewPhones);
        btnAddPhone = view.findViewById(R.id.btnAddPhone);
        btnSaveContact = view.findViewById(R.id.btnSaveContact);
        btnDeleteContact = view.findViewById(R.id.btnDeleteContact);

        // Configuração do RecyclerView para os telefones
        phoneAdapter = new PhoneAdapter(new ArrayList<Phone>());
        recyclerViewPhones.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPhones.setAdapter(phoneAdapter);

        // Adiciona novo telefone
        btnAddPhone.setOnClickListener(v -> phoneAdapter.addPhone());

        // Lógica de salvar e excluir contato
        btnSaveContact.setOnClickListener(v -> saveContact());
        btnDeleteContact.setOnClickListener(v -> deleteContact());

        return view;
    }

    private void saveContact() {
        // Validação dos campos
    }

    private void deleteContact() {
        // Excluir o contato
    }
}
