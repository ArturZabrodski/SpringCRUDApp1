package ru.zabrodski.springcourse.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class Page {
    @NotEmpty(message = "The title must not be empty.")
    private String title;
    private String description;
    private String slug;
    private String menu_label;
    private String h1;
    @NotEmpty(message = "The article must not be empty.")
    private String content;
    private LocalDateTime published_at;
    @Min(value = 1, message = "Priority must be between 1 and 5")
    @Max(value = 5, message = "Priority must be between 1 and 5")
    private int priority;

    public Page() {
    }

    public Page(String title, String slug, String content, LocalDateTime published_at, int priority) {
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.published_at = published_at;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        description = content;
        // <p> абзацы заменяются новыми строками
        description = description.replaceAll("<p .*?>", "\r\n");
        // <br> <br/> заменить на новую строку
        description = description.replaceAll("<br\\s*/?>", "\r\n");
        description = description.replaceAll("\\<.*?>", "");
        description = description.replaceAll("&zwnj", "");
        description = description.replaceAll("&zwj;", "");
        description = description.replaceAll("&nbsp;", " ");
        description = description.replaceAll("&thinsp;", " ");
        description = description.replaceAll("&ensp;", "  ");
        description = description.replaceAll("&emsp;", "    ");

        if (description.length() > 100) {
            description = description.substring(0, 100) + " ...";
        } else {
            description = description + " ...";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return title.replace(" ", "_").toLowerCase();
    }

    public void setSlug(String title) {
        this.slug = title.replace(" ", "_").toLowerCase();
    }

    public String getMenu_label() {
        return slug;
    }

    public void setMenu_label(String menu_label) {
        this.menu_label = slug;
    }

    public String getH1() {
        return "<h1>" + title + "</h1>";
    }

    public void setH1(String title) {
        this.h1 = "<h1>" + title + "</h1>";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDateTime published_at) {
        this.published_at = published_at;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
