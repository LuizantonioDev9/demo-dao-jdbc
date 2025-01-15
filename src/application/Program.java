package application;

import entities.Department;
import entities.Seller;
import impl.SellerDaoJDBC;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: seller findById ====");
        Seller seller = sellerDao.findById(3);

        System.out.println("=== TEST 2: seller findByDepartment====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for (Seller obj : list) {
            System.out.println(obj);
        }
        System.out.println(seller);
    }
}
