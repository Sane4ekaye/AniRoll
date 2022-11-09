package com.sane4ek.aniroll.splash.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sane4ek.aniroll.R
import com.sane4ek.aniroll.core.data.AppDataModel
import com.sane4ek.aniroll.databinding.FragmentSplashBinding
import com.sane4ek.aniroll.splash.data.model.AnimeResultModel
import com.sane4ek.aniroll.utils.PREFS_APP_DATA
import com.sane4ek.aniroll.utils.SharedPrefs
import com.sane4ek.aniroll.utils.isNetworkConnected
import org.json.JSONObject

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        splashViewModel.successLiveData.observe(viewLifecycleOwner, successResponse())
        splashViewModel.failureLiveData.observe(viewLifecycleOwner, failureResponse())

        if (isNetworkConnected(requireContext())) {
            val popularObject = JSONObject()
            popularObject.put("query","query {\n" +
                    "\tPage(page: 1, perPage:10) {\n" +
                    "    media(sort: POPULARITY_DESC, type: ANIME) {\n" +
                    "      coverImage {\n" +
                    "        large\n" +
                    "      }\n" +
                    "      id\n" +
                    "      source\n" +
                    "      description\n" +
                    "      genres\n" +
                    "      meanScore\n" +
                    "      volumes\n" +
                    "      averageScore\n" +
                    "      popularity\n" +
                    "      title {\n" +
                    "        english\n" +
                    "        native\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}")

            val rankedObject = JSONObject()
            rankedObject.put("query","query {\n" +
                    "\tPage(page: 1, perPage:10) {\n" +
                    "    media(sort: SCORE_DESC, type: ANIME) {\n" +
                    "      coverImage {\n" +
                    "        large\n" +
                    "      }\n" +
                    "      id\n" +
                    "      source\n" +
                    "      description\n" +
                    "      genres\n" +
                    "      meanScore\n" +
                    "      volumes\n" +
                    "      averageScore\n" +
                    "      popularity\n" +
                    "      title {\n" +
                    "        english\n" +
                    "        native\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }\n" +
                    "}")
            splashViewModel.getLists(popularObject, rankedObject)
        } else {
            Toast.makeText(requireContext(), "Проверьте подключение к интернету и сделайте свайп", Toast.LENGTH_LONG).show()
        }
    }

    private fun successResponse() = Observer<AnimeResultModel> {
        SharedPrefs.saveAppDataModel(PREFS_APP_DATA, AppDataModel(it), requireContext())
        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
    }

    private fun failureResponse() = Observer<Boolean> {
        Toast.makeText(requireContext(), "Ошибка, попробуйте снова, сделав свайп", Toast.LENGTH_LONG).show()
    }
}