package com.it.testprojects.retrofitpaging.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.it.testprojects.retrofitpaging.api.QuoteAPI
import com.it.testprojects.retrofitpaging.db.QuoteDatabase
import com.it.testprojects.retrofitpaging.model.QuoteList
import com.it.testprojects.retrofitpaging.paging.QuotePagingSource
import com.it.testprojects.utils.NetworkUtils
import javax.inject.Inject

class QuoteRepository  @Inject constructor(
   private val quoteAPI: QuoteAPI

) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {QuotePagingSource(quoteAPI)}
    ).liveData

}