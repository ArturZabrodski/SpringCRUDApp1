package ru.zabrodski.springcourse.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public class Page {
    private String slug;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotEmpty(message = "Content must not be empty")
    private String content;

    private String description;

    private LocalDateTime published_at;

    @Min(value = 1, message = "Priority must be between 1 and 5")
    @Max(value = 5, message = "Priority must be between 1 and 5")
    private int priority;

    public Page() {
    }

    public Page(String title, String content, int priority) {
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public String getSlug() {
        return getTitle().replace(" ", "_").toLowerCase();
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        if (getContent().length() > 100) {
            this.description = getContent().substring(0, 100) + " ...";
        } else {
            this.description = getContent() + " ...";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
