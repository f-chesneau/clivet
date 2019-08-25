package fr.eni.clinique.bll;

import fr.eni.clinique.dal.DALException;


public class AppliTestBLL {

	public static void main(String[] args) throws DALException {
		
		try {
			String utilisateurConnu = LoginMger.getInstance().verifier("admin", "admin");
			if (utilisateurConnu!=null) 
			{
				System.out.println("validation nom et mdp ok");
			}
			else
				{
					System.out.println("Sort de ce corps, HackerDeLaMeule !!!");
				}
		} catch (BLLException e) {
			
			e.printStackTrace();
		}
		

	}

}
