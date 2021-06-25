package FrontEnd;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import DAL.DALPersonel;
import DAL.DALUrunler;
import DAL.DALYetkiler;
import Interfaces.FrontEndInterfaces;
import Types.PersonelContract;
import Types.UrunlerContract;

public class PersonelIslemleriFE extends JDialog implements FrontEndInterfaces {

	public PersonelIslemleriFE() {
		initPencere();

	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel ��lemleri"));
		add(panel);
		setTitle("Personel Bilgi ��lemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel personelIdLabel = new JLabel("Personel Id", JLabel.RIGHT);
		panel.add(personelIdLabel);
		JTextField personelIdField = new JTextField(10);
		panel.add(personelIdField);

		JLabel personelAdiLabel = new JLabel("Personel Adi Soyadi", JLabel.RIGHT);
		panel.add(personelAdiLabel);
		JTextField personelAdiField = new JTextField(10);
		panel.add(personelAdiField);

		JLabel personelEmailLabel = new JLabel("Personel Email ", JLabel.RIGHT);
		panel.add(personelEmailLabel);
		JTextField personelEmailField = new JTextField(10);
		panel.add(personelEmailField);

		JButton personelKaydetButton = new JButton("Personel G�ncelle ");
		panel.add(personelKaydetButton);
		personelKaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// String date = format.format(urunTarihi.getDate());
				contract.setId(Integer.parseInt(personelIdField.getText()));
				contract.setAdiSoyadi(personelAdiField.getText());
				contract.setEmail(personelEmailField.getText());
				new DALPersonel().Update(contract);
				JOptionPane.showMessageDialog(null, "Ba�ar�l� bir �ekilde personel g�ncellendi");

			}
		});
		JButton personelIptalButton = new JButton("Personel Sil");
		panel.add(personelIptalButton);
		personelIptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				contract.setId(Integer.parseInt(personelIdField.getText()));
				new DALPersonel().Delete(contract);
				JOptionPane.showMessageDialog(null, "Ba�ar�l� bir �ekilde �r�n Silindi");
				
			}
		});
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}