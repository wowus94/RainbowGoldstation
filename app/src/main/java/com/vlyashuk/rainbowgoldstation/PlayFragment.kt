package com.vlyashuk.rainbowgoldstation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vlyashuk.rainbowgoldstation.databinding.FragmentPlayBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class PlayFragment() : Fragment() {
    private val handler = Handler(Looper.getMainLooper())
    private var timer: CountDownTimer? = null

    private var _binding: FragmentPlayBinding? = null
    private val binding: FragmentPlayBinding
        get() = _binding ?: throw RuntimeException("FragmentPlayBinding? is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            MAIN.navController.navigate(R.id.action_playFragment_to_startFragment)
        }

        binding.tvTimer

            handler.post(object : Runnable {
                override fun run() {
                    handler.postDelayed(this, 1000)
                    updateTime()
                }
            })

        binding.btnStop.setOnClickListener {
            binding.tvTimer.setText("Finish!")
            timer?.cancel()
            timer?.onFinish()
        }

    }

    fun updateTime() {
        if (timer != null) {
            timer?.cancel()
        } else {
            object : CountDownTimer(9000000, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    val f: NumberFormat = DecimalFormat("00")
                    val hour = millisUntilFinished / 3600000 % 24
                    val min = millisUntilFinished / 60000 % 60
                    val sec = millisUntilFinished / 1000 % 60
                    binding.tvTimer.setText(
                        (f.format(hour) + ":" + f.format(min)).toString() + ":" + f.format(sec)
                    )
                }

                override fun onFinish() {
                    binding.tvTimer.setText("00:00:00")
                }
            }.start()
        }
    }
}

