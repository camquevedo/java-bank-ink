package dev.camquevedo.bankInc.Repositories.v1.Transactions;

import dev.camquevedo.bankInc.Models.v1.Transactions.Transaction;
import dev.camquevedo.bankInc.Repositories.v1.Transactions.Interfaces.Transaction2RepositoryInterface;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class TransactionRepository implements Transaction2RepositoryInterface {

    private final RowMapper<Transaction> mapper = new TransactionRepository.TransactionRowMapper();
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertIntoTransactions;

    protected String table;
    protected Integer defaultPerPage;

    public TransactionRepository(
            NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate,
            DataSource crudDataSource
    ) {
        jdbcTemplate = crudNamedParameterJdbcTemplate;
        table = "transactions";
        defaultPerPage = 10;
        insertIntoTransactions = new SimpleJdbcInsert(crudDataSource)
                .withTableName(table)
                .usingGeneratedKeyColumns("id");
    }

    public List<Transaction> findAll() {
        String sql = "select * from "  + table;
        return jdbcTemplate.query(sql, mapper);
    }

    public List<Transaction> findById(long id) {
        String sql = "select * from " + table;
        sql += " where " + table + ".id = " + id;
        return jdbcTemplate.query(sql, mapper);
    }
    public List<Transaction> findByNumber(long number) {
        String sql = "select * from " + table;
        sql += " WHERE " + table + ".number = " + number;
        return jdbcTemplate.query(sql, mapper);
    }

    public List<Transaction> updateByNumber(long number) {
        String sql = "UPDATE " + table
                + " SET " + table + ".status = " + number
                + " WHERE " + table + ".number = " + number;
        return jdbcTemplate.query(sql, mapper);
    }

    public long create(Transaction newTransaction) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("id", newTransaction.id);
        parameters.put("status", newTransaction.status);
        parameters.put("type", newTransaction.type);
        parameters.put("balance", newTransaction.balance);

        parameters.put("created_at", newTransaction.getCreatedAt());
        parameters.put("updated_at", newTransaction.getUpdatedAt());
        parameters.put("deleted_at", newTransaction.getDeletedAt());

        Number newId = insertIntoTransactions.executeAndReturnKey(parameters);
        return newId.longValue();
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            var transaction = new Transaction();
            transaction.id = rs.getLong("id");
            transaction.status = rs.getLong("status");
            transaction.type = rs.getBoolean("type");
            transaction.balance = rs.getLong("balance");

            transaction.setCreatedAt(
                    rs.getTimestamp("created_at") == null
                            ? null : rs.getTimestamp("created_at").toLocalDateTime()
            );
            transaction.setUpdatedAt(
                    rs.getTimestamp("updated_at") == null
                            ? null : rs.getTimestamp("updated_at").toLocalDateTime()
            );
            transaction.setDeletedAt(
                    rs.getTimestamp("deleted_at") == null
                            ? null : rs.getTimestamp("deleted_at").toLocalDateTime()
            );
            return transaction;
        }
    }
}
