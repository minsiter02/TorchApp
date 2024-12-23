package com.cookandroid.torchappkt

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.cookandroid.torchappkt.R

/**
 * A simple [Fragment] subclass.
 * Use the [MorseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MorseFragment : Fragment() {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    var handler: Handler? = null
    var thread: Thread? = null
    var global: Global = Global()

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    var vibrate: Boolean = true
    val morseWeight: Float = 0.7f
    var progress: Int = 0
    var tick: Int = 0

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
        val root = inflater.inflate(R.layout.fragment_morse, container, false)
        val btnMoresToggle = root.findViewById<View>(R.id.btnMoresToggle) as Button
        val edtMores = root.findViewById<View>(R.id.edtMores) as EditText
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            activity
        )

        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val toggle = msg.obj as String
                global.torchToggle(toggle, vibrate, root.context)
            }
        }
        val moresRunnable = MorseThread(handler as Handler, btnMoresToggle)

        btnMoresToggle.setOnClickListener {
            val edtMoresText = edtMores.text.toString()
            val inputEdtMoresText = global.convertText(edtMoresText)
            val morseCode = global.convertMorseCode(inputEdtMoresText)
            moresRunnable.setMorseCode(morseCode)
            if (btnMoresToggle.text == getString(R.string.btnMorseStop)) {
                moresRunnable.stop()
                if (thread != null) thread!!.interrupt()
                global.torchToggle("off", vibrate, root.context)
                btnMoresToggle.setText(R.string.btnMoresPlay)
            } else if (btnMoresToggle.text == getString(R.string.btnMoresPlay)) {
                vibrate = sharedPreferences.getBoolean("morseVibrate", true)
                progress = sharedPreferences.getInt("morseSpeed", 0)

                tick = global.setSpeedValue(morseWeight.toDouble(), progress.toDouble())
                moresRunnable.setTickSpeed(tick)

                moresRunnable.start()
                thread = Thread(moresRunnable)
                thread!!.start()
                btnMoresToggle.setText(R.string.btnMorseStop)
            }
        }
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
         * @return A new instance of fragment MorseFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): MorseFragment {
            val fragment = MorseFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}