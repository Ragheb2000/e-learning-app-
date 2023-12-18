package com.example.newsapplicationmvvm.Modle

import com.example.newsapplicationmvvm.Modle.Article

data class NewsDataMoudle(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)