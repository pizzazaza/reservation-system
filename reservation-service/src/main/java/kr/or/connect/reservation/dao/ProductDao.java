package kr.or.connect.reservation.dao;

import kr.or.connect.reservation.domain.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.or.connect.reservation.dao.sql.ProductSqls.*;
/**
 * Created by ODOL on 2017. 7. 12..
 */
@Repository
public class ProductDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

    public ProductDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Product> selectAll() {
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    public List<Product> selectByCategoryId(Long categoryId) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("category_id", categoryId);
        return jdbcTemplate.query(SELECT_BY_CATEGORY_ID, paramsMap, rowMapper);
    }
}
