package ua.com.alevel.service;

import ua.com.alevel.model.dto.LessonInfoDTO;

import java.sql.SQLException;

public interface StudentService {
    LessonInfoDTO getLessonInfoByStudentId(long id) throws SQLException;

}
