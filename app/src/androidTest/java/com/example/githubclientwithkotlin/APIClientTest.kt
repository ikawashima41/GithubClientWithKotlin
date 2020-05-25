package com.example.githubclientwithkotlin

import com.example.githubclientwithkotlin.API.APIClient
import junit.framework.TestCase

class APIClientTest : TestCase() {

    fun testFetchRepository() {
        val service = APIClient().fetchRepository()
        assertNotNull(service)
    }
}
