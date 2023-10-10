package in.influjtech.springbootmongodb.exception;

public class TodoCollectionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TodoCollectionException(String message) {
		super(message);
	}
	
	public static String NotFoundException(String id) {
		return "Record with "+id+" not Found";
	}
	
	public static String TodoAlreadyExists(int mobileNumber) {
		return "Mobile Number already Exists";
	}
}
