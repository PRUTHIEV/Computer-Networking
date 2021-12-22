package SMTP;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpImplementation {
    private static Session createSession(String from, String password, Properties prop) {
        Session sess = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }

        });
        return sess;
    }

    private static Message createMsg(Session session, String from, String to) {
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("Java Program -SMTP");
            msg.setText("This is basic program ");
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void sendMail(String sendTo) {
        try{
            System.out.println("Creating the mail properties and session");
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            String from = "aspbrothers2001@gmail.com";
            String password = "Aspbrothers@20012004";
            Session session = createSession(from, password, properties);
            Message msg = createMsg(session, from, sendTo);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            sendMail("aspruthiev@gmail.com");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
