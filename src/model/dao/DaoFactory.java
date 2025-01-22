package model.dao;

import db.DB;
import entities.Department;
import impl.DepartmentDaoJDBC;
import impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDaoJDBC() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentJDBC() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
