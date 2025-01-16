package impl;

import db.DB;
import db.DbException;
import entities.Department;
import entities.Seller;
import model.dao.SellerDao;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    public Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select seller.*,department.Name as DepName from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "where seller.Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            if(rs.next()) {
                Department dep = instatiantionDepartment(rs);
                Seller obj = instatiantionSeller(rs,dep);
                return obj;
            }
            return null;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    public Department instatiantionDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    public Seller instatiantionSeller(ResultSet rs,Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY Name");
            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();
            rs = st.executeQuery();
            while(rs.next()) {
                int idDepartment = rs.getInt("DepartmentId");

                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null) {
                    dep = instatiantionDepartment(rs);
                    map.put(idDepartment,dep);
                }
                Seller obj = instatiantionSeller(rs,dep);
                list.add(obj);
            }
            return list;

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
    public List<Seller> findByDepartment(Department department){
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("select seller.*,department.Name as DepName from seller inner join department " +
                    "on seller.DepartmentId = department.Id " +
                    "where DepartmentId = ?");
            st.setInt(1, department.getId());
            List<Seller> list = new ArrayList<>();
            Map<Integer,Department> map = new HashMap<>();
            rs = st.executeQuery();
            while(rs.next()) {
                int idDepartment = rs.getInt("DepartmentId");

                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null) {
                    dep = instatiantionDepartment(rs);
                    map.put(idDepartment,dep);
                }
                Seller obj = instatiantionSeller(rs,dep);
                list.add(obj);
            }
            return list;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
