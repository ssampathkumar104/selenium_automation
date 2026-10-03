package scripts;



	import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

	 

	public class Email_Outlook {
	    final static String fromEmail = "your-smtp-user@example.com";
	    final static String password = "your-email-password";
	    final static String toEmail = "recipient@example.com";
	    final static String cc = "";
//	    		"user1@example.com , user2@example.com , user3@example.com , user4@example.com , user5@example.com";


	 

	    public static void email_Implement() {
	        System.out.println("TLSEmail Start");
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "mail.infor.com"); // SMTP Host
	        props.put("mail.smtp.port", "25"); // TLS Port
	        props.put("mail.smtp.auth", "true"); //enable authentication
	        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	        
	                //create Authenticator object to pass in Session.getInstance argument
	        Authenticator auth = new Authenticator() {
	            //override the getPasswordAuthentication method
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };
	        Session session = Session.getInstance(props, auth);
	        
	        Email_Outlook.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");
	        
	    }

	 

	    /**
	     * Utility method to send simple HTML email
	     * 
	     * @param session
	     * @param toEmail
	     * @param subject
	     * @param body
	     */
	    public static void sendEmail(Session session, String toEmail, String subject, String body) {
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(fromEmail, "Test Automation"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
	            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
	            message.setSubject("Support2.0 QA-Regression Results");

	 

	            // Create the message body part
	            BodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText(
	                    "Hi Team, \n\nThis is an automated mail.\nReply on this mail is not monitored.\n\nPFA, \n\nRegards, \nTest Automation Team");
	            // Create a multipart message for attachment
	            Multipart multipart = new MimeMultipart();

	 

	            // Set text message part
	            multipart.addBodyPart(messageBodyPart);

	 

	            String filename = "Demo.html";
	            // for displaying image in the email body
	            messageBodyPart = new MimeBodyPart();
	            DataSource source = new FileDataSource(filename);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(filename);
	            multipart.addBodyPart(messageBodyPart);
	            // Set the multipart message to the email message
	            message.setContent(multipart);
	            Transport.send(message);

	 

	            System.out.println("It worked Sir..!!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Email_Outlook.email_Implement();

	}

}
