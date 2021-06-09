package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.model.dto.GroupInfoDTO;
import ua.com.alevel.model.dto.LessonInfoDTO;
import ua.com.alevel.model.entity.*;
import ua.com.alevel.service.JPAServiceImpl.JPAStudentServiceImpl;
import ua.com.alevel.service.JPAServiceImpl.JPATeacherServiceImpl;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.util.HibernateUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class JpaDemo {
    private static final StudentService studentService = new JPAStudentServiceImpl();
    private static final  TeacherService teacherService = new JPATeacherServiceImpl();


    public static void main(String[] args){

        try {
            LessonInfoDTO lesson = studentService.getLessonInfoByStudentId(1L);
            List<GroupInfoDTO> group = teacherService.getGroupInfoByTeacherId(1L);
            System.out.println("\n------------------------------------------------------");
            System.out.println("Find next lesson by student id:");
            System.out.println(lesson);
            System.out.println("------------------------------------------------------");
            System.out.println("\nFind best group by teacher id:");
            System.out.println(group);
            System.out.println("------------------------------------------------------\n");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        HibernateUtil.shutdown();
    }


    private static void initDB(){

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();

                Teacher teacher = new Teacher("first teacher");
                Course course = new Course("java");
                Group group = new Group("nix-4", course, teacher);
                Group group1 = new Group("nix-5", course, teacher);

                Student student = new Student("first student", group);
                Student student1 = new Student("second student", group);
                Student student2 = new Student("third student", group1);
                Student student3 = new Student("fourth student", group1);


                Topic topic = new Topic("hibernate", course);
                Lesson lesson = new Lesson(LocalDate.of(2021, 6, 6), LocalTime.of(11, 0), Lesson.Type.LESSON, topic);
                Lesson lesson1 = new Lesson(LocalDate.of(2021, 6, 7), LocalTime.of(11, 0), Lesson.Type.LESSON, topic);


                Topic topic1 = new Topic("final lesson", course);
                Lesson finalLesson = new Lesson(LocalDate.of(2021, 5, 30), LocalTime.of(11, 0), Lesson.Type.EXAM, topic1);


                Mark mark = new Mark(12, student, finalLesson);
                Mark mark1 = new Mark(10, student1, finalLesson);
                Mark mark2 = new Mark(8, student2, finalLesson);
                Mark mark3 = new Mark(9, student3, finalLesson);

                session.persist(teacher);
                session.persist(course);
                session.persist(group);
                session.persist(student);
                session.persist(topic);
                session.persist(lesson);
                session.persist(lesson1);

                session.persist(group1);
                session.persist(student1);
                session.persist(student2);
                session.persist(student3);
                session.persist(topic1);
                session.persist(finalLesson);
                session.persist(mark);
                session.persist(mark1);
                session.persist(mark2);
                session.persist(mark3);

                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }

    }
}
