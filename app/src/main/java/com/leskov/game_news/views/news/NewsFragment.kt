package com.leskov.game_news.views.news

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.leskov.game_news.R
import com.leskov.game_news.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: NewsViewModel by viewModels()

//    private val adapter = NewsAdapter()

    private val adapter = NewsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        binding.list.adapter = adapter

        binding.list.adapter = adapter


        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_news, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    private fun initObservers() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            if (news.isNullOrEmpty()){
                adapter.submitList(null)
            } else {
                adapter.submitList(news)
            }
//            TabLayoutMediator(binding.tabLayout, binding.pager){ tab, position ->
//                val list : MutableList<String> = mutableListOf()
//                news.map {
//                    adapter.listOfTop.add(it.top)
//                    list.add(it.type)
//                    tab.text = "TAB $position"
//                }
//            }.attach()
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        p0.let { viewModel.setFinder(it!!) }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0.let { viewModel.setFinder(it!!) }
        return true
    }
}