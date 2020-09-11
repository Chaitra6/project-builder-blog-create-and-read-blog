package dao;

import java.sql.SQLException;
import java.util.List;

import model.Blog;

interface BlogDaoInterface{

    public void insertBlog(Blog blog) throws SQLException, Exception;
    public List<Blog> selectAllBlogs() throws SQLException, Exception;

}

