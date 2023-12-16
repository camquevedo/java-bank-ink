package dev.camquevedo.bankInc.Repositories.v1.Cards;

import dev.camquevedo.bankInc.Models.v1.Product;
import dev.camquevedo.bankInc.Repositories.v1.Cards.Interfaces.ProductRepositoryInterface;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
public class ProductRepository implements ProductRepositoryInterface {

    protected final String table;
    protected Integer defaultPerPage;
    private final RowMapper<Product> mapper = new EntityRowMapper();
    private final NamedParameterJdbcTemplate jdbcTemplate; // namedParameterJdbcTemplate
    private final SimpleJdbcInsert insert;
    public ProductRepository(
            NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate,
            DataSource crudDataSource
    ){
        this.table = "products";
        this.defaultPerPage = 10;
        jdbcTemplate = crudNamedParameterJdbcTemplate;
        insert = new SimpleJdbcInsert(crudDataSource)
                .withTableName(this.table)
                .usingGeneratedKeyColumns("id");
    }

    private static class EntityRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            var product = new Product();
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long number = rs.getLong("number");

            product.id = id;
            product.name = name;
            product.number = number;

            return product;
        }
    }

    public List<Product> findAll() {
        String sql = "select * from " + this.table;
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    public Long save(Product newProduct) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("name", newProduct.getName());
        parameters.put("number", newProduct.getNumber());
        Number newId = insert.executeAndReturnKey(parameters);
        newProduct.setId(newId.longValue());

        return newProduct.getId();
    }

    @Override
    public Boolean update(Integer id, Object entity) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
