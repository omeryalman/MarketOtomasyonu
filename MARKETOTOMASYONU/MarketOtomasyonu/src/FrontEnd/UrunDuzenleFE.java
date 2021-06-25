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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import DAL.DALUrunler;
import Interfaces.FrontEndInterfaces;
import Types.KategoriContract;
import Types.UrunlerContract;

public class UrunDuzenleFE extends JDialog implements FrontEndInterfaces {

	public UrunDuzenleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		add(panel);
		setTitle("�r�n D�zenle");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		/* kapan�nca hepsi kapanmas�n diye */
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createTitledBorder("�r�n D�zenleme Sayfas�"));
		JPanel ustPanel = new JPanel(new GridLayout(7, 3));
		ustPanel.setBorder(BorderFactory.createTitledBorder("�r�n D�zenle"));

		JLabel urunIdLabel = new JLabel("�r�n Id", JLabel.RIGHT);
		ustPanel.add(urunIdLabel);
		JTextField urunIdField = new JTextField(10);
		ustPanel.add(urunIdField);

		JLabel urunAdiLabel = new JLabel("�r�n Ad�", JLabel.RIGHT);
		ustPanel.add(urunAdiLabel);
		JTextField urunAdiField = new JTextField(10);
		ustPanel.add(urunAdiField);

		JLabel urunTarihLabel = new JLabel("Tarih", JLabel.RIGHT);
		ustPanel.add(urunTarihLabel);
		JDateChooser urunTarihi = new JDateChooser();
		ustPanel.add(urunTarihi);

		JLabel urunFiyatLabel = new JLabel("Fiyat", JLabel.RIGHT);
		ustPanel.add(urunFiyatLabel);

		JTextField urunFiyatField = new JTextField(10);
		ustPanel.add(urunFiyatField);

		JButton urunUpdateButton = new JButton("G�ncelle");
		ustPanel.add(urunUpdateButton);
		urunUpdateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(urunTarihi.getDate());
				contract.setAdi(urunAdiField.getText());

				contract.setId(Integer.parseInt(urunIdField.getText()));
				// System.out.println(urunAdiBox.getSelectedItem().toString());
				contract.setFiyat(Float.parseFloat(urunFiyatField.getText()));
				// System.out.println(Float.parseFloat(urunFiyatField.getText()));
				contract.setTarih(date.toString());
				new DALUrunler().Update(contract);
				JOptionPane.showMessageDialog(null, "Ba�ar�l� bir �ekilde �r�n g�ncellendi");
			}	
		});

		JButton urunDeleteButton = new JButton("Sil");
		ustPanel.add(urunDeleteButton);
		urunDeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				contract.setId(Integer.parseInt(urunIdField.getText()));
				new DALUrunler().Delete(contract);
				JOptionPane.showMessageDialog(null, "Ba�ar�l� bir �ekilde �r�n Silindi");
			}
		});
		panel.add(ustPanel);

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