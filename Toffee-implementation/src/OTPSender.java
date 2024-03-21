import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

class OTPSender {
    /**
     * function that sends an OTP to the current customer's E-Mail
     * @param email the email address of the customer
     * @param customer the customer that it's wanted to add this Randomly Generated OTP into its info
     */
    public static void sendOTP(String email, Customer customer) {
        // Generate a random 6-digit OTP
        Random rand = new Random();
        int otp = rand.nextInt(900000) + 100000; // range: 100000 - 999999
        customer.setOtp(otp);
        // Email properties
        String host = "smtp.gmail.com";
        String username = "dodo1003pro@gmail.com"; // emailak
        String password = "xkmvnlovuimvwvhj"; // passwordak
        int port = 587;

        // Sender's and recipient's email addresses
        String from = "dodo1003pro@gmail.com"; // emailak
        String to = email;

        // Email message
        String subject = "One-Time Password (OTP)";
        String body = "Your OTP is: " + otp;

        // Setup email session and properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Authenticate sender's email
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            // Send email message
            Transport.send(message);

            System.out.println("OTP sent to " + email);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}