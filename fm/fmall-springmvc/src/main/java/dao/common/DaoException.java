package dao.common;

public class DaoException extends RuntimeException{
	public DaoException(){
		super();
	}
	public DaoException(String message){
		super(message);
	}
}
