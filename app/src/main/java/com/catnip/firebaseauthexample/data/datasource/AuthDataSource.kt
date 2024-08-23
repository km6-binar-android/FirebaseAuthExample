package com.catnip.firebaseauthexample.data.datasource

import com.catnip.firebaseauthexample.data.model.User
import com.catnip.firebaseauthexample.data.model.toUser
import com.catnip.firebaseauthexample.data.source.firebase.FirebaseService

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface AuthDataSource {
    @Throws(exceptionClasses = [Exception::class])
    suspend fun doLogin(email: String, password: String): Boolean

    @Throws(exceptionClasses = [Exception::class])
    suspend fun doRegister(email: String, password: String, fullName: String): Boolean

    suspend fun updateProfile(fullName: String): Boolean

    suspend fun updateEmail(newEmail: String): Boolean

    suspend fun updatePassword(newPassword: String): Boolean

    fun requestChangePasswordByEmail(): Boolean

    fun doLogout(): Boolean

    fun isLoggedIn(): Boolean

    fun getCurrentUser(): User?
}

class FirebaseAuthDataSource(private val service: FirebaseService) : AuthDataSource {
    override suspend fun doLogin(email: String, password: String): Boolean {
        return service.doLogin(email, password)
    }

    override suspend fun doRegister(email: String, password: String, fullName: String): Boolean {
        return service.doRegister(email, password, fullName)
    }

    override suspend fun updateProfile(fullName: String): Boolean {
        return service.updateProfile(fullName)
    }

    override suspend fun updateEmail(newEmail: String): Boolean {
        return service.updateEmail(newEmail)
    }

    override suspend fun updatePassword(newPassword: String): Boolean {
        return service.updatePassword(newPassword)
    }

    override fun requestChangePasswordByEmail(): Boolean {
        return service.requestChangePasswordByEmail()
    }

    override fun doLogout(): Boolean {
        return service.doLogout()
    }

    override fun isLoggedIn(): Boolean {
        return service.isLoggedIn()
    }

    override fun getCurrentUser(): User? {
        return service.getCurrentUser().toUser()
    }
}