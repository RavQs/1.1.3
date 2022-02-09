package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.dropUsersTable();
        userDao.createUsersTable();

        User user1 = new User("Victor", "Kim", (byte) 34);
        userDao.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        System.out.println(user1.getName() + " Добавлен в базу данных");

        User user2 = new User("Ekaterina", "Melnikova",(byte) 22);
        userDao.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        System.out.println(user2.getName() + " Добавлен в базу данных");

        User user3= new User("유진", "이",(byte) 45);
        userDao.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        System.out.println(user3.getName() + " Добавлен в базу данных");

        User user4 = new User("Alex", "Kim",(byte) 26);
        userDao.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.println(user4.getName() + " Добавлен в базу данных");

        List<User> userList = new ArrayList<>(userDao.getAllUsers());
        for (User user : userList) {
            System.out.println(user);
        }

        userDao.cleanUsersTable();
    }
}