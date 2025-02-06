package com.myjio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {
    String country;
    Integer totalResults;
    Article[] articles;
}
