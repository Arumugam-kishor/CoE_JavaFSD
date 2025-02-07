package libraryManagementSystem;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User Not Found, Try with another UserID");
	}
}
