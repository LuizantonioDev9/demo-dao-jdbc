package application;

import entities.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao depDao = DaoFactory.createDepartmentJDBC();
        Scanner sc = new Scanner(System.in);

        System.out.println("==== TEST 1: FindById ====");
        System.out.println("Procure por ID:");
        System.out.println("Digite o id: ");
        Department dep = depDao.findById(sc.nextInt());
        System.out.println(dep);

        System.out.println("\n==== TEST 2: FindAll ====");
        List<Department> list = depDao.findAll();
        for(Department obj : list) {
            System.out.println(obj);
        }

//        System.out.println("\n==== TEST 3: Insert ====");
//        Department newDep = new Department(null,"Singer");
//        depDao.insert(newDep);
//        System.out.println("Inserted");
//
//        System.out.println("\n==== TEST 4: Update ====");
//        newDep = depDao.findById(6);
//        newDep.setName("Jogadores");
//        depDao.update(newDep);
//        System.out.println("UPDATED");

        System.out.println("\n==== TEST 5: Delete ====");
        System.out.println("DELETE ID: ");
        depDao.deleteById(sc.nextInt());
        System.out.println("DELETED");

        sc.close();

    }
}
