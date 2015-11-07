package xyz;

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
					 JLabel label = new JLabel (
							 "Pomoc dla programu Tu coś sie dopisze \n"+
							 "Klika linijek tekstu pomocy "+
						      "obsluga programu");
					//  JTextArea tekst = new JTextArea ("Pomoc dla programu Tu coś sie dopisze"+
					// "Klika linijek tekstu pomocy"+
				//			  "obsluga programu");
					  frame.getContentPane().add(label);
					 
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
   public static void main(String[] args)
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




