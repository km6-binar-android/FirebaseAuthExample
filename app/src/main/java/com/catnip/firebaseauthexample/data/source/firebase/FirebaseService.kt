package com.catnip.firebaseauthexample.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface FirebaseService {
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

    fun getCurrentUser(): FirebaseUser?
}

class FirebaseServiceImpl : FirebaseService {
    private val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun doLogin(email: String, password: String): Boolean {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user != null
    }

    override suspend fun doRegister(email: String, password: String, fullName: String): Boolean {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        result.user?.updateProfile(
            userProfileChangeRequest { displayName = fullName }
        )
        return result != null
    }

    override suspend fun updateProfile(fullName: String): Boolean {
        getCurrentUser()?.updateProfile(
            userProfileChangeRequest { displayName = fullName }
        )
        return true
    }

    override suspend fun updateEmail(newEmail: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(newPassword: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun requestChangePasswordByEmail(): Boolean {
        TODO("Not yet implemented")
    }

    override fun doLogout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }
}