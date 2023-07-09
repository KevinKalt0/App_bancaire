package fr.saintaspais.l3.model;

import java.math.BigDecimal;

public class CompteCourant extends Compte {

	// Un montant limite pour le retrait
	private double limiteRetrait;

	public CompteCourant(Long identifiant, Banque banque, Titulaire titulaire, BigDecimal solde, double limiteRetrait) {
		super(identifiant, banque, titulaire, solde);

		// un compte courant possède une limite de retrait par jour
		this.limiteRetrait = limiteRetrait;
	}

	@Override
	public boolean retraitPossible() {
		// On peut retirer directement depuis un Compte courant
		return Boolean.TRUE;
	}

	public void retirer(BigDecimal montant) {

		// Si ce type de compte ne permet pas de retirer
		if (!retraitPossible()) {
			String message = String.format(
					"Ce type de compte (%s) ne permet pas de retirer de l'argent", 
					CompteCourant.class.getSimpleName() );
			throw new IllegalArgumentException(message);
		}

		// Si le solde est négatif => erreur
		if (super.solde.compareTo(BigDecimal.valueOf(0)) < 0) {
			throw new IllegalArgumentException("Votre solde est inférieur ou égal à 0, retrait impossible");
		}

		// Si le montant à retirer est supérieur au solde
		if (super.solde.compareTo(montant) < 0) {
			throw new IllegalArgumentException("Le montant souhaité est supérieur au solde, retrait impossible");
		}

		// Si on arrive ici c'est que tout va bien

		// On mémorise l'ancien solde temporairement pour affichage
		BigDecimal ancienSolde = super.solde;

		// on retire la somme
		super.solde = solde.subtract(montant);

		// on informe du nouveau solde
		String message = String.format(
				"Retrait de : %s - Ancien solde : %s - Nouveau solde : %s", 
				montant.toString(), 
				ancienSolde.toString(), 
				super.solde.toString());
		
		System.out.println(message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompteCourant [limiteRetrait=");
		builder.append(limiteRetrait);
		builder.append(", retrait possible : ");

		// On utilise l'opérateur ternaire pour éviter
		// d'afficher true false mais oui ou non
		String retraitPossible = retraitPossible() ? "oui" : "non";
		builder.append(retraitPossible);

		builder.append(", infos compte : ");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
