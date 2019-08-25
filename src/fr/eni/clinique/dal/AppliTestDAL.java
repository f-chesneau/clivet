package fr.eni.clinique.dal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import fr.eni.clinique.bo.*;
import fr.eni.clinique.dal.jdbc.JdbcConnect;


public class AppliTestDAL {

	public static void main(String[] args) throws DALException {

		
		//DÃ©claration et instanciation de la DAO
		DAO clientDAO = (DAO) ClientDaoJdbcImpl.getInstance();
		DAO agendaDAO = (DAO) AgendaDaoJdbcImpl.getInstance();

		JdbcConnect.getInstance();
		
		//Instanciation du jeu d'essai 
		
		/*
		 * this.nomClient = nomClient;
			this.prenomClient = prenomClient;
			this.adresse1 = adresse1;
			this.adresse2 = adresse2;
			this.codePostal = codePostal;
			this.ville = ville;
			this.numTel = numTel;
			this.assurance = assurance;
			this.email = email;
			this.remarque = remarque;
			this.archive = archive;
		 */
/*		Client c1 = new Client("Paul","Durand","rue des vaches","","53100","Bomchamp de Bonchoir","0243045827","058e7d18","pDurand@pdurant.com","néant",false);
		Client c2 = new Client("Paul","Dupont","Imp. de la gariggue","","07810","Crussol","0493018823","072h7x93","pDurand@pdupont.com","",false);
		Client c3 = new Client("Robert","Bidochon","Imp. des français moyens","","91300","L'essonne-Gelès","0123456789","014f9x92","rBidochon@bd.com","",false);

		System.out.println("Ajout des clients en BDD... ");

			clientDAO.insert(c1);			
			clientDAO.insert(c2);			
			clientDAO.insert(c3);


			//Sélection de l'article par id
			System.out.println(clientDAO.selectById(3).toString());
			
			//Sélection de tous les clients
			List<Client> clients = clientDAO.selectAll();
			afficherClients(clients);
		
			//Modification d'un client
			System.out.println("\nModification d'un article  : " );
			System.out.println("Article avant modification : "  + c1.toString());
			c1.setCodeClient(1);
			c1.setPrenomClient("Paul");
			c1.setNomClient("Amar");
			c1.setAdresse1("rekhov ben yehuda");
			clientDAO.update(c1);
			System.out.println("Article aprÃ¨s modification  : " + c1.toString() );
				
			
		//Suppression d'un article
			System.out.println("\nSuppression de l'article  : " + c1.toString());
			clientDAO.delete(1);
			System.out.println("Liste des articles après suppression : "  );
			afficherClients(clientDAO.selectAll());
			System.out.println("---------------------------------------------------------------");
*/
		
		Agenda a1= new Agenda (1,LocalDate.parse("2015-07-12"),LocalTime.parse("10:15:30"),3);
		Agenda a2= new Agenda (1,LocalDate.parse("2015-08-24"),LocalTime.parse("14:30:00"),4);
		
		agendaDAO.insert(a1);
		agendaDAO.insert(a2);
	}

	
	private static void afficherClients(List<Client> clients){
		/*StringBuffer sb = new StringBuffer();
		for(Article art: articles){
			sb.append(art.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());*/
		
		for (Client cli: clients)
		  {
			  System.out.println(cli.toString()); 
		  }
	}
}
