package model.dao;

import db.DB;
import impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDao createSellerDaoJDBC() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
