package anhnpp.model;

import static anhnpp.model.RandomCode.random_Code;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nguye
 */
public class MailService {

    public static void sendMail(String email, String subject, String content) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mailMessage.setSubject(subject);
        String emailBody = content;
        mailMessage.setContent(emailBody, "text/html");

        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", "fpt.car.rental.co@gmail.com", "090145FPT");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    public static void sendVerificationCode(String email, String randomCode) throws MessagingException {
        String subject = "Account verification code of FPT Car Rental";
        String content = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>JSP Page</title></head>\n"
                + "    <body>\n"
                + "        <div style=\"text-align: center; font-size: 100px; font-family: sans-serif;\">Verification Code</div>\n"
                + "        <div style=\"text-align: center; font-size: 70px; font-family: sans-serif;\">" + randomCode + "</div>\n"
                + "        <img src=\"https://fpt.com.vn/Content/home/images/icon/logo-ft.png\" alt=\"\" height=\"70px\"> <span class=\"\" style=\"font-size: 50px; font-family: sans-serif;\">Car Rental</span>\n"
                + "    </body>\n"
                + "</html>\n"
                + "";
        sendMail(email, subject, content);
    }

    public static void main(String args[]) throws MessagingException {
        String email = "nokia701cuaanh@gmail.com";
        String code = random_Code(8);
        sendVerificationCode(email, code);
    }
}
