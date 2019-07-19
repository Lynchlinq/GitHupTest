
import com.bosssoft.hr.train.chp2.pojo.entity.User;
import com.bosssoft.hr.train.chp2.util.db.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

	@Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from User";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

	@Override
    public void add(User bean) {

        String sql = "insert into user values(null ,? ,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

	
	@Override
    public void update(User bean) {
		String sql = "update user set password=? where id=?";
		try (Connection c = DBUtil.getConnection(); 
	        	PreparedStatement ps = c.prepareStatement(sql)) {
	        	ps.setString(1,bean.getPassword());
	        	ps.setInt(2, bean.getId());
	        	ps.execute();
	        	 	               
	            }
		catch (SQLException e) {

            e.printStackTrace();
        }
    }


	@Override
    public void delete(int id) {
		User bean=new User();	  
		String sql = "delete  from user where id=?";
		   try (Connection c = DBUtil.getConnection(); 
		        	PreparedStatement ps = c.prepareStatement(sql)) {
		        	ps.setInt(1, id);
		        	 ps.execute();	               
		            }
		          	           
		        catch (SQLException e) {

		            e.printStackTrace();
		        }
		     
    }
	
	@Override
    public User get(int id) {
	    User bean=new User();
	  
		String sql = "select * from user where id=?";
        try (Connection c = DBUtil.getConnection(); 
        	PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setInt(1, id);
            ResultSet rs =  ps.executeQuery();
            if(rs.next()) {
            	bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                return bean;
            }
          
           
        } catch (SQLException e) {

            e.printStackTrace();
        }
       
        return null;
    }

	
	
	@Override
    public List<User> list() {

		List<User> beans=new ArrayList<User>();
		
		String sql = "select * from user ";
	     try (Connection c = DBUtil.getConnection(); 
	         	PreparedStatement ps = c.prepareStatement(sql)) {
	             ResultSet rs =  ps.executeQuery();
	             if(rs.next()) {
	            	 User bean=new User();
	             	  bean.setId(rs.getInt(1));
	                 bean.setName(rs.getString(2));
	                 bean.setPassword(rs.getString(3));
	                  beans.add(bean);
	            	 
	             }
	           
	             return beans;
	         } catch (SQLException e) {

	             e.printStackTrace();
	         }
       return null;

    }

	@Override
    public List<User> list(int start, int count) {
		List<User> beans=new ArrayList<User>();
		String sql = "select * from user ";
	     try (Connection c = DBUtil.getConnection(); 
	         	PreparedStatement ps = c.prepareStatement(sql)) {
	             ResultSet rs =  ps.executeQuery();
	             while(rs.next()) {
	            	 if(rs.getInt(1)<(start+count)&&rs.getInt(1)>start) {
	            		 User bean=new User();
		             	  bean.setId(rs.getInt(1));
		                 bean.setName(rs.getString(2));
		                 bean.setPassword(rs.getString(3));
		                  beans.add(bean);	            	  
	            	 }
	             	
	            	 
	             }
	           
	             return beans;
	         } catch (SQLException e) {

	             e.printStackTrace();
	         }
       return null;
    }

	@Override
    public boolean isExist(String name) {
        User user = get(name);
        return user != null;

    }

	@Override
    public User get(String name) {
		   User bean=new User();
			  
			String sql = "select * from user where name=?";
	        try (Connection c = DBUtil.getConnection(); 
	        	PreparedStatement ps = c.prepareStatement(sql)) {
	        	ps.setString(1, name);
	            ResultSet rs =  ps.executeQuery();
	            if(rs.next()) {
	            	bean.setId(rs.getInt(1));
	                bean.setName(rs.getString(2));
	                bean.setPassword(rs.getString(3));
	                return bean;
	            }
	          
	           
	        } catch (SQLException e) {

	            e.printStackTrace();
	        }
        return null;
    }

    public User get(String name, String password) {
    	   User bean=new User();
    		  
   		String sql = "select * from user where name=? and password=?";
           try (Connection c = DBUtil.getConnection(); 
           	PreparedStatement ps = c.prepareStatement(sql)) {
           	ps.setString(1, name);
          	ps.setString(2, password);
               ResultSet rs =  ps.executeQuery();
               if(rs.next()) {
                	bean.setId(rs.getInt(1));
                   bean.setName(rs.getString(2));
                   bean.setPassword(rs.getString(3));
                   return bean;
               }
             
              
           } catch (SQLException e) {

               e.printStackTrace();
           }
       return null;
    }
        
}