package model.dao;

import db.DB;
import entities.Seller;
import impl.SellerDaoJDBC;

public class DaoFactory {//classe que vai fazer a conexão com o driver do BD
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
