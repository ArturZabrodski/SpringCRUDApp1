package ru.zabrodski.springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zabrodski.springcourse.models.Page;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PageDAO {
    private final JdbcTemplate jdbcTemplate;

    public PageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Page> indexShow() {
        return jdbcTemplate.query("SELECT * FROM Page ORDER BY Priority",
                new BeanPropertyRowMapper<>(Page.class));
    }

    public List<Page> indexHide() {
        return jdbcTemplate.query("SELECT * FROM Page WHERE published_at IS NOT NULL ORDER BY Priority",
                new BeanPropertyRowMapper<>(Page.class));
    }

    public Page show(String slug) {
        return jdbcTemplate.query("SELECT * FROM Page WHERE slug=?", new Object[]{slug},
                new BeanPropertyRowMapper<>(Page.class))
                .stream().findAny().orElse(null);
    }

    public void save(Page page) {
        jdbcTemplate.update("INSERT INTO Page VALUES(?, ?, ?, ?)",
                page.getSlug(),
                page.getTitle(),
                page.getContent(),
                page.getPriority());
    }

    public void update(String slug, Page updatedPage) {
        jdbcTemplate.update("UPDATE Page SET  title=?, slug=?,content=?, priority=? WHERE slug=?",
                updatedPage.getTitle(),
                updatedPage.getSlug(),
                updatedPage.getContent(),
                updatedPage.getPriority(),
                slug);
    }

    public void publish(String slug, Page updatedPage) {
        updatedPage.setPublished_at(LocalDateTime.now());
        jdbcTemplate.update("UPDATE Page SET published_at=? WHERE slug=?",
                updatedPage.getPublished_at(),
                slug);
    }

    public void delete(String slug) {
        jdbcTemplate.update("DELETE FROM Page WHERE slug=?", slug);
    }
}
