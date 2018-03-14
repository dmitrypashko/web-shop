package com.gmail.dmitrypashko.dmitry;

import com.gmail.dmitrypashko.dmitry.model.Comment;
import com.gmail.dmitrypashko.dmitry.model.News;
import com.gmail.dmitrypashko.dmitry.modelDTO.AppUserPrincipal;
import com.gmail.dmitrypashko.dmitry.validator.NewsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;


@Controller
public class NewsController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private NewsValidator newsValidator;

    @RequestMapping(value = {"/news/img/{id}"}, method = RequestMethod.GET)
    public void NewsImg(HttpServletResponse resp, @PathVariable Long id) throws IOException {
        File file = fileService.getFileById(id);
        if (!file.exists()) {
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write("Error".getBytes(Charset.forName("UTF-8")));
            outputStream.close();
        }
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        resp.setContentType(mimeType);

        resp.setHeader("Content-Disposition", "inline; filename=/*" + file.getName() + "/*");
        resp.setContentLength((int) file.length());
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream inputStream = new BufferedInputStream(fileInputStream);
        ) {
            FileCopyUtils.copy(inputStream, resp.getOutputStream());
        }
    }


    @RequestMapping(value = {"/user/news/page/{pagination}"}, method = RequestMethod.GET)
    public String userNewsGet(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        req.setAttribute("paginations", newsService.getPaginationList());
        req.setAttribute("news", newsService.getAllNews(pagination - 1));
        return "news";
    }

    @RequestMapping(value = {"/admin/news/page/{pagination}"}, method = RequestMethod.GET)
    public String adminNewsGet(HttpServletRequest req, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        req.setAttribute("paginations", newsService.getPaginationList());
        req.setAttribute("news", newsService.getAllNews(pagination - 1));
        return "admin_news";
    }

    @RequestMapping(value = "/admin/addNews/page/{pagination}", method = RequestMethod.POST)
    public String addNews(HttpServletRequest req, @ModelAttribute("News") News news, @RequestParam("imageNews") MultipartFile image, BindingResult bindingResult, @PathVariable("pagination") Integer pagination) throws IOException {
        newsValidator.validate(news, bindingResult);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        if (bindingResult.hasErrors()) {
            req.setAttribute("paginations", newsService.getPaginationList());
            req.setAttribute("news", newsService.getAllNews(pagination - 1));
            return "add_news";
        }
        newsService.addNews(news, image);
        return "redirect:/admin/addNews/page/{pagination}";
    }

    @RequestMapping(value = "/admin/addNews/page/{pagination}", method = RequestMethod.GET)
    public String addNewsGet(HttpServletRequest req, Model model, @PathVariable Integer pagination) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        model.addAttribute("News", new News());
        req.setAttribute("paginations", newsService.getPaginationList());
        req.setAttribute("news", newsService.getAllNews(pagination - 1));
        return "add_news";
    }

    @RequestMapping(value = {"/user/news/{id}"}, method = RequestMethod.GET)
    public String detailNewsGet(HttpServletRequest req, Model model, @PathVariable Long id) throws IOException {
        model.addAttribute("Comment", new Comment());
        req.setAttribute("detailedNews", newsService.getNews(id));
        req.setAttribute("comments", commentService.getCommentByNews(id));
        return "detailed_news";
    }


    @RequestMapping(value = {"/admin/news/{id}"}, method = RequestMethod.GET)
    public String AdminDetailNewsGet(HttpServletRequest req, @PathVariable("id") Long id) throws IOException {
        req.setAttribute("comments", commentService.getCommentByNews(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUserPrincipal user = (AppUserPrincipal) auth.getPrincipal();
        req.setAttribute("role", user.getUser().getRole().name());
        News news = newsService.getNews(id);
        if (news != null) {
            req.setAttribute("detailedNews", news);
            return "admin_detailed_news";
        }
        return "redirect:/admin/news/page/{id}";
    }

    @RequestMapping(value = "/admin/deleteNews", method = RequestMethod.POST)
    public String deleteNews(HttpServletRequest req) throws IOException {
        String idNews = req.getParameter("idNews");
        newsService.deleteNews(Long.parseLong(idNews));
        return "redirect:/admin/news/page/1";
    }

    @RequestMapping(value = "/admin/deleteNews", method = RequestMethod.GET)
    public String deleteNewsGet() throws IOException {
        return "redirect:/admin/news/page/1";
    }

    @RequestMapping(value = {"/admin/changeNews/{id}"}, method = RequestMethod.POST)
    public String changeNews(HttpServletRequest req, @ModelAttribute("News") News news, @RequestParam("imageNews") MultipartFile image, BindingResult bindingResult, @PathVariable("id") String id) throws IOException {
        newsValidator.validate(news, bindingResult);
        if (bindingResult.hasErrors()) {
            req.setAttribute("detailedNews", newsService.getNews(news.getId()));
            return "change_news";
        }
        newsService.changeNews(news, image);
        return "redirect:/admin/changeNews/{id}";
    }

    @RequestMapping(value = {"/admin/changeNews/{id}"}, method = RequestMethod.GET)
    public String changeNewsGet(HttpServletRequest req, @PathVariable Long id, Model model) throws IOException {
        model.addAttribute("News", new News());
        News news = newsService.getNews(id);
        if (news != null) {
            req.setAttribute("detailedNews", news);
            return "change_news";
        }
        return "admin_news";


    }

}
