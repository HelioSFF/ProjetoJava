package model;

import java.util.ArrayList;

public class Medico {
    private String nome;
    private int crm;

    public String getNome() {
        return nome;
    }

    public int getCrm() {
        return crm;
    }

    public Medico(String nome, int crm) {
        this.nome = nome;
        this.crm = crm;
    }

    public static void main(String[] args) {
        ArrayList<Medico> medicos = new ArrayList<>();

        medicos.add(new Medico("Junior", 123));
        medicos.add(new Medico("Nisston", 234));
        medicos.add(new Medico("Fernanda", 345));
        medicos.add(new Medico("Glaucio", 456));
        medicos.add(new Medico("Paulemir", 567));

        for (Medico m : medicos) {
            System.out.println("Nome: " + m.getNome() + " | CRM: " + m.getCrm());
        }
    }



    @Override
    public String toString() {
        return nome + "," + crm;
    }
}
