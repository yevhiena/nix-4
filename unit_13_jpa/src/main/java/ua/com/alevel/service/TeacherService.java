package ua.com.alevel.service;

import ua.com.alevel.model.dto.GroupInfoDTO;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {
    List<GroupInfoDTO> getGroupInfoByTeacherId(long id) throws SQLException;
}
