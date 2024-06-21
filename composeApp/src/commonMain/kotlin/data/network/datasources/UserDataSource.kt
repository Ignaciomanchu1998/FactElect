package data.network.datasources

import data.network.services.UserService
import kotlinx.coroutines.withContext
import util.Dispatcher

class UserDataSource(
    private val api: UserService,
    private val dispatcher: Dispatcher
) {
    suspend fun getUser() = withContext(dispatcher.ioDispatcher) {
        api.getToken()
    }
}