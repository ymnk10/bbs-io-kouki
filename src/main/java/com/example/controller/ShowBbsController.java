package com.example.controller;


import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Bbs一覧画面を表示するコントローラー.
 *
 * @author koki.osumi
 */
@Controller
@RequestMapping("/show")
public class ShowBbsController {
  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private CommentRepository commentRepository;

  @GetMapping("")
  public String index(Model model) {
    List<Article> articles = articleRepository.findAll();
    articles.forEach(article -> article.setCommentList(commentRepository.findAllByArticleId(article.getId())));
    model.addAttribute("articles", articles);
    System.out.println(articles);
    return "index";
  }

  @PostMapping("/insert-article")
  public String insertArticle(ArticleForm form, Model model) {
    Article article = new Article();
    BeanUtils.copyProperties(form, article);
    articleRepository.insertArticle(article);
    return "redirect:/show";
  }

  @PostMapping("/insert-comment")
  public String insertComment(CommentForm form, Model model) {
    Comment comment = new Comment();
    BeanUtils.copyProperties(form, comment);
    comment.setArticleId(Integer.valueOf(form.getArticleId()));
    commentRepository.insertComment(comment);
    return "redirect:/show";
  }

  @PostMapping("/delete-article")
  public String deleteArticle(String articleId) {
    articleRepository.deleteByIdWithComments(Integer.parseInt(articleId));
    return "redirect:/show";
  }
}
