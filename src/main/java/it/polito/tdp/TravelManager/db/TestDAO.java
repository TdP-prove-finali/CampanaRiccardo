package it.polito.tdp.TravelManager.db;

public class TestDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TravelManagerDAO dao = new TravelManagerDAO();
//		System.out.println(dao.loadAllAeroporti());
		
		System.out.println(dao.searchAirBnBs(300.0, "apartment", 3, "LA", 10, "90"));
	}

}
