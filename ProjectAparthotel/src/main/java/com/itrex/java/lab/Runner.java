package com.itrex.java.lab;

import com.itrex.java.lab.config.ApplicationContextConfiguration;
import com.itrex.java.lab.entities.Role;
import com.itrex.java.lab.entities.User;
import com.itrex.java.lab.exceptions.RepositoryException;
import com.itrex.java.lab.repositories.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {

        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);
        RoomRepository roomRepository = applicationContext.getBean(RoomRepository.class);
        BookingRepository bookingRepository = applicationContext.getBean(BookingRepository.class);
        PriceRepository priceRepository = applicationContext.getBean(PriceRepository.class);

        workWithHibernate(userRepository, roleRepository, roomRepository, bookingRepository, priceRepository);

        System.out.println("=================SHUT DOWN APP====================");
    }

    public static void workWithHibernate(UserRepository userRepository, RoleRepository roleRepository, RoomRepository roomRepository,
                                         BookingRepository bookingRepository, PriceRepository priceRepository) throws RepositoryException {
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
    }
}
