package com.example.chef_who.core.domain.usecases.auth

import com.example.chef_who.core.domain.repository.UserRepository

class LoginAuth(
    private val mUserRepository : UserRepository
){
      suspend operator fun invoke(){
        mUserRepository.login()
    }
}