package login;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	// yandex
	private final static String FROM = "YuutubeMalaya@yandex.com";
	private final static String PASSWORD = "Admin.";
	private final static String HOST = "smtp.yandex.com";
	private final static String PORT = "465";

	public static void useYandex(String to, String title, String body) throws Exception {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", HOST); // smtp host
		prop.setProperty("mail.smtp.port", PORT);//
		prop.put("mail.smtp.auth", "true"); // enable authentication
		prop.put("mail.smtp.Starttles.enable", "true"); // enable Starttles

		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		prop.put("mail.smtp.user", FROM);
		prop.put("mail.smtp.password", PASSWORD);

		Session session = Session.getInstance(prop);
		   //session.setDebug(true); //remove the bars when debugging

		MimeMessage msg = new MimeMessage(session);
		msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
		msg.setSubject(title, "UTF-8");
		msg.setText(body, "UTF-8");

		try {
			msg.setFrom(new InternetAddress(FROM));
			msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
			//msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(FROM));

			msg.setSubject(title);
			msg.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, FROM, PASSWORD);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}

}
