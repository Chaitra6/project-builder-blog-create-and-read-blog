package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.time.format.DateTimeFormatter;
import java.util.List;

import utility.ConnectionManager;
import model.Blog;


public class BlogDaoImpl implements BlogDaoInterface{

	@Override
	public void insertBlog(Blog blog) throws SQLException, Exception {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		int blogid = blog.getBlogId();
		String blog_title = blog.getBlogTitle();
		String blogDesc = blog.getBlogDescription();
		LocalDate postedOn = blog.getpostedOn();
		
		
		ConnectionManager cm = new ConnectionManager();
		
		//insert all details into DB		
		String sql = "insert into BLOG(BLOG_ID, BLOG_TITLE, BLOG_DESCRIPTION, BLOG_POSTED)VALUES(?,?,?,?)";
		
		//CREATE STATEMENT OBJECT
		PreparedStatement st = cm.getConnection().prepareStatement(sql);		
		
		st.setInt(1, blogid);
		st.setString(2, blog_title);
		st.setString(3, blogDesc);
		st.setDate(4, java.sql.Date.valueOf(postedOn));
		
		int execute = st.executeUpdate();
		if(execute >0 ) {
			System.out.println("Executed");
		}
		else {
			System.out.println("NotExeccuted");
		}
		cm.getConnection().close();
		
	}

	@Override
	public List<Blog> selectAllBlogs() throws SQLException, Exception {
		
		List<Blog> list = new ArrayList<Blog>();
		
		ConnectionManager con = new ConnectionManager();
		Statement st = con.getConnection().createStatement();
		Blog blog = new Blog();
		
		ResultSet rs = st.executeQuery("SELECT * FROM BLOG");
		
		while(rs.next()) {
			LocalDate postedOn = LocalDate.parse((CharSequence)rs.getDate("BLOG_POSTED"));
			
			blog.setBlogId(rs.getInt("BLOG_ID"));
			blog.setBlogTitle(rs.getString("BLOG_TITLE"));
			blog.setBlogDescription(rs.getString("BLOG_DESCRIPTION"));			
			blog.setpostedOn(postedOn);
			
			list.add(blog);
		}
		return list;
		
	}
	
}
