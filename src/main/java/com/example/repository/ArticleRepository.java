package com.example.repository;

import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ArticleRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

    public List<Article> findAll(){
        String sql = "SELECT (id, name, content) FROM articles ORDER BY id DESC;";
        List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
        return articleList;
    }

    public void insert(Article article){
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        String sql = "INSERT INTO articles(name, content) VALUES(:name, :content);";
        template.update(sql, param);
    }
}
