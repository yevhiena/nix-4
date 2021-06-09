package ua.com.alevel.service.JPAServiceImpl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.com.alevel.model.dto.LessonInfoDTO;
import ua.com.alevel.model.entity.Course;
import ua.com.alevel.model.entity.Lesson;
import ua.com.alevel.model.entity.Teacher;
import ua.com.alevel.model.entity.Topic;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.HibernateUtil;

import java.sql.SQLException;

public class JPAStudentServiceImpl implements StudentService {
    private final Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public LessonInfoDTO getLessonInfoByStudentId(long id) throws SQLException {

            try {
                session.beginTransaction();
                Course course = getCourseByStudentId(id);
                Lesson lesson = session.createQuery("from Lesson l where l.topic.course.id = :id order by l.date, l.time asc ", Lesson.class)
                        .setParameter("id", course.getId()).setMaxResults(1).getSingleResult();

                Teacher teacher = getTeacherByStudentId(id);
                Topic topic = session.find(Topic.class, lesson.getTopic().getId());
                session.getTransaction().commit();
                session.close();
                return new LessonInfoDTO(lesson.getId(), lesson.getDate(), lesson.getTime(), topic.getTitle(), teacher.getName(), lesson.getType().name());
            } catch (Exception e){
                session.getTransaction().rollback();
                session.close();
                throw new SQLException("Data layer exception");
            }
    }

    private Course getCourseByStudentId(long id) throws SQLException{
            try {
                Query<Course> findCourse =  session.createQuery("select course from Group g where g.id = " +
                        "(select group.id from Student s where s.id = :studentId)", Course.class);
                findCourse.setParameter("studentId", id);
                return findCourse.getSingleResult();
            }catch (Exception e){
                throw new SQLException("Data layer exception");
            }
    }

    private Teacher getTeacherByStudentId(long id) throws SQLException{
            try {
                Query<Teacher> findTeacher =  session.createQuery("select teacher from Group g where g.id = " +
                        "(select group.id from Student s where s.id = :studentId)", Teacher.class);
                findTeacher.setParameter("studentId", id);
                return findTeacher.getSingleResult();
            }catch (Exception e){
                throw new SQLException("Data layer exception");
            }

    }

}
