package Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import DAL.DALAccounts;
import FrontEnd.KategoriDuzenleFE;
import FrontEnd.LoginFE;
import FrontEnd.PersonelEkleFE;
import FrontEnd.PersonelIslemleriFE;
import FrontEnd.SifreÝslemleriFE;
import FrontEnd.UrunDuzenleFE;
import FrontEnd.UrunEkleFE;
import FrontEnd.YetkiEkleFE;
import FrontEnd.kategoriEkleFE;
import Types.PersonelContract;

public class MenulerUI {

	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();

		JMenu dosyaMenu = new JMenu("Dosya");
		bar.add(dosyaMenu);
		JMenuItem cikisItem = new JMenuItem("Çýkýþ");
		dosyaMenu.add(cikisItem);
		cikisItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/* Ürünler Menüsü */
		JMenu urunlerMenu = new JMenu("Ürünler");
		bar.add(urunlerMenu);
		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);
		JMenuItem kategoriEkleItem = new JMenuItem("Kategori Ekle");
		urunlerMenu.add(kategoriEkleItem);
		urunlerMenu.addSeparator();
		JMenuItem urunDuzenleItem = new JMenuItem("Ürünü Düzenle");
		urunlerMenu.add(urunDuzenleItem);
		urunDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UrunDuzenleFE();

			}
		});
		JMenuItem kategoriDuzenleItem = new JMenuItem("Kategoriyi Düzenle");
		urunlerMenu.add(kategoriDuzenleItem);
		kategoriDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriDuzenleFE();

			}
		});

		/* Personeller Menüsü */
		JMenu personellerMenu = new JMenu("Personel Ýþlemleri");
		bar.add(personellerMenu);
		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);
		personelEkleItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new PersonelEkleFE();
					}
				});

			}
		});
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);
		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new YetkiEkleFE();
					}
				});
			}
		});
		JMenuItem sifreBelirleItem = new JMenuItem("Þifre Belirleme");
		personellerMenu.add(sifreBelirleItem);
		sifreBelirleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new SifreÝslemleriFE();

			}
		});
		personellerMenu.addSeparator();
		JMenuItem personelDuzenleItem = new JMenuItem("Personel Düzenle");
		personelDuzenleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PersonelIslemleriFE();

			}
		});
		personellerMenu.add(personelDuzenleItem);

	

		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();

		if (new DALAccounts().GetYetkiId(contract.getId()).getYetkiId() == 2) {
			personellerMenu.hide();
		} else if (new DALAccounts().GetYetkiId(contract.getId()).getYetkiId() == 3) {
			
			personellerMenu.hide();
			urunlerMenu.hide();
		}

		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						new UrunEkleFE();
					}
				});

			}
		});

		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new kategoriEkleFE();

			}
		});

		return bar;
	}
}