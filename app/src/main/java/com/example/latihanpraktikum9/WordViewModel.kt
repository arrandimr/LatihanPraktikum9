package com.example.latihanpraktikum9

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel (application: Application) : AndroidViewModel(application){
    //menyumpan data dengan siklus yang selamat dari perubahan konfigurasi
    private val repository: WordRepository
    val allWords : LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    fun insert(word:Word) = viewModelScope.launch {
        repository.insert(word)
    }
    fun deleteALL() = viewModelScope.launch {
        repository.deleteALL()
    }
}