package FrontEnd;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import DAL.DALAccounts;
import DAL.DALPersonel;
import DAL.DALYetkiler;
import Interfaces.FrontEndInterfaces;
import Types.AccountsContract;
import Types.PersonelContract;
import Types.YetkilerContract;

public class Sifre�slemleriFE extends JDialog implements FrontEndInterfaces {

	public Sifre�slemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("�ifre Belirleme ��lemleri"));
		add(panel);
		setTitle("�ifre Belirleme ��lemleri");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel personelLabel = new JLabel("Personel Se�", JLabel.RIGHT);
		panel.add(personelLabel);
		JComboBox personelBox = new JComboBox(new DALPersonel().GetAll().toArray());
		panel.add(personelBox);
		JLabel yetkiLabel = new JLabel("Yetki Se�", JLabel.RIGHT);
		panel.add(yetkiLabel);
		JComboBox yetkiBox = new JComboBox(new DALYetkiler().GetAll().toArray());
		panel.add(yetkiBox);
		JLabel passwordLabel = new JLabel("�ifre belirle", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);
		JLabel passTekrarLabel = new JLabel("�ifre Tekrar", JLabel.RIGHT);
		panel.add(passTekrarLabel);
		JPasswordField passTekrarField = new JPasswordField(10);
		panel.add(passTekrarField);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);
  
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		
		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract = new AccountsContract();
				PersonelContract pContract = (PersonelContract) personelBox.getSelectedItem();
				YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();

				if (passField.getText().equals(passTekrarField.getText())) {
					contract.setPersonelId(pContract.getId());
					contract.setYetkiId(yContract.getId());
					contract.setSifre(passField.getText());
					new DALAccounts().Insert(contract);
					JOptionPane.showMessageDialog(null,
							pContract.getAdiSoyadi() + " adl� ki�iye" + yContract.getAdi() + " yetkisi verilmi�tir.");
				} else {
					JOptionPane.showMessageDialog(null, "�ifreler E�le�miyor");
				}

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