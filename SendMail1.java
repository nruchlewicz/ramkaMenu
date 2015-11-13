package xyz;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail1 {

 private static final String HOST = "smtp.gmail.com";
 private static final int PORT = 465;
 // Adres email osby która wysyła maila
 private static final String FROM = "wybierznazwe.natalia@gmail.com";
 // Hasło do konta osoby która wysyła maila podajemy w konsoli
 private static String haslo;
 // Adres email osoby do której wysyłany jest mail
 private static final String TO = ("nataliaaaxd@wp.pl");
 // Temat wiadomości
 private static final String SUBJECT = "Witaj:)";
 // Treść wiadomości
 private static final String CONTENT = "Wiadomosc wyslana z java api ";


@SuppressWarnings("resource")
public static void main(String[] args) {
	  System.out.printf("Podaj haslo \n");
	Scanner odczyt = new Scanner(System.in);
	  haslo = odczyt.nextLine();
	
		 try {
   new SendMail().send(); 
   System.out.printf("wyslano e-mail");
  } catch (MessagingException e) {
	  System.out.printf("Nie wyslano e-mail!");
   e.printStackTrace();
  }
	  }

 public void send() throws MessagingException {

  Properties props = new Properties();
  props.put("mail.transport.protocol", "smtps");
  props.put("mail.smtps.auth", "true");

  // Inicjalizacja sesji
  Session mailSession = Session.getDefaultInstance(props);

  // ustawienie debagowania, jeśli nie chcesz oglądać logów to usuń
  // linijkę poniżej lub zmień wartość na false
  mailSession.setDebug(true);

  // Tworzenie wiadomości email
  MimeMessage message = new MimeMessage(mailSession);
  message.setSubject(SUBJECT);
  message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
  message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));

  Transport transport = mailSession.getTransport();
  transport.connect(HOST, PORT, FROM, haslo);

  // wysłanie wiadomości
  transport.sendMessage(message, message
    .getRecipients(Message.RecipientType.TO));
  transport.close();
 }
}
