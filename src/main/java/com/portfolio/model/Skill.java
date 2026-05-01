package com.portfolio.model;

public class Skill {
    private String name;
    private int level;
    private String category;
    private String icon;

    public Skill() {}

    public Skill(String name, int level, String category, String icon) {
        this.name = name;
        this.level = level;
        this.category = category;
        this.icon = icon;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
