package com.cookandroid.torchappkt

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.cookandroid.torchappkt.R

/**
 * A simple [Fragment] subclass.
 * Use the [FlashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlashFragment : Fragment() {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    var handler: Handler? = null

    var thread: Thread? = null
    var global: Global = Global()
    val flashWeight: Double = 0.7
    private val flashRun = true
    var vibrate: Boolean = true

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_flash, container, false)
        val btnFlashToggle = root.findViewById<View>(R.id.btnFlashToggle) as Button
        val seekBar = root.findViewById<View>(R.id.seekBar) as SeekBar
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            activity
        )

        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val toggle = msg.obj as String
                vibrate = sharedPreferences.getBoolean("flashVibrate", true)
                global.torchToggle(toggle, vibrate, root.context)
            }
        }
        val flashRunnable = FlashTread(handler as Handler)
        btnFlashToggle.setOnClickListener {
            if (btnFlashToggle.text == getString(R.string.btnFlashOff)) {
                flashRunnable.stop()
                if (thread != null) thread!!.interrupt()
                global.torchToggle("off", vibrate, root.context)
                btnFlashToggle.setText(R.string.btnFlashOn)
            } else if (btnFlashToggle.text == getString(R.string.btnFlashOn)) {
                flashRunnable.start()
                thread = Thread(flashRunnable)
                thread!!.start()
                btnFlashToggle.setText(R.string.btnFlashOff)
            }
        }

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val seekBarPG = seekBar.progress
                val waitTime = global.setSpeedValue(flashWeight, seekBarPG.toDouble())
                flashRunnable.setSpeed(waitTime) // 시간이므로
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        return root
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlashFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): FlashFragment {
            val fragment = FlashFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}