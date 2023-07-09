package fr.saintaspais.l3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Banque {

	private String nom;
	private String adresse;
	
	private List<Compte> comptes = new ArrayList<>();
	
	public Banque(String nom, String adresse) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public List<Compte> getComptes() {
		// umodifiableList permet de ne pas modifier 
		// la liste en dehors de l'objet Banque
		// c'est une copie défensive
		return Collections.unmodifiableList(comptes);
	}
	
	public void ajouterCompte(Compte compte) {
		if( !comptes.contains(compte) ) {
			this.comptes.add(compte);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(adresse, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banque other = (Banque) obj;
		return Objects.equals(adresse, other.adresse) && Objects.equals(nom, other.nom);
	}

	public void afficherComptes() {
		System.out.println("Liste des comptes associés à la banque");
		System.out.println("********** Début liste ***********************");
		System.out.println();
		for(Compte c : comptes) {
			System.out.println(c);
			System.out.println();
		}
		System.out.println("********** Fin liste ***********************");
	}
	
}
