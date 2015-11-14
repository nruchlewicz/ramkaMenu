#CLASS MAIN

	import java.awt.EventQueue;
	public class Main {
	   public static void main(String[] args) {
	       EventQueue.invokeLater(new Runnable() {
	           @Override
	           public void run() {
	               new Frame();
	         }
	       });
	   }
	}

#CLASS FRAME

	import java.awt.Dimension;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
 
	public class Frame extends JFrame {
	   public Frame() {
	    super("Logowanies");
	    LoginListener listener = new LoginListener(this);
	    JPanel loginPanel = new LoginPanel(listener);
	    add(loginPanel);
 
	     setPreferredSize(new Dimension(600, 400));
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      pack();
	      setVisible(true);
	   }
	}

#CLASS LOGINLISTENER

	import java.awt.Component;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
 	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.SwingUtilities;

	public class LoginListener implements ActionListener {
	 //Główna ramka programu
	 private final JFrame frame;
	 //Panel logowania, potrzebny do pobrania loginu i hasła
	 private LoginPanel loginPanel;
	  public void setPanel(LoginPanel loginPanel) {
	      this.loginPanel = loginPanel;
	  }
	    public LoginListener(JFrame frame) {
	     this.frame = frame;
	 }
    	@Override
	 public void actionPerformed(ActionEvent event) {
	   String login = loginPanel.getName();
	  String haslo = loginPanel.getPassword();
	  if (UserValidator.authenticate(login, haslo)) {
        	   SwingUtilities.invokeLater(new Runnable() {
        	     @Override
        	    public void run() {
        	          // panel z edytorem html
        	     	RamkaMenu ramka = new RamkaMenu();
                	    // usuwamy panel logowania
                    	frame.getContentPane().removeAll();
                    	ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    	ramka.setVisible(true);
                    	// dodajemy panel i odświeżamy widok
                	 frame.add(ramka);
                    	frame.validate();
                	}
            	});
        	}
    	}
	}

#CLASS LOGINPANEL

	import java.awt.BorderLayout;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;	
	import java.awt.GridLayout;
 	import javax.swing.AbstractButton;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
 
	public class LoginPanel extends JPanel {
    		private JTextField PoleNazwa; //pole na nazwę
    		private JPasswordField PoleHaslo; //pole na hasło
    		private JButton PrzyciskZaloguj; //przycisk logowania
		private LoginListener listener; //słuchacz przycisku
 	
		   /**
     		* @return wprowadzona nazwa użytkownika
		 */
    		public String getName() {
        	return PoleNazwa.getText();
		 }
    		/**
		 * @return wprowadzone przez użytkownika hasło
     		*/
    		public String getPassword() {
        	String haslo = "";
        	char[] pass = PoleHaslo.getPassword();
        	for(int i=0; i<pass.length; i++) {
            	haslo += pass[i];
        	}
        	return haslo;
    		}
 
    	public LoginPanel(LoginListener listener) {
        	super();
        	// ustawiamy layout
        	GridBagLayout gridBag = new GridBagLayout();
        	GridBagConstraints constraints = new GridBagConstraints();
        	constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
        	setLayout(gridBag);
        	// tworzymy komponenty logowania
	        this.listener = listener;
	        this.listener.setPanel(this);
	       createComponents();
	    }
	 
	    /**
	     * Metoda, która tworzy etykiety i pola do wprowadzania danych.
	     */
	    private void createComponents() {
	    	JLabel opis= new JLabel("<html>Proszę się zalogować, aby przejść dalej! </html>");
	        JLabel login = new JLabel("<html>Login:</html>");
	        JLabel haslo = new JLabel("Hasło: ");
	        PoleNazwa = new JTextField();
	        PoleHaslo = new JPasswordField();
 
	        //pomocniczy panel do wprowadzania danych
	        JPanel inputPanel = new JPanel();
	        inputPanel.add(opis);
	        inputPanel.setLayout(new GridLayout(8,8));
	        inputPanel.add(login);
	        inputPanel.add(PoleNazwa);
	        inputPanel.add(haslo);
	        inputPanel.add(PoleHaslo);
	        //tworzymy przycisk logowania
	        PrzyciskZaloguj = new JButton("Zaloguj");
	        PrzyciskZaloguj.addActionListener(listener);
		 
	        //pomocniczy panel do wyśrodkowania elementów
	        JPanel parentPanel = new JPanel();
	        parentPanel.setLayout(new BorderLayout());
	        parentPanel.add(inputPanel, BorderLayout.CENTER);
	        parentPanel.add(PrzyciskZaloguj, BorderLayout.SOUTH);
	 
	        // dodajemy do głównego panelu
	        this.add(parentPanel);
	    }
	}

#CLASS USER VALIDATOR


	public class UserValidator {
	    private static final String name = "tajniak";
	    private static final String password = "1234";
	    public static boolean authenticate(String login, String haslo) {
	        if(UserValidator.name.equals(name) & UserValidator.password.equals(password))
	            return true;
	        else
	            return false;
	    }
	}

#CLASS TEST MENU

	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	import javax.swing.event.*;
	
	/**
	   Ramka, zawierajaca pasek menu.
	*/
	class RamkaMenu extends JFrame
	{  
		
	   public RamkaMenu()
	   {  
		   
	      setTitle("TestMenu");
	      setSize(SZEROKOSC, WYSOKOSC);
	
	      JMenu menuPlik = new JMenu("Plik");
	      JMenuItem elemNowy = menuPlik.add(new DzialanieTestowe("Nowy"));
	
	      // demonstruj akceleratory
	
	      JMenuItem elemOtworz = menuPlik.add(new DzialanieTestowe("Otwórz"));
	      elemOtworz.setAccelerator(KeyStroke.getKeyStroke(
	        KeyEvent.VK_O, InputEvent.CTRL_MASK));
	
	      menuPlik.addSeparator();
	
	      elemZapisz = menuPlik.add(new DzialanieTestowe("Zapisz"));
	      elemZapisz.setAccelerator(KeyStroke.getKeyStroke(
	         KeyEvent.VK_Z, InputEvent.CTRL_MASK));
	      elemZapiszJako = menuPlik.add(new DzialanieTestowe("Zapisz jako"));
	      menuPlik.addSeparator();
	      menuPlik.add(new AbstractAction("Zamknij")
	         {
	            public void actionPerformed(ActionEvent zdarzenie)
	            {
	               System.exit(0);
	            }
	         });
	
	      // demonstruj wlaczanie/wylaczanie elementow
	      menuPlik.addMenuListener(new SluchaczMenuPlik());
	      // demonstruj pole wyboru i przelacznik
	      elemTylkoDoOdczytu = new JCheckBoxMenuItem("Tylko do odczytu");
	      ButtonGroup grupa = new ButtonGroup();
	      JRadioButtonMenuItem elemWstaw
	         = new JRadioButtonMenuItem("Wstaw");
	      elemWstaw.setSelected(true);
	      JRadioButtonMenuItem elemNadpisz
	         = new JRadioButtonMenuItem("Nadpisz");
	
	      grupa.add(elemWstaw);
	      grupa.add(elemNadpisz);
	
	      // demonstruj ikony
	      final JTextField textField = new JTextField(15);
	      Action dzialWytnij = new DzialanieTestowe("Wytnij"); 
	      dzialWytnij.putValue(Action.SMALL_ICON, 
	         new ImageIcon("wytnij.gif"));
	      Action dzialKopiuj = new DzialanieTestowe("Kopiuj");
	      dzialKopiuj.putValue(Action.SMALL_ICON, 
	         new ImageIcon("kopiuj.gif"));
	      Action dzialWklej = new DzialanieTestowe("Wklej");
	      dzialWklej.putValue(Action.SMALL_ICON, 
	         new ImageIcon("wklej.gif"));
	
	      JMenu menuEdycji = new JMenu("Edycja");
	      menuEdycji.add(dzialWytnij);
	      menuEdycji.add(dzialKopiuj);
	      menuEdycji.add(dzialWklej);
	
	      // demonstruj menu zagniezdzone
	
	      JMenu menuOpcji = new JMenu("Opcje");
	
	      menuOpcji.add(elemTylkoDoOdczytu);
	      menuOpcji.addSeparator();
	      menuOpcji.add(elemWstaw);
	      menuOpcji.add(elemNadpisz);
	
	      menuEdycji.addSeparator();
	      menuEdycji.add(menuOpcji);
	
	      // demonstruj mnemoniki
	
	      JMenu menuPomocy = new JMenu("Pomoc");
	      menuPomocy.setMnemonic('P');
	  
	      
	      JMenuItem elemIndeks = new JMenuItem("Indeks");
	      elemIndeks.setMnemonic('I');
	      menuPomocy.add(elemIndeks);
	      
	      final JEditorPane editorPane = new JEditorPane();
	      
	      // mozesz rowniez dolaczyc klawisz mnemonika do dzialania
	     // Action dzialPrzeczytaj =new DzialanieTestowe("Przeczytaj");
	    //  dzialPrzeczytaj.putValue(Action.MNEMONIC_KEY, 
	   //      new Integer('P'));
	   //   menuPomocy.add(dzialPrzeczytaj);
	      menuPomocy.add(new DzialanieTestowe("Przeczytaj")
	      {
         public void actionPerformed(ActionEvent zdarzenie)
         {
             SwingUtilities.invokeLater(new Runnable(){
                 public void run(){
               	  utworzGui();
                 }

				private void utworzGui() {
					
					JFrame frame = new JFrame("Pomoc Programu");
					 JLabel label = new JLabel ("<html>"+
							 "Pomoc dla programu Tu coś sie dopisze <br>"+
							 "Klika linijek tekstu pomocy <br>"+
						      "obsluga programu");
					 // JTextArea tekst = new JTextArea ("Pomoc dla programu Tu coś sie dopisze \n"+
					 //"Klika linijek tekstu pomocy \n"+
					//		  "obsluga programu");
					  frame.getContentPane().add(label);
					//  frame.getContentPane().add(tekst);
					  frame.setSize(300,150);
					  frame.setVisible(true);
			
				}
                 });
         }
      });
   

      // dolacz menu najwyzszego poziomu do paska menu

      JMenuBar pasekMenu = new JMenuBar();
      setJMenuBar(pasekMenu);

      pasekMenu.add(menuPlik);
      pasekMenu.add(menuEdycji);
      pasekMenu.add(menuPomocy);

      // demonstruj menu kontekstowe

      kontekst = new JPopupMenu();
      kontekst.add(dzialWytnij);
      {

    	  textField.cut();
      }
      kontekst.add(dzialKopiuj);
      kontekst.add(dzialWklej);

      getContentPane().addMouseListener(new 
         MouseAdapter()
         {  
            public void mouseReleased(MouseEvent zdarzenie)         
            {  
               if (zdarzenie.isPopupTrigger())
                  kontekst.show(zdarzenie.getComponent(),
                     zdarzenie.getX(), zdarzenie.getY());
            }
         });
   } 
   public static final int SZEROKOSC = 600;
   public static final int WYSOKOSC = 400; 
   

   private JMenuItem elemZapisz;
   private JMenuItem elemZapiszJako;
   private JCheckBoxMenuItem elemTylkoDoOdczytu;
   private JPopupMenu kontekst;

   /**
      aktualizuje stan menu Plik. Opcja Zapisz jest wylaczana
      jezeli dokument znajduje sie w trybie "Tylko do odczytu"
   */
   private class SluchaczMenuPlik implements MenuListener
   {
      public void menuSelected(MenuEvent zd)
      {  
         elemZapisz.setEnabled(!elemTylkoDoOdczytu.isSelected());
         elemZapiszJako.setEnabled(!elemTylkoDoOdczytu.isSelected());
      }

      public void menuDeselected(MenuEvent zd) {}
      
      public void menuCanceled(MenuEvent zd) {}
   }
}


/**
   Przykladowe dzialanie, przesylajace nazwe do System.out.
*/
class DzialanieTestowe extends AbstractAction
{  
   public DzialanieTestowe(String nazwa) { super(nazwa); }
   
   public void actionPerformed(ActionEvent zdarzenie)
   {  
      System.out.println("Zostal wybrany element " + getValue(Action.NAME)
         + ".");
   }
}

public class TestMenu
{
   {  
      RamkaMenu ramka = new RamkaMenu();
      ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ramka.setVisible(true);
      final JTextField textField = new JTextField(15);
      JButton buttonCut = new JButton("Wytnij");
      JButton buttonPaste = new JButton("Wklej");
      JButton buttonCopy = new JButton("Kopiuj");


        ramka.setLayout(new FlowLayout());
    //  jfrm.setSize(230, 150);
    //  jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      buttonCut.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent le) {
          textField.cut();
        }
      });

      buttonPaste.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent le) {
          textField.paste();
        }
      });

      buttonCopy.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent le) {
          textField.copy();
        }
      });
    
      ramka.add(textField);
      ramka.add(buttonCut);
      ramka.add(buttonPaste);
      ramka.add(buttonCopy);
      ramka.setVisible(true);
    }

}




