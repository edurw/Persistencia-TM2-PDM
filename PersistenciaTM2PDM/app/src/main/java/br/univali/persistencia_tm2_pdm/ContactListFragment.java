package br.univali.persistencia_tm2_pdm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class ContactListFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewContacts);
        fabAddContact = view.findViewById(R.id.fabAddContact);

        // Configuração do RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Exemplo de Adapter
        recyclerView.setAdapter(new ContactAdapter(new ArrayList<Contact>()));

        // Adiciona contato ao clicar no FAB
        fabAddContact.setOnClickListener(v -> {
            // Substitui pelo fragmento de adição de contato
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddEditContactFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
