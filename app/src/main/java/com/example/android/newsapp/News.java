package com.example.android.newsapp;

public class News {
    // Information about news
    private final String mSectionName;
    private final String mAuthor;
    private final String mTitle;
    private final String mPublicationDate;
    private final String mUrl;


    /**
     * Create the {@link News} object.
     *
     * @param section         is the news category name.
     * @param author          is the name of news author.
     * @param title           is the news title.
     * @param publicationDate is the news publication date.
     * @param url             is the news url.
     */
    public News(String section, String author, String title, String publicationDate, String url) {
        mSectionName = section;
        mAuthor = author;
        mTitle = title;
        mPublicationDate = publicationDate;
        mUrl = url;
    }


    /**
     * Getter methods of {@link News} class.
     *
     * @return the String objects.
     */
    public String getSectionName() {
        return mSectionName;
    }


    public String getAuthor() {
        return mAuthor;
    }


    public String getTitle() {
        return mTitle;
    }


    public String getPublicationDate() {
        return mPublicationDate;
    }


    public String getUrl() {
        return mUrl;
    }
}
