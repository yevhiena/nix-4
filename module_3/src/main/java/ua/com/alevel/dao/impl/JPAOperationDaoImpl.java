package ua.com.alevel.dao.impl;

import org.hibernate.Session;
import ua.com.alevel.dao.CommonOperationDao;
import ua.com.alevel.model.dto.AccountDto;
import ua.com.alevel.model.entity.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class JPAOperationDaoImpl implements CommonOperationDao {
    private final Session session;

    public JPAOperationDaoImpl(Session session) {
        this.session = session;
    }


    @Override
    public List<Category> findAllCategories() throws SQLException {
        try {
            session.beginTransaction();

            List<Category> categories = session.createQuery("from Category", Category.class).list();

            session.getTransaction().commit();
            return categories;
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new SQLException("Data layer exception");
        }
    }

    @Override
    public void createOperation(Operation operation) throws SQLException {
        try {
            session.beginTransaction();
            session.persist(operation);

            if(operation.getResult().getStatus().equals(TransferResult.Status.ACCEPTED)){
                BigDecimal balanceUpdated = operation.getAccount().getBalance().add(operation.getTransferAmount());
                operation.getAccount().setBalance(balanceUpdated);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new SQLException("Data layer exception");
        }
    }


    @Override
    public Account findAccountByNumber(long number) throws SQLException {
        try {
            session.beginTransaction();

            Account account = session.createQuery("from Account a where a.accountNumber = :number", Account.class)
                    .setParameter("number", number)
                    .getSingleResult();

            session.getTransaction().commit();
            return account;
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new SQLException(e.getCause());
        }
    }

    @Override
    public List<AccountDto> findAccountByUserId(long id) throws SQLException {
        try {
            session.beginTransaction();

            List<AccountDto> accountDto = session.createQuery("select new ua.com.alevel.model.dto.AccountDto(" +
                    "a.accountNumber, a.balance)" +
                    " from Account a where a.user.id= :id", AccountDto.class)
                    .setParameter("id", id).list();

            session.getTransaction().commit();
            return accountDto;
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new SQLException(e.getCause());
        }
    }

    @Override
    public long getUserIdByEmail(String email) throws SQLException {
        try {
            session.beginTransaction();

            User user = session.createQuery("from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            session.getTransaction().commit();
            return user.getId();
        }catch (Exception e){
            session.getTransaction().rollback();
            throw new SQLException(e.getCause());
        }
    }
}
