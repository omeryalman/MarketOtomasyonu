package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import Complex.Types.SatisContractComplex;
import Complex.Types.StokContractComplex;
import Complex.Types.StokContractTotalComplex;
import DAL.DALSatis;
import DAL.DALStock;
import DAL.DALUrunler;
import Interfaces.FrontEndInterfaces;
import Types.PersonelContract;
import Types.SatisContract;
import Types.StokContract;
import Types.UrunlerContract;
import Utilities.MenulerUI;

public class AnaPencereUI extends JFrame implements FrontEndInterfaces {

	public AnaPencereUI() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		
		JMenuBar bar = initBar();
		add(panel);
		setJMenuBar(bar);

		setTitle("MarketStokOtomasyonu");
		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane pane = initTabs();
		
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerUI.initBar();

		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		ImageIcon Icon = new ImageIcon("icons/stock.png");
		ImageIcon Icon2 = new ImageIcon("icons/stock.png");
		
		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		/* Stok �temleri */
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel stokSolAltPanel = new JPanel();

		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok ��lemleri"));

		Object[] stokKolonlar = { "Id", "�r�n Ad�", "Personel Ad�", "Adeti", "Tarihi", };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);

		for (StokContractComplex contract : new DALStock().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("�r�n Adi:", JLabel.RIGHT);
		
		
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new DALUrunler().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();

		stokSolUstPanel.add(stokTarihi);

		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		JButton stokTotalButton = new JButton("Stok Toplam �r�n");
		stokSolUstPanel.add(stokTotalButton);
		stokTotalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokContractTotalComplex total : new DALStock().GetTotalStok()) {

					model.addRow(total.getVeriler());
				}
			}
		});

		stokYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokContractComplex compContract : new DALStock().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}

			}
		});

		for (StokContractComplex compContract : new DALStock().GetAllStok()) {
			model.addRow(compContract.getVeriler());
		}
		stokEkleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stokTarihi.getDate());

				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new DALStock().Insert(contract);

				JOptionPane.showMessageDialog(null, uContract.getAdi() + " adl� �r�n eklenmi�tir");

				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokContractComplex compContract : new DALStock().GetAllStok()) {
					model.addRow(compContract.getVeriler());
				}
			}
		});

		/* Sat�� �temleri */
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(5, 2));
		JPanel satisSagAltPanel = new JPanel();

		Object[] satisKolonlar = { "Id",  "Personel Ad�", "�r�n Ad�", "Adeti", "Tarihi" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);
		JLabel satisUrunAdiLabel = new JLabel("�r�n Adi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new DALUrunler().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Sat�� Tarihi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();

		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Sat�� Yap");
		satisSagUstPanel.add(satisEkleButton);
		for (SatisContractComplex yenileContract : new DALSatis().GetAllSatis()) {
			satisModel.addRow(yenileContract.getVeriler());
		}
		satisEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
				SatisContract contract = new SatisContract();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(satisTarihi.getDate());

			
				contract.setPersonelId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setTarih(date);

				new DALSatis().Insert(contract);
				StokContract stokContract = new StokContract();
				stokContract.setPersonelId(pContract.getId());
				stokContract.setUrunId(uContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setTarih(date);
				new DALStock().Insert(stokContract);
				JOptionPane.showMessageDialog(null,
						contract.getPersonelId() + " id numaral� personel sat�� ger�ekle�tirmi�tir ve "
								+ uContract.getAdi() + " adl� �r�n stokta " + contract.getAdet()
								+ " adet eksiltilmi�tir");
				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for (SatisContractComplex yenileContract : new DALSatis().GetAllSatis()) {
					satisModel.addRow(yenileContract.getVeriler());
				}
			}
		});

		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = satisModel.getRowCount();
				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				for (SatisContractComplex contract : new DALSatis().GetAllSatis()) {
					satisModel.addRow(contract.getVeriler());
				}

			}
		});
		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);
		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);
		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);
		pane.addTab("Stoklar", Icon, stokPanel, "Doesn't Nothing");
		pane.addTab("Sat��lar", Icon2, satisPanel, "Doesn't Nothing");
		return pane;
	}
}
