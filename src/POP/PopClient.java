package POP;

import java.util.Arrays;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

public class PopClient {
    public static void main(String[] args) throws Exception {
        String username = "aspruthiev@gmail.com";
        String password = "Aspru@2001";
        Properties prop = new Properties();
        prop.put("mail.store.protocol", "pop3s");
        String host = "pop.gmail.com";
        Session sess = Session.getDefaultInstance(prop);
        Store st = sess.getStore();
        st.connect(host, username, password);
        Folder inbox = st.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
		//Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.USER),true));
		SearchTerm sender = new FromTerm(new InternetAddress("aspbrothers2001@gmail.com"));
		Message[] messages = inbox.search(sender);
        for (Message m : messages) {
            String from = ((InternetAddress)m.getFrom()[0]).getAddress();
            System.out.println("From : "+from);
            System.out.println("To :" + username);
			System.out.println("Subject :" + m.getSubject() + "\n");

        }
    }
}
