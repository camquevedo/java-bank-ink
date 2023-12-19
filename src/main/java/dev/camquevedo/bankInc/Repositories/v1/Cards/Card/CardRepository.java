package dev.camquevedo.bankInc.Repositories.v1.Cards.Card;

import dev.camquevedo.bankInc.Models.v1.Cards.Card;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Card.Interfaces.Card2RepositoryInterface;
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
public class CardRepository implements Card2RepositoryInterface {

    private final RowMapper<Card> mapper = new CardRowMapper();
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertIntoCards;

    protected String table;
    protected Integer defaultPerPage;

    public CardRepository(
            NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate,
            DataSource crudDataSource
    ) {
        jdbcTemplate = crudNamedParameterJdbcTemplate;
        table = "cards";
        defaultPerPage = 10;
        insertIntoCards = new SimpleJdbcInsert(crudDataSource)
                .withTableName(table)
                .usingGeneratedKeyColumns("id");
    }

    public List<Card> findAll() {
        String sql = "select * from "  + table;
        return jdbcTemplate.query(sql, mapper);
    }

    public List<Card> findById(long id) {
        String sql = "select * from " + table;
        sql += " where " + table + ".id = " + id;
        return jdbcTemplate.query(sql, mapper);
    }
    public List<Card> findByNumber(long number) {
            String sql = "select * from " + table;
            sql += " WHERE " + table + ".number = " + number;
            return jdbcTemplate.query(sql, mapper);
        }

    public List<Card> updateByNumber(long number) {
        String sql = "UPDATE " + table
                + " SET " + table + ".status = " + number
                + " WHERE " + table + ".number = " + number;
        return jdbcTemplate.query(sql, mapper);
    }

    public long create(Card newCard) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("id", newCard.id);
        parameters.put("status", newCard.status);
        parameters.put("product_id", newCard.productId);
        parameters.put("number", newCard.number);
        parameters.put("balance", newCard.balance);
        parameters.put("type", newCard.type);
        parameters.put("first_name", newCard.firstName);
        parameters.put("last_name", newCard.lastName);
        parameters.put("expiration_date", newCard.expirationDate);
        parameters.put("created_at", newCard.getCreatedAt());
        parameters.put("updated_at", newCard.getUpdatedAt());
        parameters.put("deleted_at", newCard.getDeletedAt());

        Number newId = insertIntoCards.executeAndReturnKey(parameters);
        return newId.longValue();

        /*
        return insertIntoCards.executeAndReturnKey(
                new MapSqlParameterSource(
                "status", newCard.status
                )
        ).longValue();
        */
    }

    private static class CardRowMapper implements RowMapper<Card> {

        @Override
        public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
            var card = new Card();
            card.id = rs.getLong("id");
            card.status = rs.getLong("status");
            card.productId = rs.getLong("product_id");
            card.number = rs.getLong("number");
            card.balance = rs.getLong("balance");
            card.type = rs.getBoolean("type");
            card.firstName = rs.getString("first_name");
            card.lastName = rs.getString("last_name");
            card.expirationDate = rs.getDate("expiration_date").toLocalDate();

            card.setCreatedAt(
                    rs.getTimestamp("created_at") == null
                            ? null : rs.getTimestamp("created_at").toLocalDateTime()
            );
            card.setUpdatedAt(
                    rs.getTimestamp("updated_at") == null
                            ? null : rs.getTimestamp("updated_at").toLocalDateTime()
            );
            card.setDeletedAt(
                    rs.getTimestamp("deleted_at") == null
                            ? null : rs.getTimestamp("deleted_at").toLocalDateTime()
            );
            return card;
        }
    }
}
