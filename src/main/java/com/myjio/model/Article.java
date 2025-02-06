package com.myjio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String title;
    private String description;
    private String url;
    private String content;
    private String publishedAt;
}
