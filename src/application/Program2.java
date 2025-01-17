package application;

import entities.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("==== TEST 1: FindById ====");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDaoJDBC();
        System.out.println("Digite o id que deseja procurar: ");
        int id = sc.nextInt();
        Department department = departmentDao.findById(id);
        System.out.println(department);

        System.out.println("\n==== TEST 2: FindALL ====");
        List<Department> list = departmentDao.findAll();
        for(Department dep : list) {
            System.out.println(dep);
        }

        System.out.println("\n==== TEST 3: Insert ====");
        Department newDep = new Department(null,"Gamers");
        departmentDao.insert(newDep);
        System.out.println("Done inserted");

        System.out.println("\n==== TEST 5: Update ====");
        newDep = departmentDao.findById(6);
        newDep.setName("Gamers");
        departmentDao.update(newDep);
        System.out.println("Updated");

        System.out.println("\n==== TEST 6: Delete ====");
        System.out.print("ID para deletar: ");
        int idDelete = sc.nextInt();
        departmentDao.deleteById(idDelete);
        System.out.println("Deleted");
    }
}
