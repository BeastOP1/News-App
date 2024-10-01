package com.example.newsapp.domain.usecase.app_entry

import com.example.newsapp.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {

    suspend  operator fun invoke(){
        localUserManger.saveAppEntry()
    }
}