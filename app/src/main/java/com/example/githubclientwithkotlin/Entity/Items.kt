package com.example.githubclientwithkotlin.Entity

import com.google.gson.annotations.SerializedName
import java.net.URL

data class Items(
    var id: Int,
    @SerializedName("full_name") var fullName: String,
    var description: String,
    @SerializedName("stargazers_count") var stargazersCount: Int,
    @SerializedName("html_url") var htmlUrl: URL
)
