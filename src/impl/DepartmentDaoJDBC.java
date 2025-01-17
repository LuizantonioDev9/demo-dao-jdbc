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
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("update department set Name = ? where id = ?");
            st.setString(1,dep.getName());
            st.setInt(2,dep.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("delete from department where id = ?");
            st.setInt(1,id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select * from department where id = ?");
            st.setInt(1,id);

            rs = st.executeQuery();

            if(rs.next()) {
                Department dep = instantionDepartment(rs);
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
            st = conn.prepareStatement("select * from department order by Name ");
            List<Department> list = new ArrayList<>();
            rs = st.executeQuery();

            while(rs.next()) {
                Department dep = instantionDepartment(rs);
                list.add(dep);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public Department instantionDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));
        return department;
    }
}
