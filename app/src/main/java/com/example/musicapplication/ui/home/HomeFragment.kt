package com.example.musicapplication.ui.home

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapplication.R
import com.example.musicapplication.adapter.AlbumAdapter
import com.example.musicapplication.adapter.RecommendationSongAdapter
import com.example.musicapplication.adapter.SongAdapter
import com.example.musicapplication.base.BaseFragment
import com.example.musicapplication.databinding.FragmentHomeBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private lateinit var recommendationAdapter: RecommendationSongAdapter
    private lateinit var songAdapter: SongAdapter
    private lateinit var albumAdapter: AlbumAdapter

    override fun initView() {
        binding.apply {
            recommendationAdapter = RecommendationSongAdapter(requireContext()) { song ->
                // TODO: navigate to detail screen
            }
            rcvRecommendations.adapter = recommendationAdapter
            rcvRecommendations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            songAdapter = SongAdapter(requireContext()) { song ->
                // TODO: navigate to detail screen
            }
            rcvRecentSongs.adapter = songAdapter
            rcvRecentSongs.layoutManager = LinearLayoutManager(context)

            albumAdapter = AlbumAdapter(requireContext()) { album ->
                // TODO: navigate to detail screen
            }
            rcvTopAlbums.adapter = albumAdapter
            rcvTopAlbums.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun initViewObserver() {
        GlobalScope.launch(Dispatchers.IO) {
            lifecycleScope.launch {
                viewModel.recommendationListFlow.collect { songs ->
                    recommendationAdapter.submitList(songs)
                }
                viewModel.songListFlow.collect { songs ->
                    songAdapter.submitList(songs)
                }
                viewModel.albumListFlow.collect { albums ->
                    albumAdapter.submitList(albums)
                }
            }
        }
    }

    override fun initViewListener() {

    }

    override fun initData() {

    }
}