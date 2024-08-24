package com.util;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.Student;

public class HBUtil {
    public static SessionFactory sf = new Configuration().setProperties(p1()).addAnnotatedClass(Student.class).buildSessionFactory();
    private static Properties p1(){
        Properties p = new Properties();
        p.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        p.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/jap67");
        p.setProperty("hibernate.connection.username", "root");
        p.setProperty("hibernate.connection.password", "root");
        p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        p.setProperty("hibernate.hm2ddl.auto", "update");
        p.setProperty("hibernate.show_sql", "false");
        return p;

    }
}
