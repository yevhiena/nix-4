package ua.com.alevel.service.JPAServiceImpl;

import org.hibernate.Session;
import ua.com.alevel.model.dto.GroupInfoDTO;
import ua.com.alevel.model.entity.Group;
import ua.com.alevel.model.entity.Lesson;
import ua.com.alevel.model.entity.Mark;
import ua.com.alevel.service.TeacherService;
import ua.com.alevel.util.HibernateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JPATeacherServiceImpl implements TeacherService {

    private final Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public List<GroupInfoDTO> getGroupInfoByTeacherId(long id) throws SQLException{
        try {
            session.beginTransaction();
            List<Group> groups = getGroupsByTeacherId(id);

            List<Group> bestGroups = findGroupsWithMaxMedian(groups);

            List<GroupInfoDTO> bestGroupsDTO = new ArrayList<>();
            for (Group g : bestGroups) {
                bestGroupsDTO.add(new GroupInfoDTO(g.getId(), g.getCode(), g.getCourse().getName()));
            }
            session.getTransaction().commit();
            session.close();
            return bestGroupsDTO;
        } catch (SQLException e){
            session.getTransaction().rollback();
            session.close();
            throw new SQLException("Data layer exception");
        }

    }


    private List<Group>  getGroupsByTeacherId(long id) throws SQLException {
            try {
                List<Group> groups = session.createQuery("from Group g where g.teacher.id = :id", Group.class)
                        .setParameter("id", id)
                        .list();
                return groups;
            } catch (Exception e){
                throw new SQLException("Teacher with id " + id + "doesn't exist");
            }
    }


    private List<Group> findGroupsWithMaxMedian(List<Group> groups) throws SQLException{
        List<Group> bestGroups = new ArrayList<>();
        double maxMedian = 0.0;
        for (Group g :groups) {
            List<Mark> marks = findExamMarksForStudentsInGroup(g);

            double median;
            if (marks.size() % 2 == 1) median = marks.get(marks.size()/2).getValue();
            else median = (double) (marks.get(marks.size()/2).getValue() + marks.get(marks.size()/2 -1).getValue())/2;

            if(maxMedian < median) {
                maxMedian = median;
                bestGroups.clear();
                bestGroups.add(g);
            }
            else if (maxMedian == median) bestGroups.add(g);
        }
        return bestGroups;
    }


    private List<Mark> findExamMarksForStudentsInGroup(Group group) throws SQLException {
            try {
                List<Mark> marks = session.createQuery("from Mark m where m.student.group.id = :id and m.lesson.type = :type order by m.value asc", Mark.class)
                        .setParameter("id", group.getId()).setParameter("type", Lesson.Type.EXAM)
                        .list();
                return marks;
            } catch (Exception e){
                throw new SQLException("Data layer exception");
            }
    }

}
