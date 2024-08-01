import javax.swing.JOptionPane;

public class UserDefinedException extends Exception {
	public UserDefinedException (String errorMessage) {
		super(errorMessage);
	}
}
