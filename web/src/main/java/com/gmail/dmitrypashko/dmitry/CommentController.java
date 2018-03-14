package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Comment;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private INewsService newsService;


    @RequestMapping(value = "/user/news/{id}", method = RequestMethod.POST)
    public String addComment(HttpServletRequest req, @ModelAttribute("Comment") @Valid Comment comment,
                             BindingResult bindingResult) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        if (bindingResult.hasErrors()) {
            req.setAttribute("detailedNews", newsService.getNews(id));
            req.setAttribute("comments", commentService.getCommentByNews(id));
            return "detailed_news";
        }
        commentService.saveComment(id, user.getUser(), comment);
        return "redirect:/user/news/{id}";
    }

    @RequestMapping(value = "/admin/news/{id}", method = RequestMethod.POST)
    public String deleteComment(HttpServletRequest req) throws IOException {
        String idComment = req.getParameter("idComment");
        commentService.deleteComment(Long.parseLong(idComment));
        return "redirect:/admin/news/{id}";
    }
}
