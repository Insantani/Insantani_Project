import java.sql.*;

public class ConnectDB {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public ConnectDB(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/insantani", "root", "");
			st = con.createStatement();
		}
		
		catch(Exception ex){
			System.out.println("Error:" + ex);
		}
	}
	
	public void getFarmer(){
		
		try {
			String query = "select * from farmer";
			rs = st.executeQuery(query);
			System.out.println("Farmer's Profile");
			while(rs.next()){
				String id = rs.getString("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String email = rs.getString("email");
				System.out.println("Farmer's Name:" + name + "\n"+
								   "ID: " + id + "\n" +
								   "Address:" + address +"\n" + 
								   "Email:" + email + "\n");
			}
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
		
		
}