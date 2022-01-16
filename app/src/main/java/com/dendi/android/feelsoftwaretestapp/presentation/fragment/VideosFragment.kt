package com.dendi.android.feelsoftwaretestapp.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dendi.android.feelsoftwaretestapp.data.video.Video
import com.dendi.android.feelsoftwaretestapp.databinding.FragmentVideosBinding
import com.dendi.android.feelsoftwaretestapp.presentation.util.KohiiProvider
import com.dendi.android.feelsoftwaretestapp.presentation.adapter.MovieAdapter
import kohii.v1.core.MemoryMode
import kohii.v1.core.Strategy

/**
 *  To play the sound to change Strategy.Single_Player on Strategy.Multi_Player.
 *  But in this case, only one video will be played.
 *  It was still an option instead of Recyclerview to use two players on the screen.
 */

class VideosFragment : BaseFragment<FragmentVideosBinding>() {

    override fun getViewBinding() = FragmentVideosBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kohii = KohiiProvider.get(requireContext())
        kohii.register(
            this, memoryMode = MemoryMode.BALANCED,
            activeLifecycleState = Lifecycle.State.CREATED
        )
            .addBucket(view = binding.rvMovies,
                strategy = Strategy.MULTI_PLAYER,
                selector = { candidates ->
                    candidates.take(2)
                }
            )

        val movieAdapter = MovieAdapter(kohii)

        movieAdapter.submitList(listOf(Video(BIG_BUCK_BUNNY_URL), Video(ELEPHANTS_DREAM_URL)))
        binding.rvMovies.adapter = movieAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    private companion object {
        const val BIG_BUCK_BUNNY_URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        const val ELEPHANTS_DREAM_URL =
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    }
}
