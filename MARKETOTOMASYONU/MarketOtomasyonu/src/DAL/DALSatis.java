package DAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Complex.Types.SatisContractComplex;
import Core.ObjectHelper;
import Interfaces.DALInterfaces;
import Types.SatisContract;

public class DALSatis extends ObjectHelper implements DALInterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis (UrunId,  Tarih, Adet, PersonelId) VALUES("
					+ entity.getUrunId() + ","  + entity.getTarih() + "',"
					+ entity.getAdet() + "," + entity.getPersonelId() + ")");
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<SatisContractComplex> GetAllSatis() {
		List<SatisContractComplex> dataContract = new ArrayList<SatisContractComplex>();
		Connection connection = getConnection();
		SatisContractComplex contract;
		try {
			Statement statement = connection.createStatement();

			ResultSet resulset = statement.executeQuery(
					"SELECT satis.Id, personel.AdiSoyadi, Adi, Adet, satis.Tarih FROM satis "
							+ "LEFT JOIN urunler on satis.UrunId = urunler.Id "
							+ "LEFT JOIN personel on satis.PersonelId = personel.Id ORDER BY satis.Id DESC");

			while (resulset.next()) {

				contract = new SatisContractComplex();

				contract.setId(resulset.getInt("Id"));
				contract.setPersonelAdi(resulset.getString("personel.AdiSoyadi"));
				contract.setTarih(resulset.getString("satis.Tarih"));
				contract.setUrunAdi(resulset.getString("Adi"));
				contract.setAdet(resulset.getInt("Adet"));

				dataContract.add(contract);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContract;
	}

	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Delete(SatisContract entity) {

	}

	@Override
	public void Update(SatisContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
