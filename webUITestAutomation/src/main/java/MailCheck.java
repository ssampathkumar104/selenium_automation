import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class MailCheck {

	/**
     * Main method to send an email with execution results.
     *
     * @param args Command-line arguments
     * @throws EmailException if an error occurs while sending the email
     */
	public static void main(String[] args) throws EmailException {
		MultiPartEmail email = new MultiPartEmail();

		// Set email configuration
		email.setHostName("smtp.gmail.com");
		System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//		
//		email.setSmtpPort(465);
//		email.setSSLOnConnect(true);

		email.setSmtpPort(587);
		email.setStartTLSEnabled(true);
		email.setStartTLSRequired(true);
		
		email.setAuthentication("your-smtp-user@example.com", "your-email-password"); ////
		email.setFrom("your-smtp-user@example.com");

		// Add recipient, subject, and message
		email.addTo("recipient@example.com");
		email.setSubject(" - Execution results");
		email.setMsg("Detailed Execution results \n");
		
		// Send the email
		email.send();

		System.out.println("Email sent with Report  html Attached");

	}

}
