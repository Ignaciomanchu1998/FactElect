package data.network.services

import data.network.KtorApi
import data.network.models.User
import io.ktor.client.call.body
import io.ktor.client.request.post

class  UserService: KtorApi() {
    suspend fun getToken(): List<User> = client.post {
        pathUrl("/Account/Login")
    }.body()
}