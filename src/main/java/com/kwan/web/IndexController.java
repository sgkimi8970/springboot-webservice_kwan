package com.kwan.web;

import com.kwan.config.auth.LoginUser;
import com.kwan.config.auth.SessionUser;
import com.kwan.domain.post.Posts;
import com.kwan.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser sessionUser){
        model.addAttribute("posts", postsService.selectListDesc());
        if(sessionUser!=null){
            model.addAttribute("userName", sessionUser.getName());
        }
        return "index";
    }

    @GetMapping("/posts/list")
    public String selectList(Model model){
        model.addAttribute("posts", postsService.selectListSort());
        return "index";
    }

    @GetMapping("/posts/save")
    public String save(){
        return "posts-save";   // posts-save.mustache 화면 표시
    }

    @GetMapping("/posts/update/{id}")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("post", postsService.select(id));
        return "posts-edit";   // posts-save.mustache 화면 표시
    }
}
