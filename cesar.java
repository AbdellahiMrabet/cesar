package chiffrement_cesar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MainClass implements ActionListener{
	//declaration des variables

	JFrame window = new JFrame("chiffrement du César");
	
	JLabel titre1 = new JLabel("1. Chiffrement");
	
	JLabel titre2 = new JLabel("2. Déchiffrement");
	
	JLabel txt_clrl = new JLabel("Text clair: ");
	
	JLabel txt_chl = new JLabel("Text chiffré: ");
	
	JLabel txt_chl2 = new JLabel("Text chiffré: ");
	
	JLabel txt_clrl2 = new JLabel("Txt dchffré: ");
	
	JLabel clefl = new JLabel("Clef: ");
	
	JTextArea txt_cl = new JTextArea();
	
	JTextArea txt_ch2 = new JTextArea();
	
	JTextArea txt_ch = new JTextArea();
	
	JTextArea txt_cl2 = new JTextArea();
	
	JButton b1 = new JButton("Chiffrer");
	
	JButton b2 = new JButton("Déchiffrer");
	
	JButton b3 = new JButton("Cryptanalyse");
	
    JComboBox<Integer> clef = new JComboBox<>();
    //fin dv
	
	MainClass(){
		window.setSize(500, 500);//ajuster la taille de la fenêtre
		window.setVisible(true);
		window.setLayout(null);
		
		titre1.setBounds(30, 20, 100, 20);//ajuster les coordonnées et les dims
		window.add(titre1);//ajout du titre à la fenêtre
		
		titre2.setBounds(30, 205, 100, 20);
		window.add(titre2);
		
		txt_chl2.setBounds(70, 220, 100, 20);
		window.add(txt_chl2);
		
		txt_ch2.setBounds(140, 220, 210, 50);
		window.add(txt_ch2);
		
		txt_ch2.setLineWrap(true);
		
		txt_clrl2.setBounds(70, 280, 100, 20);
		window.add(txt_clrl2);
		
		txt_cl2.setBounds(140, 280, 210, 50);
		window.add(txt_cl2);
		
		txt_cl2.setLineWrap(true);
		
		txt_clrl.setBounds(70, 50, 100, 20);
		window.add(txt_clrl);
		
		txt_chl.setBounds(70, 110, 100, 20);
		window.add(txt_chl);
		
		txt_cl.setBounds(140, 50, 210, 50);
		window.add(txt_cl);
		
		txt_cl.setLineWrap(true);
		
		txt_ch.setBounds(140, 110, 210, 50);
		window.add(txt_ch);
		
		txt_ch.setLineWrap(true);
		
		clefl.setBounds(70, 165, 50, 20);
		window.add(clefl);
		
		clef.setBounds(140, 165, 50, 20);
		window.add(clef);
		
		b1.setBounds(140, 190, 80, 20);
		b1.addActionListener(this);
		window.add(b1);
		
		b2.setBounds(140, 335, 91, 20);
		b2.addActionListener(this);
		window.add(b2);
		
		b3.setBounds(235, 335, 115, 20);
		b3.addActionListener(this);
		window.add(b3);
		
		//affecter des valeurs pour le combo clef
		for(int i = 1; i < 26; i++) {
			clef.addItem(i);
		}
	}//fin mainClass
	
	//fonction decoupage
	char[] decoupage(String text) {
		char[] ch = new char[text.length()];
		for(int i = 0; i < text.length(); i++) {
			ch[i] = text.charAt(i);
		}
		return ch;
	}//fin decoupage
	
	//alphabet normal pour l'utiliser comme référence
	ArrayList<String> alphabet = new ArrayList<String>();
	

	//on creer une table pour les indexes des lettre dans le tab de l'alphabet
	ArrayList<Integer> index = new ArrayList<Integer>();
	
	//fonction get index
	void getindex(char[] c, ArrayList<Integer> idx) {
		for(char cr = 'A'; cr <= 'Z'; ++cr) {
			alphabet.add(Character.toString(cr));
		}
		idx.clear();
		for(int i = 0; i < c.length; i++){
			//traitement d'espace
			if(Character.toString(c[i]).equals(" ")) {
				idx.add(100);
			}
			//
			for(int j = 0; j < 26; j++) {
				if(Character.toString(c[i]).equals(alphabet.get(j))) {
					idx.add(j);
				}
			}
			
		}
	}//fin getindex
	
	//fonction change ch
	String change_ch(char[] c, ArrayList<Integer> idx, int cle) {
		String dec = "";//chaine vide à ramplir par les caractères
		//après la conversion
		int k = 0;
		for(int j = 0; j < idx.size(); j++) {
				k = (idx.get(j) + cle)%26;
			c[j] = alphabet.get(k).charAt(0);
			//traitement d'espace
			if(index.get(j) == 100) {
				c[j] = " ".charAt(0);			
				}
			///
		}
		
		for(int i = 0; i < idx.size(); i++) {
			dec = dec+Character.toString(c[i]);
		}
		return dec;
	}//fin fonction change ch
	
	//fonction dechiffrer
	void dechiffrer(String txt, int cle) {
		char[] ch = decoupage(txt);
		getindex(ch, index);
		txt_cl2.setText(change_ch(ch, index, (26 - cle)));
	}//fin dechiffrer
	
	
	@Override 
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//text clair
		String txt_clair = txt_cl.getText().toUpperCase();
		
		//text chiffré
		String txt_chiffre = txt_ch2.getText().toUpperCase();
		
				if(arg0.getSource() == b1) {
					
					//conversion du text clair en tableau
					char[] ch = decoupage(txt_clair);
					//dans la phase de chiffrement
					getindex(ch, index);
				//alphabet décalé dans la phase de chifferment
				txt_ch.setText(change_ch(ch, index, (int) clef.getSelectedItem()));
				
				}//fin de la première boutton
				

				
				if(arg0.getSource() == b2) {
				dechiffrer(txt_chiffre, (int) clef.getSelectedItem());
	}//fin de la dexième boutton
				
	if(arg0.getSource() == b3) {
		char[] ch2 = decoupage(txt_chiffre);
		int max = 0;
		int ind_mx = 0;
		//on creer une table pour les indexes des lettre repetes
		int[] stat = new int[txt_chiffre.length()];
		for(int i = 0; i < txt_chiffre.length(); i++) {
			stat[i] = 1;
		}
		//on calcul la fréquence de chaque lettre et la fré max.
		for(int i = 0; i < txt_chiffre.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(!Character.toString(ch2[i]).equals(" ")&&
						Character.toString(ch2[i]).equals(Character.toString(ch2[j]))
						&&(i != j)) {
					stat[j] = stat[j] + 1;
				}//fin if
				if(max < stat[j]) {
					max = stat[j];
					ind_mx = j;
				}//fin if max
			}//fin for j
		}//fin for(int i = 0; i < txt_chiffre.length(); i++)
		//obtenir la clé de chiffrement basant sur le fait que "e" est l
		//plus fréquent en français
		for(int inx2 = 0; inx2 < 26; inx2++) {
			if(Character.toString(ch2[ind_mx]).equals(alphabet.get(inx2))) {
				dechiffrer(txt_chiffre, inx2 - 4);
			}
		}//fin for(int inx2 = 0; inx2 < 26; inx2++)
		
	}//fin de la troisième boutton
	}//fin de la fonction action performed

	//methode main
		public static void main(String[] args) {
			new MainClass();
		}
}