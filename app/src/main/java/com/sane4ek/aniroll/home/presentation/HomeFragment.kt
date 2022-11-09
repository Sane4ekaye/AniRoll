package com.sane4ek.aniroll.home.presentation

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.databinding.FragmentHomeBinding
import com.sane4ek.aniroll.home.presentation.adapters.PopularAdapter
import com.sane4ek.aniroll.home.presentation.adapters.RankedAdapter
import com.sane4ek.aniroll.splash.data.model.AnimeEntity
import com.sane4ek.aniroll.utils.PREFS_APP_DATA
import com.sane4ek.aniroll.utils.SharedPrefs


class HomeFragment : Fragment() {

    private val TAG = "homefrag1"

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val images = IntArray(2)
//        images.set(0, R.drawable.ic_home)
//        images.set(1, R.drawable.filters_btn)
//        binding.carousel.referencedIds = images

        var colors = intArrayOf(
            Color.parseColor("#ffd54f"),
            Color.parseColor("#ffca28"),
            Color.parseColor("#ffc107"),
            Color.parseColor("#ffb300"),
            Color.parseColor("#ffa000"),
            Color.parseColor("#ff8f00"),
            Color.parseColor("#ff6f00"),
            Color.parseColor("#c43e00")
        )

        binding.carousel.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return colors.size
            }

            override fun populate(view: View, index: Int) {
                view.setBackgroundColor(colors[index])
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })

        init()
    }

    private fun init() {
        binding.homeLayout.clipToOutline = true

        val appData = SharedPrefs.getAppDataModel(PREFS_APP_DATA, requireContext())

        // доп проверка от которой надо нахуй избавиться
        if (appData != null) {
            binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvPopular.adapter = PopularAdapter(appData.animeResultModel.popular) {
                val bundle = Bundle()
                bundle.putSerializable("anime", it)
                requireView().findNavController().navigate(R.id.action_homeFragment_to_animeFragment, bundle)
            }

            binding.rvByRating.layoutManager = LinearLayoutManager(requireContext())
            binding.rvByRating.adapter = RankedAdapter(appData.animeResultModel.ranked)
        } else {
            Toast.makeText(requireContext(), "АААААА БЛЯЯЯТЬ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }
}