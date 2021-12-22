package SMTP;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpClientPractice {
    private static final Scanner input = new Scanner(System.in);

    private static Session createSession(Properties prop, String from, String password) {
        Session sess = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });
        return sess;
    }

    private static Message createMessage(Session sess,Properties prop,String from,String to) throws Exception{
        Message msg = new MimeMessage(sess);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject("Welcome to Computer Networking Laboratory");
        msg.setText("This is an example of Simple Mail Transfer Protocol (SMTP)");
        return msg;

    }

    private static boolean sendMail(String to) {
        try{
            Properties prop = new Properties();
            System.out.println("Creating the properties for the mail ...");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.trust", "smtp.gmail.com");
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            String from = "aspbrothers2001@gmail.com";
            String password = "Aspbrothers@20012004";
            System.out.println("Creating a session for gmail account authentication ");
            Session sess = createSession(prop, from, password);
            System.out.println("Creating the Message to be sent ...");
            Message msg = createMessage(sess, prop, from, to);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("Enter the Receipent Email Address :");
        String to = input.nextLine();
        if (sendMail(to)) {
            System.out.println("The email is successfully sent ");
        }
        else {
            System.out.println("Error occured while sending !!!");
        }

    }
}
