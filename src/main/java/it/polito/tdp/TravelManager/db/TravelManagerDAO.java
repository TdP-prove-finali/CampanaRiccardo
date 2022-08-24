package it.polito.tdp.TravelManager.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.TravelManager.model.Adiacenza;
import it.polito.tdp.TravelManager.model.Aeroporto;
import it.polito.tdp.TravelManager.model.Volo;

public class TravelManagerDAO {
	
	public List<Aeroporto> loadAllAeroporti(){

		String sql = "SELECT * "
				+ "FROM Airport";
		List<Aeroporto> result = new ArrayList<Aeroporto>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Aeroporto(rs.getInt("ID"), rs.getString("IATA_CODE"), rs.getString("AIRPORT"), rs.getString("CITY"), rs.getString("STATE"), rs.getString("STATENAME")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Volo> loadAllVoli(Map<String, Aeroporto> mappaAeroporti){
		String sql = "SELECT c.itinID, c.origin, c.dest, t.farepermile, t.passengers, t.itinfare, t.distance "
				+ "FROM Coupon c, Ticket t "
				+ "WHERE c.itinID = t.itinID";
		List<Volo> result = new ArrayList<Volo>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Volo(rs.getString("itinID"), mappaAeroporti.get(rs.getString("origin")), mappaAeroporti.get(rs.getString("dest")), rs.getDouble("farepermile"), rs.getInt("passengers"), rs.getDouble("itinfare"), rs.getInt("distance")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public Adiacenza loadAdiacenze(Aeroporto a1, Aeroporto a2){
		String sql = "SELECT c.Origin, c.Dest, AVG(t.itinFare) as AVG "
				+ "FROM Coupon c, Ticket t "
				+ "WHERE c.Origin = ? AND c.Dest = ? AND c.itinID = t.itinID "
				+ "GROUP BY c.Origin, c.Dest";
		
		Adiacenza result = null;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, a1.getIATA());
			st.setString(2, a2.getIATA());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result = new Adiacenza(a1, a2, rs.getDouble("AVG"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Adiacenza> loadAllAdiacenze(Map<String, Aeroporto> mappaAeroporti){
		String sql = "SELECT c.origin, c.dest, AVG(t.itinFare) as AVG "
				+ "FROM Coupon c, Ticket t "
				+ "WHERE c.itinID = t.itinID "
				+ "GROUP BY c.Origin, c.Dest";
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Adiacenza(mappaAeroporti.get(rs.getString("origin")), mappaAeroporti.get(rs.getString("dest")), rs.getDouble("AVG")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

}
