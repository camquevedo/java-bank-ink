package dev.camquevedo.bankink.Repositories.v1.cards;

import dev.camquevedo.bankink.Models.v1.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate; // as namedParameterJdbcTemplate
    private final ProductMapper mapper = new ProductMapper();
    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAll() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, mapper);
    }

    private static class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long number = rs.getLong("number");

            return new Product(id, name, number);
        }
    }
}
