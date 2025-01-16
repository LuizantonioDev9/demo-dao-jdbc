package application;

import entities.Department;
import entities.Seller;
import impl.SellerDaoJDBC;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDaoJBDC();

        System.out.println("=== TEST 1: seller findById ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println("=== TEST 2: seller findByDepartment ====");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);
        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();
        System.out.println("=== TEST 3: seller findAll ====");
        list = sellerDao.findAll();
        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println();
        System.out.println("=== TEST 4: seller insert ====");
        department = new Department(1,"Cantor");
        seller = new Seller(1,"Alexandre Pires","SPC@gmail.com",new Date(),3000.0,department);
        sellerDao.insert(seller);
    }
}
