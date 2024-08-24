package com.model;

import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.util.HBUtil;

public class MyMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Session session = HBUtil.sf.openSession();
        Transaction tx = session.beginTransaction();


        /* //01: Insertion data in the Database 
        for(int i=1;i<=4500;i++){
            Student s1 = new Student();
            s1.setSname(generateRandomName());
            s1.setScity(generateRandomCity());
            s1.setSpercentage(generateRandomPercentage());
            session.save(s1);

            // Flush and clear the session periodically to manage memory usage
            if (i % 50 == 0) {
                session.flush();
                session.clear();
            }
        }
        */


        /* //02: delete from Student where sid=? 
        Student deleteStudentInfo = session.get(Student.class, 4500);
        session.delete(deleteStudentInfo);
        */


        /* //03: update Student set scity=?, sname=?, spercentage=? where sid=?
        Student updateStudent = session.get(Student.class, 6);
        updateStudent.setSname("Harry Potter");
        updateStudent.setScity("London");
        session.update(updateStudent);
        */


        /* //04: select * from table_name where sid=?
        Student findStudentById = session.get(Student.class, 3);
        System.out.println(findStudentById);
        */


        /* //05: select * from Student where spercentage=?
        List<Student> list = session.createQuery("from Student where spercentage= :per",Student.class)
                            .setParameter("per", 100.0).list();
        for(Student s: list)
            System.out.println(s);
        */


        /* //06: Find Student by name 
        List<Student> list =session.createQuery("from Student where sname like :name",Student.class)
                            .setParameter("name", "%dd%").list();
        for(Student s:list)
            System.out.println(s);
        */


        /* //07: Display first 10 Student
        List<Student> list = session.createQuery("from Student",Student.class).setMaxResults(10).list();
        for(Student s:list)
            System.out.println(s);
        */


        /* //08: Skip first 20 students and display next 10 students 
        List<Student> list = session.createQuery("from Student",Student.class)
                            .setFirstResult(20).setMaxResults(10).list();
        for(Student s: list)
            System.out.println(s);
        */


        /* //09: Pagination 
        paginationByPage(session);
        */


        /* //10: PaginationBYPage Studen tName on Specific Space 
        List<Student> list = paginationByPageOnPerticularPageNumber(session, 30);
        for(Student s: list)
            System.out.println(s);
        */





        //HCQL (Hibernate Criteria Query Language)
        //Criteria through Solving
        

        /* //11: Display All Students Data 
        //By Old Method
        List<Student> list = session.createQuery("from Student",Student.class).setReadOnly(true).list();
        for(Student s: list)
            System.out.println(s);


        //By New Criteria Methods
        List<Student> list2 = session.createCriteria(Student.class).list();
        for(Student s: list2)
            System.out.println(s);

            //OR
        
        Criteria c  = session.createCriteria(Student.class); List<Student> l = c.list(); for(Student s: l){System.out.println(s);}
        */



        /* //12: display student by Scity=? and sname=?
        Criteria c = session.createCriteria(Student.class);
        c.add(Restrictions.eq("sid",45));
        Student s1 =(Student) c.uniqueResult();
        System.out.println(s1);
        //OR
        Student s2 = (Student) session.createCriteria(Student.class)
                                .add(Restrictions.and(Restrictions.eq("scity","Pune"))
                                .add(Restrictions.eq("sname", "Avery"))).uniqueResult();
        System.out.println(s2);
        */


        /* //13:  display student by scity=? or sname=?
        List<Student> s1 = session.createCriteria(Student.class)
                            .add(Restrictions.or(Restrictions.eq("scity","Pune"))
                            .add(Restrictions.eq("sname", "John")))
                            .list();
        for (Student student : s1) {
            System.out.println(student);
        }
        */
        
        
        /* //14: display Student by sid less than=? 
        List<Student> l = session.createCriteria(Student.class)
                            .add(Restrictions.lt("sid", 21)).list();
        for(Student s:l){System.out.println(s);} 
        */


        /* //15: not less than */
        List<Student> l = session.createCriteria(Student.class)
                            .add(Restrictions.not(Restrictions.lt("spercentage",99.90))).list();
        for (Student student : l) {System.out.println(student);}

        tx.commit();
        session.clear();
    }
    private static void paginationByPage(Session session){
        int size = 20;
        int page = 1;
        while (true) {
            List<Student> list = session.createQuery("from Student",Student.class)
                                .setFirstResult((page-1)*size)
                                .setMaxResults(size)
                                .list();
            if (!list.isEmpty()) {
                System.out.println("Page Number: "+page);
                for(Student s : list)
                    System.out.println(s);
                System.out.println("----------------------------------------------------------------------");
                page++; //make HBUtil showSql=false
            }
        }
    }
    private static List<Student> paginationByPageOnPerticularPageNumber(Session sesion, int page){
        int size = 20;
        return sesion.createQuery("from Student",Student.class)
        .setFirstResult((page-1)*size).setMaxResults(size).list();
    }
    private static String generateRandomName() {
        String[] names = {
            "John", "Jane", "Alex", "Chris", "Katie", "Amit", "Patil", "Sam", "Taylor", "Jordan",
            "Charlie", "Morgan", "Cameron", "Casey", "Riley", "Sydney", "Dakota", "Bailey", "Skyler", "Jesse",
            "Addison", "Emerson", "Avery", "Blake", "Drew", "Reese", "Quinn", "Harper", "Payton", "Finley",
            "Sawyer", "Sasha", "Phoenix", "Rowan", "Peyton", "Logan", "Kai", "River", "Hayden", "Elliot",
            "Dakota", "Skylar", "Taylor", "Jordan", "Cameron", "Morgan", "Jaden", "Avery", "Brooklyn", "Hunter",
            "Rory", "Shiloh", "Tatum", "Reese", "Riley", "Casey", "Blake", "Quinn", "Parker", "Spencer",
            "Remy", "Alexis", "Mackenzie", "Jamie", "Rowan", "Emerson", "Finley", "Addison", "Kennedy", "Aiden",
            "Aubrey", "Elliott", "Harley", "Sage", "Blaine", "Dylan", "Jordan", "Skyler", "River", "Phoenix",
            "Riley", "Taylor", "Morgan", "Cameron", "Sydney", "Jesse", "Avery", "Hunter", "Payton", "Quinn"
        };
        Random rand = new Random();
        return names[rand.nextInt(names.length)];
    }

    private static String generateRandomCity() {
        String[] cities = {
            "Pune", "Mumbai", "Delhi", "Bangalore", "Chennai", "Hyderabad", "Kolkata", "Ahmedabad", "Surat", "Jaipur",
            "Lucknow", "Kanpur", "Nagpur", "Indore", "Thane", "Bhopal", "Visakhapatnam", "Pimpri-Chinchwad", "Patna", "Vadodara"
        };
        Random rand = new Random();
        return cities[rand.nextInt(cities.length)];
    }

    private static double generateRandomPercentage() {
        Random rand = new Random();
        double percentage =  50 + (rand.nextDouble() * 50); // Generates a percentage between 50 and 100
        return Math.round(percentage * 100.0) / 100.0;
    }
}