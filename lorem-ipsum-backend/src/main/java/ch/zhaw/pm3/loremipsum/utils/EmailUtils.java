package ch.zhaw.pm3.loremipsum.utils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Utils for sending e-mails
 */
public class EmailUtils {
    private static Properties properties;
    private static String username;
    private static String password;

    /**
     * Send an e-mail and print the e-mail to the console in case the config is missing
     *
     * @param to      recipient
     * @param subject subject of the e-mail
     * @param body    content of the e-mail
     */
    public static void sendEmail(String to, String subject, String body) {
        if (properties != null || loadProperties()) {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject(subject);
                message.setText(body);
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.printf("Unable to send e-mail with subject %s to %s:%n%s%n", subject, to, body);
        }
    }

    /**
     * Try to load the configurations for sending the mail
     *
     * @return true if the configurations are loaded, false otherwise
     */
    private static boolean loadProperties() {
        URL propertiesUrl = EmailUtils.class.getResource("/email/config.properties");
        if (propertiesUrl != null) {
            properties = new Properties();
            try {
                properties.load(propertiesUrl.openStream());
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                properties.remove("username");
                properties.remove("password");
                return true;
            } catch (IOException e) {
                properties = null;
                e.printStackTrace();
            }
        }
        return false;
    }
}
