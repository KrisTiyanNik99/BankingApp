package main.configs;

import java.util.List;

public class Contacts {
    // Url hipper links
    private static final String LinkedInLinks = "<html><a href=\"#\">Visit my LinkedIn</a></html>";
    private static final String GitHubLinks = "<html><a href=\"#\">Visit my Github</a></html>";
    private static final String GmailLinks = "kristiyan18kiko@gmail.com";

    // Contact names
    private static final String Description = "You can contact with me at any of the following links:";
    private static final String LinkedIn= "LinkedIn:";
    private static final String GitHub= "GitHub:";
    private static final String Gmail= "Gmail:";

    public static List<String> getLinks() {
        return List.of(LinkedInLinks, GitHubLinks, GmailLinks);
    }

    public static List<String> getContactsNames() {
        return List.of(Description, LinkedIn, GitHub, Gmail);
    }
}
