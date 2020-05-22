package spring;
import java.sql.*;

public class sqlTest {
	
	
	public static void main(String[] args) {
		Connection con = null;
		String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password = "Banzaipct15";
		String query = "select * from ski_resorts";
		 ResultSet rs = null;
		 Statement st = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url, user, password);
			 System.out.println("Connected");
			 st = con.createStatement();
			  rs = st.executeQuery(query);
			
			 while( rs.next()){
				 String resort = rs.getString("name");
				 System.out.println(resort);
			 }
		}catch(SQLException ex){
			   System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {}
				rs = null;
			}
			
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {}
				st = null;
			}
		}
	}

}
