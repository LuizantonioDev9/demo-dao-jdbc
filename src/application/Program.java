package application;

import entities.Department;
import entities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDaoJDBC();

        System.out.println("==== TESTE 1: FindById =====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n==== TESTE 2: FindByDepartment =====");
        Department dep = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(dep);
        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n==== TESTE 3: FindAll =====");
        list = sellerDao.findAll();
        for(Seller obj : list) {
            System.out.println(obj);
        }

//        System.out.println("\n==== TESTE 4: Insert =====");
//        Seller newSeller = new Seller(null,"Michael Jackson","mick@gmail",new Date(),1234.0,dep);
//        sellerDao.insert(newSeller);
//        System.out.println("INSERIDO COM SUCESSO");
//
//        System.out.println("\n==== TESTE 5: Update =====");
//        newSeller = sellerDao.findById(28);
//        newSeller.setName("Mick Jagger");
//        sellerDao.update(newSeller);
//        System.out.println("UPDATE COMPLETED");

        System.out.println("\n==== TESTE 6: Delete =====");
        sellerDao.deleteById(28);
        System.out.println("Deleted");
    }
}
