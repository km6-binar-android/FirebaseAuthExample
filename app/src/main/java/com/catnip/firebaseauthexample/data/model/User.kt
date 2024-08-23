package com.catnip.firebaseauthexample.data.model

import com.google.firebase.auth.FirebaseUser

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class User(
    val id: String,
    val email: String,
    val fullName: String,
)

fun FirebaseUser?.toUser() = this?.let {
    User(
        id = this.uid,
        email = this.email.orEmpty(),
        fullName = this.displayName.orEmpty()
    )
}