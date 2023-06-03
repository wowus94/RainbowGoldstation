package com.vlyashuk.rainbowgoldstation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vlyashuk.rainbowgoldstation.databinding.FragmentMainBinding
import com.vlyashuk.rainbowgoldstation.databinding.FragmentStartBinding
import java.lang.Math.random
import kotlin.random.Random

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding? is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_startFragment_to_mainFragment)
        }

        binding.btnStart.setOnClickListener {
            MAIN.navController.navigate(R.id.action_startFragment_to_playFragment)

        }
        numberComputerView()
    }

    private fun numberComputerView() {
        val number = arrayOf(
            "J321", "F435", "R356",
            "I888", "P952", "Y545",
            "H789", "C321", "T578",
            "G875", "L653", "E342"
        )
        val arraySize = number.size
        val rand = Random.nextInt(arraySize)
        val name = "${number[rand]}"
        binding.tvNumberComputer.text = name
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}