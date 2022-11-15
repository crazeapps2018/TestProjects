package com.it.testprojects.retrofitpaging.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.it.testprojects.retrofitpaging.model.QuoteList
import com.it.testprojects.retrofitpaging.repository.QuoteRepository
import com.it.testprojects.retrofitpaging.repository.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(repository: QuoteRepository):ViewModel() {

   val list = repository.getQuotes().cachedIn(viewModelScope)

}