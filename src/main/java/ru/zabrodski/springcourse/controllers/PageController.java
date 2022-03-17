package ru.zabrodski.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zabrodski.springcourse.dao.PageDAO;
import ru.zabrodski.springcourse.models.Page;

import javax.validation.Valid;

@Controller
@RequestMapping()
public class PageController {

    private final PageDAO pageDAO;
    private boolean buttonClick = false;
    @Autowired
    public PageController(PageDAO pageDAO) {
        this.pageDAO = pageDAO;
    }

    @GetMapping()
    public String index(Model model) {
        buttonClick = !buttonClick;
        if (buttonClick){
            model.addAttribute("pages", pageDAO.indexHide());
        }
        else
        {
            model.addAttribute("pages", pageDAO.indexShow());
        }

        return "index";
    }

    @GetMapping("/{slug}")
    public String show(@PathVariable("slug") String slug, Model model) {
        model.addAttribute("page", pageDAO.show(slug));
        return "show";
    }

    @GetMapping("/new")
    public String newPage(@ModelAttribute("page") Page page) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("page") @Valid Page page,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        pageDAO.save(page);
        return "redirect:/";
    }

    @GetMapping("/{slug}/edit")
    public String edit(Model model, @PathVariable("slug") String slug) {
        model.addAttribute("page", pageDAO.show(slug));
        return "edit";
    }

    @GetMapping("/{slug}/publish")
    public String publish(Model model, @PathVariable("slug") String slug) {
        model.addAttribute("page", pageDAO.show(slug));
        return "redirect:/";
    }

    @PatchMapping("/{slug}")
    public String update(@ModelAttribute("page") @Valid Page page,
                         BindingResult bindingResult,
                         @PathVariable("slug") String slug) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        pageDAO.update(slug, page);
        return "redirect:/";
    }

    @PutMapping("/{slug}")
    public String updatePublish(@ModelAttribute("page") @Valid Page page,
                                BindingResult bindingResult,
                                @PathVariable("slug") String slug) {
        pageDAO.publish(slug, page);
        return "redirect:/";
    }

    @DeleteMapping("/{slug}")
    public String delete(@PathVariable("slug") String slug) {
        pageDAO.delete(slug);
        return "redirect:/";
    }
}
