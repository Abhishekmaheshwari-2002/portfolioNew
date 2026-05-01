package com.portfolio.model;

public class Project {
    private String id;
    private String title;
    private String description;
    private String[] technologies;
    private String category;
    private String githubUrl;
    private String liveUrl;
    private String year;

    public Project() {}

    public Project(String id, String title, String description,
                   String[] technologies, String category,
                   String githubUrl, String liveUrl, String year) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.category = category;
        this.githubUrl = githubUrl;
        this.liveUrl = liveUrl;
        this.year = year;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String[] getTechnologies() { return technologies; }
    public void setTechnologies(String[] technologies) { this.technologies = technologies; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLiveUrl() { return liveUrl; }
    public void setLiveUrl(String liveUrl) { this.liveUrl = liveUrl; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
}
