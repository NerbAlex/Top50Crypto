package ru.inc.simplecoinmarketcap.ui

import android.os.Bundle
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.core.Observable
import ru.inc.simplecoinmarketcap.R
import ru.inc.simplecoinmarketcap.databinding.ActivityMainBinding
import ru.inc.simplecoinmarketcap.view_model.MainViewModel
import ru.inc.simplecoinmarketcap.view_model.MainViewState
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        initRecyclerView()
        initViewModel()
        initSearchView()
    }

    private fun renderData(state: MainViewState) {
        when (state) {
            is MainViewState.Success -> {
                adapter.list = state.cryptoList
            }

            is MainViewState.ApiError -> {
                Toast.makeText(this, getString(R.string.api_request), Toast.LENGTH_LONG).show()
            }

            is MainViewState.NoNetwork -> {
                Toast.makeText(this, getString(R.string.network_request), Toast.LENGTH_LONG).show()
            }

            is MainViewState.FirstStartNoNetworkNoCache -> {
                Toast.makeText(
                    this, getString(R.string.first_start_request), Toast.LENGTH_LONG
                ).show()
            }

        }
    }

    private fun initRecyclerView() {
        adapter = MainAdapter()
        ui.recycler.adapter = adapter
        ui.recycler.setHasFixedSize(true)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.liveData.observe(this) { renderData(it) }
        viewModel.startActivity()
    }

    private fun initSearchView() {
        Observable.create<String> { emitter ->
            ui.searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    emitter.onNext(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    emitter.onNext(newText)
                    return false
                }
            })
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { viewModel.searchCryptoItem(it) }
    }
}