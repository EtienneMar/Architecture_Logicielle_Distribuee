package web.service.client.main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import web.service.client.consultation.wsimport.Offre;

import java.awt.Font;
import javax.swing.JTextPane;

public class GuiDiaporama {

	private JFrame frame;
	private int counter = 0;
	private int i = 0;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Create the application.
	 */
	public GuiDiaporama(List<Offre> listOffre) {
		initialize(listOffre);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param offrePicBytesCode 
	 */
	private void initialize(List<Offre> listOffre) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1240, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//List stockant les bytes codes des différentes images
		ImageIcon picture[] = new ImageIcon[listOffre.size()];
		
		//List stockant les strings à afficher pour chaque image
		String stringPrint[] = new String[listOffre.size()];
		
		//String.format("%2.f", offre.getPrixChambreWithReduction())
		
		for (Offre offre : listOffre) {
			ImageIcon icon = new ImageIcon(offre.getPicOfChamberBytesCode());
			picture[i] = icon;
			String toPrint = (offre.getHotelNom()+" Chambre n°"+offre.getNumero()+"\n"
					+"Prix Chambre : "+offre.getPrixChambreWithReduction()+" €\n"
					+"Identifiant de l'offre : "+offre.getIdentifiant()+"\n"
					+"disponibilité : \n") ;
					for (int i = 0; i < offre.getDisponibilitee().size(); i++){
							toPrint += offre.getDisponibilitee().get(i)+" - ";
							toPrint += offre.getDisponibilitee().get(i+=1)+"\n";
					}
			stringPrint[i] = toPrint; 
			i++;
	
		}
		
		
		
		JLabel Picslider = new JLabel();
		Picslider.setBounds(24, 10, 936, 493);
		frame.getContentPane().add(Picslider);
		Picslider.setIcon(picture[0]);
		
		JTextPane textPanel = new JTextPane();
		textPanel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPanel.setBounds(970, 10, 235, 493);
		frame.getContentPane().add(textPanel);
		textPanel.setText(stringPrint[0]);
		
		JButton btnPrecedent = new JButton("Précedent");
		btnPrecedent.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (counter!=0) {
					counter -=1;
					Picslider.setIcon(picture[counter]);
					textPanel.setText(stringPrint[counter]);
				}
			}
		});
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnPrecedent) {
					if (counter==0) {
						JOptionPane.showMessageDialog(null,"Ceci est la première image");
					}
				}
			}
		});
		
		btnPrecedent.setBounds(59, 513, 210, 85);
		frame.getContentPane().add(btnPrecedent);

		JButton btnSuivant = new JButton("Suivant");
		btnSuivant.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (counter != picture.length) {
					counter +=1;
					Picslider.setIcon(picture[counter]);
					textPanel.setText(stringPrint[counter]);
				}
			}
		});
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnSuivant) {
					if (counter==picture.length-1) {
						JOptionPane.showMessageDialog(null,"Ceci est la dernière image");
					}
				}
			}
		});
		btnSuivant.setBounds(926, 513, 210, 85);
		frame.getContentPane().add(btnSuivant);
		
	
		
	}
}
