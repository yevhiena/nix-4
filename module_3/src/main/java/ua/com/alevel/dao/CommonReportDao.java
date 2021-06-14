package ua.com.alevel.dao;

import ua.com.alevel.model.dto.ReportDto;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public interface CommonReportDao extends UserDao, AccountDao {
    List<ReportDto> findAllOperationsByAccountNumber(long number, Instant start, Instant end) throws SQLException;
}
