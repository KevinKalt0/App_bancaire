package fr.saintaspais.l3.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Compte {

	protected Long identifiant;
	protected Banque banque;
	protected Titulaire titulaire;
	protected BigDecimal solde;
	
	protected Compte(Long identifiant, Banque banque, Titulaire titulaire, BigDecimal solde) {
		this.identifiant = identifiant;
		this.banque = banque;
		this.titulaire = titulaire;
		this.solde = solde;
	}

	/*
	 * Méthodes commune aux classes filles, et qui doit
	 * être implémentée par celles-ci (CompteCourant et LivretA) 
	 */
	public abstract boolean retraitPossible();
	
	public abstract void retirer(BigDecimal montant) throws IllegalArgumentException;
	
	/*
	 * Méthode commune aux classes filles avec implémentation générique
	 */
	public void verser(BigDecimal montant) {
		if(montant == null) {
			throw new IllegalArgumentException("Vous ne pouvez pas verser un montant nul");
		}
		
		// On mémorise l'ancien solde temporairement pour affichage
		BigDecimal ancienSolde = solde;
		
		// On augmente le solde 
		solde = solde.add(montant);
		
		// on informe du nouveau solde
		String message = String.format(
				"Versement de : %s - Ancien solde : %s - Nouveau solde : %s", 
				montant.toString(),
				ancienSolde.toString(),
				solde.toString());
		
		System.out.println(message);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compte [identifiant=");
		builder.append(identifiant);
		builder.append(", banque=");
		builder.append(banque.getNom());
		builder.append(", titulaire=");
		builder.append(titulaire.nomComplet());
		builder.append(", solde=");
		builder.append(solde);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(banque, identifiant);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(banque, other.banque) && Objects.equals(identifiant, other.identifiant);
	}
	
}
