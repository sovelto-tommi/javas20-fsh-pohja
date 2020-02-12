package fi.academy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ColorDAO {
    private JdbcTemplate jdbc;
    private static final Logger log = LoggerFactory.getLogger(ColorDAO.class);

    @Autowired
    public ColorDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Color> fetchAllColors() {
        List<Color> list = jdbc.query("SELECT * FROM color", rowMapper);
        return list;
    }
    public Optional<Color> findById(int id) {
        try {
            Color c = jdbc.queryForObject("SELECT * FROM color WHERE id=?", new Object[]{id}, rowMapper);
            return Optional.of(c);
        } catch (EmptyResultDataAccessException e) {
            log.error("Could not find a color with id " + id);
        }
        return Optional.empty();
    }
    public Optional<Color> findByName(String name) {
        try {
            Color c = jdbc.queryForObject("SELECT * FROM color WHERE name=?", new Object[]{name}, rowMapper);
            return Optional.of(c);
        } catch (EmptyResultDataAccessException e) {
            log.error("Could not find a color with name " + name);
        }
        return Optional.empty();
    }
    public Color addColor(Color c) {
        String sql = "INSERT INTO color(name, hex, rgb, custom) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getName());
            ps.setString(2, c.getHex());
            ps.setArray(3, jdbc.getDataSource().getConnection()
                    .createArrayOf("smallint", Arrays.stream(c.getRgb()).mapToObj(Integer::valueOf).toArray()));
            ps.setBoolean(4, c.isCustom());
            return ps;
        }, keyHolder);
        var keys = keyHolder.getKeys();
        int id = (int)keys.get("id");
        c.setId(id);
        return c;
    }

    public void deleteColor(int id) {
        jdbc.update("DELETE FROM color WHERE id = ?", id );
    }

    private RowMapper<Color> rowMapper = (rs, i) -> {
        Color c = new Color();
        c.setId(rs.getInt("id"));
        c.setName(rs.getString("name"));
        c.setHex(rs.getString("hex"));
        c.setName(rs.getString("name"));
        Short[] rgbArrShort = (Short[]) rs.getArray("rgb").getArray();
        int[] rgbArrint = Arrays.stream(rgbArrShort).mapToInt(n->n).toArray();
        c.setRgb(rgbArrint);
        c.setCustom(rs.getBoolean("custom"));
        return c;
    };
}


