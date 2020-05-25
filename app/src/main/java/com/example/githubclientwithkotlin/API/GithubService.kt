package com.example.githubclientwithkotlin.API
import com.example.githubclientwithkotlin.Entity.Repository
import retrofit2.http.GET

interface GithubService {
    @GET("/search/repositories")
    fun fetchRepository(): retrofit2.Call<Repository>
}
