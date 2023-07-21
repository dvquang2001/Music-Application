package com.example.musicapplication.ui.home

import androidx.lifecycle.ViewModel
import com.example.musicapplication.data.Album
import com.example.musicapplication.data.RecommendationSong
import com.example.musicapplication.data.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeViewModel: ViewModel() {

     val recommendationListFlow: Flow<List<RecommendationSong>> = flow {
        val recommendationList = mutableListOf<RecommendationSong>()
        recommendationList.add(RecommendationSong("1","Night Vocals","30 songs. 1 hour",""))
        recommendationList.add(RecommendationSong("2","Dance All Nights","34 songs. 1 hour 34 mins",""))
        recommendationList.add(RecommendationSong("3","Night Vocals Ver2","20 songs. 1 hour",""))

        emit(recommendationList)
    }
    val songListFlow: Flow<List<Song>> = flow {
        val songList = mutableListOf<Song>()

        songList.add(Song("s1","Redbone","","","6:19"))
        songList.add(Song("s2","3005","","","3:54"))
        songList.add(Song("s3","Les","","","5:17"))

        emit(songList)
    }

    val albumListFlow: Flow<List<Album>> = flow {
        val albumList = mutableListOf<Album>()

        albumList.add(Album("a1","Awaken, My Love","","Album - 2016"))
        albumList.add(Album("a2","Because of the truth","","Album - 2016"))
        albumList.add(Album("a3","Feels","","Album - 2016"))

        emit(albumList)
    }

}