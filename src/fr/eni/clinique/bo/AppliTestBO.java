package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class AppliTestBO
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List <Personnel> personnels = new ArrayList <Personnel>();
		List <Client> clients= new ArrayList <Client>();
		List <Animal> animaux= new ArrayList <Animal>();
		
		
		try
		{
			// ********************************
			// tester la gestion des personnels
			// ********************************
			Personnel p1= new Personnel("Bosapin Edmond", "toto", "vet", false);
			System.out.println(p1.toString());
			System.out.println("---------------------------------------------------------------");
			Personnel p2 = new Personnel("Malalanich M�lanie", "toto", "sec", false);
			System.out.println(p2.toString());
			System.out.println("---------------------------------------------------------------");
			Personnel p3 = new Personnel("admin", "admin", "adm", false);
			System.out.println(p3.toString());
			System.out.println("---------------------------------------------------------------");

			// Ajout des personnels � la liste.
			personnels.add(p1);
			personnels.add(p2);
			personnels.add(p3);

			System.out.println("\nREM : Affichage des personnels");
			// on affiche la liste des personnels
			for (Personnel p : personnels)
			{
				System.out.println(p.toString());
				System.out.println("---------------------------------------------------------------");
			}
			
			Client c1= new Client("Toupris", "Emma", "123, rue des fleurs", "3�me sous-sol, � droite apr�s le carton de P�trus", "53440",
					"Commer", "1864643684", "MMA championship", "emma.toupris@finance.gouv.fr", "v�nale", false);
			System.out.println(c1.toString());
			System.out.println("---------------------------------------------------------------");
			Client c2= new Client("Dejeu", "Odette", "3, rue du Casino", "pr�s des machines � sous", "29120",
					"B�nodet", "0298546743", "Assu-rance du bueurre p�rim�", "vieillebique29@plustropmeetic.com", "rombi�re", false);
			System.out.println(c2.toString());
			System.out.println("---------------------------------------------------------------");

			//Ajout des clients � la liste
			clients.add(c1);
			clients.add(c2);
			
			System.out.println("\nREM : Affichage des clients");
			// on affiche la liste des personnels
			for (Client c : clients)
			{
				System.out.println(c.toString());
			System.out.println("---------------------------------------------------------------");
			}
			
			Animal a1= new Animal("kiki", Animal.Sexe.M, "blanc cr�me", "Serpill�re � pattes", "pincher", 0,
					"La t�te de Jhonny sur la fesse droite", "st�rilisation", false);
			System.out.println(a1.toString());
			System.out.println("---------------------------------------------------------------");
			Animal a2= new Animal("Brenda", Animal.Sexe.F, "blanc", "enfant", "p�tasse", 2,
					"que ceux des yaourts", "allergie aux gar�ons", false);
			System.out.println(a2.toString());
			System.out.println("---------------------------------------------------------------");
			
			
			animaux.add(a1);
			animaux.add(a2);
			
			System.out.println("\nREM : Affichage des clients");
			// on affiche la liste des personnels
			for (Animal a : animaux)
			{
				System.out.println(a.toString());
			System.out.println("---------------------------------------------------------------");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		
		
	}

	
}
