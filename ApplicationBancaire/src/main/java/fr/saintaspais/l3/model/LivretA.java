package fr.saintaspais.l3.model;

import java.math.BigDecimal;

public class LivretA extends Compte {

	// taux de rémunération du livret
	private double tauxRemuneration;

	public LivretA(Long identifiant, Banque banque, Titulaire titulaire, BigDecimal solde, double tauxRemuneration) {
		
		super(identifiant, banque, titulaire, solde);
		
		// un livret A est rémunéré contrairement au autres types de comptes
		this.tauxRemuneration = tauxRemuneration;
	}

	@Override
	public boolean retraitPossible() {
		// On suppose qu'on ne peut pas 
		// retirer directement depuis un LivretA 
		return Boolean.FALSE;
	}
	
	@Override
	public void retirer(BigDecimal montant) {
		String message = String.format(
				"Ce type de compte (%s) ne permet pas de retirer de l'argent", 
				LivretA.class.getSimpleName() );
		throw new IllegalArgumentException(message);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LivretA [tauxRemuneration en %=");
		builder.append(tauxRemuneration);
		builder.append(", retrait possible : ");
		
		// On utilise l'opérateur ternaire pour éviter
		// d'afficher true false mais oui ou non
		String retraitPossible = retraitPossible() ? "oui" : "non";
		builder.append( retraitPossible);
		
		builder.append(", infos compte : ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
}
