package fr.saintaspais.l3.programme;

import java.math.BigDecimal;
import java.time.LocalDate;

import fr.saintaspais.l3.model.Banque;
import fr.saintaspais.l3.model.Compte;
import fr.saintaspais.l3.model.CompteCourant;
import fr.saintaspais.l3.model.LivretA;
import fr.saintaspais.l3.model.Titulaire;

/**
 * Programme de test pour les classes Banque, Compte etc ...
 */
public class ProgrammeBanque {

	private static final String ERREUR_OPERATION = "Erreur opération : ";

	public static void main(String[] args) {

		// On créé une banque
		Banque banque = new Banque("MyBank", "1 rue Test 75001 Paris");

		// On créé un titulaire de compte
		Titulaire titulaire = new Titulaire("DOE", "John", 
				LocalDate.of(1970, 10, 25), 
				"+331234567891",
				"john.doe@email.com");

		// On créé deux types de comptes et on leur associe le titulaire ci-dessus
		Compte compteCourant = new CompteCourant(1023L, banque, titulaire, BigDecimal.valueOf(3250.35), 500.0);
		Compte livret = new LivretA(1065L, banque, titulaire, BigDecimal.valueOf(15200.00), 1.2);

		// On associe la banque avec les deux comptes
		banque.ajouterCompte(compteCourant);
		banque.ajouterCompte(livret);

		// On demande à tout afficher
		banque.afficherComptes();

		System.out.println();
		System.out.println("--------------- Opérations --------------------");
		
		// On essaie de retirer la totalité du solde du compte courant
		// (la méthode peut remonter une exception)
		try {
			System.out.println("Retrait sur compte courant : ");
			compteCourant.retirer(BigDecimal.valueOf(3250.35));
		} catch (IllegalArgumentException e) {
			System.err.println(ERREUR_OPERATION + e.getMessage());
		}
		
		System.out.println();
		
		// On essaie de retirer depuis le livret A
		try {
			System.out.println("Retrait sur un livret A : ");
			livret.retirer(BigDecimal.valueOf(100.0));
		} catch (IllegalArgumentException e) {
			System.err.println(ERREUR_OPERATION + e.getMessage());
		}
		
		System.out.println();
		
		// On verse un montant sur le compte courant
		// (la méthode peut remonter une exception en cas de montant null)
		try {
			System.out.println("Versement sur compte courant : ");
			compteCourant.verser(BigDecimal.valueOf(1000.0));
		} catch (IllegalArgumentException e) {
			System.err.println(ERREUR_OPERATION + e.getMessage());
		}
		
		System.out.println();
		
		// On verse un montant sur le livret A
		// (la méthode peut remonter une exception en cas de montant null)
		try {
			System.out.println("Versement sur Livret A : ");
			livret.verser(BigDecimal.valueOf(3500.0));
		} catch (IllegalArgumentException e) {
			System.err.println(ERREUR_OPERATION + e.getMessage());
		}
		
	}
}
