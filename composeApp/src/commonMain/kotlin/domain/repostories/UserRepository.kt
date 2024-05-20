package domain.repostories

interface UserRepository {
    suspend fun getToken(): String
}