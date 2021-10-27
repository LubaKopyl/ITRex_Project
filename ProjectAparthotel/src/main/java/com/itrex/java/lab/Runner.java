package com.itrex.java.lab;

import com.itrex.java.lab.entities.Price;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.repositories.*;
import com.itrex.java.lab.repositories.impl.hibernate.*;
import com.itrex.java.lab.repositories.impl.jdbc.JDBCRoleRepositoryImpl;
import com.itrex.java.lab.repositories.impl.jdbc.JDBCUserRepositoryImpl;
import com.itrex.java.lab.service.FlywayService;
import com.itrex.java.lab.util.HibernateUtil;
import org.h2.jdbcx.JdbcConnectionPool;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import static com.itrex.java.lab.properties.Properties.*;

public class Runner {

    public static void main(String[] args) {
        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        System.out.println("============CREATE CONNECTION POOL================");
        //JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);
        Session session = HibernateUtil.getSessionFactory().openSession();

        System.out.println("=============CREATE UserRepository and RoleRepository================");

        //UserRepository userRepository = new JDBCUserRepositoryImpl(jdbcConnectionPool);
        //RoleRepository roleRepository = new JDBCRoleRepositoryImpl(jdbcConnectionPool);

        UserRepository userRepository = new HibernateUserRepositoryImpl(session);
        RoleRepository roleRepository = new HibernateRoleRepositoryImpl(session);
        RoomRepository roomRepository = new HibernateRoomRepositoryImpl(session);
        BookingRepository bookingRepository = new HibernateBookingRepositoryImpl(session);
        PriceRepository priceRepository = new HibernatePriceRepositoryImpl(session);

        System.out.println("All existing users: " + userRepository.selectAll());
        System.out.println("All existing roles: " + roleRepository.selectAll());

        System.out.println("All users with the GUEST role: " + userRepository.selectAllUsersByRole("guest"));
        System.out.println("All users with the ADMIN role: " + userRepository.selectAllUsersByRole("admin"));

        System.out.println("User with id = 3: " + userRepository.selectById(3));

        User user4 = new User();
        user4.setFirstName("Anna");
        user4.setLastName("Ivanova");
        user4.setEmail("anna@gmail.com");
        user4.setPassword("12345anna");
        userRepository.add(user4);
        System.out.println(userRepository.selectAll());

        user4.setLastName("Kovaleva");
        userRepository.update(user4);
        System.out.println("Updated user Anna Ivanova: " + user4);

        List<User> newUsers = new ArrayList<>();

        User user5 = new User();
        user5.setFirstName("Sasha");
        user5.setLastName("Sergeev");
        user5.setEmail("sasha@gmail.com");
        user5.setPassword("12345sasha");
        newUsers.add(user5);

        User user6 = new User();
        user6.setFirstName("Pavel");
        user6.setLastName("Pavlov");
        user6.setEmail("pavel@gmail.com");
        user6.setPassword("12345pavel");
        newUsers.add(user6);

        userRepository.addAll(newUsers);
        System.out.println("New users: " + newUsers);
        System.out.println("All users after adding: " + userRepository.selectAll());

        userRepository.delete(5);
        System.out.println("User with id = 5 was deleted.");

        System.out.println("All users after deleting: " + userRepository.selectAll());

        System.out.println("All rooms: " + roomRepository.selectAll());

        System.out.println("All bookings: " + bookingRepository.selectAll());

        System.out.println("All prices: " + priceRepository.selectAll());

        System.out.println("=========CLOSE ALL UNUSED CONNECTIONS=============");
        //jdbcConnectionPool.dispose();
        System.out.println("=================SHUT DOWN APP====================");
    }
}
