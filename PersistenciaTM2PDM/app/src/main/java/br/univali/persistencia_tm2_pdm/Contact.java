package br.univali.persistencia_tm2_pdm;

import java.util.List;

public class Contact {
    private String fullName;
    private List<Phone> phoneList;

    public Contact(String fullName, List<Phone> phoneList) {
        this.fullName = fullName;
        this.phoneList = phoneList;
    }

    // Getters e Setters
}
