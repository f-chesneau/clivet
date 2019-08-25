package fr.eni.clinique.bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agenda {
	private int codeVeto;
	private LocalDate dateRdv;
	private LocalTime heureRdv;
	private int codeAnimal;
	
	public Agenda(int codeVeto, LocalDate date, LocalTime heure, int codeAnimal) 
	{
		super();
		this.codeVeto = codeVeto;
		this.dateRdv = date;
		this.heureRdv = heure;
		this.codeAnimal = codeAnimal;
	}

/*	public Agenda(LocalDate date, LocalTime heure, int codeAnimal) 
	{
		super();
		this.dateRdv = date;
		this.heureRdv = heure;
		this.codeAnimal = codeAnimal;
	}*/

	public int getCodeVeto() {
		return codeVeto;
	}

	public LocalDate getDateRdv() {
		return dateRdv;
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}

	public void setDateRdv(LocalDate dateRdv) {
		this.dateRdv = dateRdv;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public LocalTime getHeureRdv ()
		{
			return heureRdv;
		}

	public void setHeureRdv (LocalTime heureRdv)
		{
			this.heureRdv = heureRdv;
		}

	@Override
	public String toString ()
		{
			return "Agenda [codeVeto=" + codeVeto + ", dateRdv=" + dateRdv + ", heureRdv=" + heureRdv + ", codeAnimal="
					+ codeAnimal + "]";
		}

	

}
