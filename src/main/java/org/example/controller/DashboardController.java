package org.example.controller;

import org.example.model.Board;
import org.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public String getDeshboard() {

        return "forward:/board.html";
    }

    @GetMapping("/write")
    public String getWrite(){
        return "forward:/write.html";
    }

    @GetMapping("details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" +id));
        model.addAttribute("board", board);
        return "boardDetails";
    }

    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board Id: " + id));
        model.addAttribute("board", board);
        return "editBoard"; // Thymeleaf 템플릿의 이름을 반환
    }


}
