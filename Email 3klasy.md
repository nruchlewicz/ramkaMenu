_Asia Powinna być dumna!_

#CLASS OKNO

	import javax.mail.MessagingException;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import java.awt.BorderLayout;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class Okno extends JFrame{
		public static String getPassword(){ //pobranie hasla z pola passField i zapisanie go w passsowrd.
			String password ="";
			char[] pass = passField.getPassword();
			for (int i=0; i<pass.length;  i++){
				password += pass[i];
			}
			return password;
		}
	
		private static JPasswordField passField;
		public Okno(){
			super("Wyślij E-Mail");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel panel = new JPanel();
			setLayout(new FlowLayout());
			panel.setLayout(new GridLayout(3,2));
			setSize(400,300);
			JLabel opis;
			JButton wyslij = new JButton("wyslij");
			opis = new JLabel ("Podaj haslo, aby wyslać e-mail");
			passField = new JPasswordField();
			panel.add(opis);
			panel.add(passField);
			panel.add(wyslij);
			wyslij.addActionListener(new ActionListener() {
				  public void actionPerformed(ActionEvent le) {
					  try {
						   new SendMail().send(); 
						   System.out.printf("Wysłano e-mail!");
						  } catch (MessagingException e) {
							  System.out.printf("HASLO:"+getPassword());
							  System.out.printf("Nie wysłano e-mail!");
						   e.printStackTrace();
						  }
				        }
				      });
			panel.setVisible(true);
			this.add(panel, BorderLayout.CENTER);
			setVisible(true);
		}
	}
	
#CLASS SendMail


	import java.util.Properties;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeMessage;
	public class SendMail {
	 
		private static final String HOST = "smtp.gmail.com";
		private static final int PORT = 465;
		// Adres email osby która wysyła maila
		private static final String FROM = "wybierznazwe.natalia@gmail.com"; 
		// Hasło do konta osoby która wysyła maila podajemy w konsoli
		// Adres email osoby do której wysyłany jest mail
		private static final String TO = ("nataliaaaxd@wp.pl");
		// Temat wiadomości
		private static final String SUBJECT = "Java Witaj:)";
		// Treść wiadomości
		private static final String CONTENT = "Witaj świecie, działa to czy nie ?:)";
		String password = Okno.getPassword(); //pobranie password z klasy Okno
		@SuppressWarnings("resource")
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
			 transport.connect(HOST, PORT, FROM, password);

			 // wysłanie wiadomości
			 transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			 transport.close();
			}
		}

#CLASS Test1

	import java.awt.EventQueue;
	import javax.mail.MessagingException;

		public class Test1{
			@SuppressWarnings("resource")
				public static void main(String[] args){
					EventQueue.invokeLater(new Runnable(){
						@Override
						public void run(){
						new Okno();
						}
		});
	}
	}


