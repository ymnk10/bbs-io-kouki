package com.example.repository;

import com.example.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

  public List<Comment> findAllByArticleId(Integer articleId) {
    String sql = "SELECT id, name, content, article_id FROM comments WHERE article_id = :articleId ORDER BY id DESC;";
    SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
    return template.query(sql, param, COMMENT_ROW_MAPPER);
  }
}
