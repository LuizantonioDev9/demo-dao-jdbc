package application;

import entities.Department;
import entities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDaoJDBC();

        System.out.println("==== TEST 1: FindById ====");
        System.out.println("Procure pelo ID");
        System.out.println("Digite o id:");
        Seller seller = sellerDao.findById(sc.nextInt());
        System.out.println(seller);

        System.out.println("\n==== TEST 2: FindByDepartment ====");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDeparment(department);
        for(Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n==== TEST 3: Insert ====");
        Seller newSeller = new Seller(null,"Reinaldo Oliveira","rei@gmail.com",new Date(),2332.0,department);
        sellerDao.insert(newSeller);
        System.out.println(newSeller);

        System.out.println("\n==== TEST 4: Update ====");
        seller = sellerDao.findById(31);

        System.out.println("\n==== TEST 5: Delete ====");
        System.out.println("Escolha o id que deseja excluir: ");
        sellerDao.deleteById(sc.nextInt());
        System.out.println("DELETED");
    }
}
