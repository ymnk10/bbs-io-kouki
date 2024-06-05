package com.example.controller;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @GetMapping("")
  public String index(Model model) {
    List<Article> articles = articleRepository.findAll();
    model.addAttribute("articles", articles);
    return "index";
  }
}
