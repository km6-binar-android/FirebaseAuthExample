package com.catnip.firebaseauthexample.data.repository

import com.catnip.firebaseauthexample.data.datasource.AuthDataSource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface UserRepository {

}

class UserRepositoryImpl(private val dataSource: AuthDataSource) : UserRepository {

}
