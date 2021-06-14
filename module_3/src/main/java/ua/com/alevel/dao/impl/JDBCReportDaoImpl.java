package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CommonReportDao;
import ua.com.alevel.exception.DataNotFoundException;
import ua.com.alevel.model.dto.AccountDto;
import ua.com.alevel.model.dto.ReportDto;
import ua.com.alevel.model.entity.Account;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class JDBCReportDaoImpl implements CommonReportDao {
    private final Connection connection;
    public JDBCReportDaoImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<ReportDto> findAllOperationsByAccountNumber(long number, Instant start, Instant end) throws SQLException, DataNotFoundException {

        try (PreparedStatement read  = connection.prepareStatement(
                "select * from accounts a where a.number = (?) ")){
                read.setLong(1, number);
                ResultSet rs = read.executeQuery();
                if(!rs.next()) {
                    rs.close();
                    throw new RuntimeException();
                }
                rs.close();
        }catch (RuntimeException e){
            throw new DataNotFoundException("Account not found, number: " + number);
        }

        try (PreparedStatement read  = connection.prepareStatement(
                "select number, amount, name, c.type, issued_at, status, o.reason  " +
                        "from accounts  a join operations_ o on a.id = o.account_id " +
                        "left join operations__categories oc on o.id = oc.operation_id " +
                        "left join categories c on c.id = oc.category_id " +
                        "where a.number = (?) and o.issued_at between (?) and (?) ")) {

            read.setLong(1, number);
            read.setTimestamp(2, Timestamp.from(start));
            read.setTimestamp(3, Timestamp.from(end));
            ResultSet rs = read.executeQuery();

            List<ReportDto> operations = new ArrayList<>();

            while (rs.next()) {
                long accountNumber = rs.getLong("number");
                BigDecimal transferAmount = rs.getBigDecimal("amount");
                String categoryName = rs.getString("name");
                String categoryType = rs.getString("type");
                Instant issuedAt = rs.getTimestamp("issued_at").toInstant();
                String status = rs.getString("status");
                String reason = rs.getString("reason");

                operations.add(new ReportDto(accountNumber, transferAmount, categoryName, categoryType, issuedAt, status, reason));
            }
            rs.close();
            return operations;

        }
    }

    @Override
    public long getUserIdByEmail(String email) throws SQLException, DataNotFoundException {
        try (PreparedStatement read  = connection.prepareStatement(
                "select id from users u where u.email = (?)")) {

                read.setString(1, email);
                ResultSet rs = read.executeQuery();
                if(!rs.next()) {
                    rs.close();
                    throw new RuntimeException();}
            long id = rs.getInt("id");
            rs.close();
            return id;
        }catch (RuntimeException e){
            throw  new  DataNotFoundException("User not found with email: " + email);
        }
    }


    @Override
    public Account findAccountByNumber(long number) throws SQLException {
        return null;
    }

    @Override
    public List<AccountDto> findAccountByUserId(long id) throws SQLException, DataNotFoundException {
        try (PreparedStatement read  = connection.prepareStatement(
                "select a.number, a.balance from accounts a where a.user_id = (?)")) {

            read.setLong(1, id);
            ResultSet rs = read.executeQuery();
            List<AccountDto> accountDto = new ArrayList<>();

            if(!rs.next()) {
                rs.close();
                throw new RuntimeException();}
            else {
               do{
                    long accountNumber = rs.getLong("number");
                    BigDecimal balance= rs.getBigDecimal("balance");
                    accountDto.add(new AccountDto(accountNumber, balance));
                } while (rs.next());
                rs.close();
                return accountDto;
            }

        }catch (RuntimeException e){
            throw  new  DataNotFoundException("Accounts not found for user with id: " + id);
        }

    }
}
