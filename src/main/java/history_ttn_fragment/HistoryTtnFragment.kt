package history_ttn_fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import database.entity.TtnHistory

import kotlinx.android.synthetic.main.fragment_history_ttn.*
import ukr.cargotracker.R


class HistoryTtnFragment : Fragment() {

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTtnHistory.layoutManager = LinearLayoutManager(context)
        var adapter = HistoryListAdapter(context)
        rvTtnHistory.adapter = adapter

        val viewModel = ViewModelProviders.of(this).get(HistoryListViewModel::class.java!!)

        viewModel.getAllNotes().observe(this,
                Observer<List<TtnHistory>> { list ->
                    list?.let {
                        adapter.setData(list)
                    }
                })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_history_ttn, container, false)
    }

}
