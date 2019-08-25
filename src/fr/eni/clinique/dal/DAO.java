package fr.eni.clinique.dal;

import java.util.List;


public interface DAO <T>
{
	//Create
	public void insert(T data) throws DALException;
			
	//Read by id
	public T selectById(int id) throws DALException;
				
	//Read all
	public List<T> selectAll() throws DALException;
		
	//Update
	public void update(T data) throws DALException;
	
	//Delete
	public void delete(int id) throws DALException;
	
}
