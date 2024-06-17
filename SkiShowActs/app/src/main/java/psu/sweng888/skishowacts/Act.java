package psu.sweng888.skishowacts;

import java.io.Serializable;

public class Act implements Serializable {
    // Private members
    private String title;
    private String subtitle;
    private String description;

    // Constructor
    public Act(String title, String subtitle, String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public String getDescription() {
        return description;
    }
}
