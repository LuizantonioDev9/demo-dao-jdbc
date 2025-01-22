package impl;

import db.DB;
import db.DbException;
import entities.Department;
import model.dao.DepartmentDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    public Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("insert into department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,dep.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if(rs.next()) {
                    int id = rs.getInt(1);
                    dep.setId(id);
                }
                else {
                    throw new DbException("No rows affected");
                }
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("Update department set Name = ? where Id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,dep.getName());
            st.setInt(2,dep.getId());

            int rowsAffected = st.executeUpdate();


        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("Delete from department where id = ?");
            st.setInt(1,id);

            int rowsAffected = st.executeUpdate();


        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from department where id = ? order by name");
            st.setInt(1,id);

            rs = st.executeQuery();

            if(rs.next()) {
                Department dep = instatiationDepartment(rs);
                return dep;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from department order by name");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();

            while(rs.next()) {
                Department dep = instatiationDepartment(rs);
                list.add(dep);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Department instatiationDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
