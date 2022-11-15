package com.it.testprojects.retrofitpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.it.testprojects.R
import com.it.testprojects.retrofitpaging.paging.QuotePagingAdapter
import com.it.testprojects.retrofitpaging.paging.QuotePagingSource
import com.it.testprojects.retrofitpaging.repository.Response
import com.it.testprojects.retrofitpaging.viewmodels.QuoteViewModel
import com.it.testprojects.utils.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuotesActivity : AppCompatActivity() {

    private val quoteViewModel by viewModels<QuoteViewModel>()
    lateinit var adapter: QuotePagingAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        recyclerView = findViewById(R.id.quoteList)
        adapter = QuotePagingAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        quoteViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })

    }
}