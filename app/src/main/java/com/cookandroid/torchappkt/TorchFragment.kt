package com.cookandroid.torchappkt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.cookandroid.torchappkt.R

/**
 * A simple [Fragment] subclass.
 * Use the [TorchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TorchFragment : Fragment(), View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    var global: Global = Global()
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
        val root = inflater.inflate(R.layout.fragment_torch, container, false)
        val btnTorchToggle = root.findViewById<View>(R.id.btnTorchToggle) as Button
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            activity
        )

        btnTorchToggle.setOnClickListener {
            vibrate = sharedPreferences.getBoolean("torchVibrate", true)
            if (btnTorchToggle.text == getString(R.string.btnTorchOn)) {
                btnTorchToggle.setText(R.string.btnTorchOff)
                global.torchToggle("on", vibrate, root.context)
            } else if (btnTorchToggle.text == getString(R.string.btnTorchOff)) {
                btnTorchToggle.setText(R.string.btnTorchOn)
                global.torchToggle("off", vibrate, root.context)
            }
        }
        return root
    }


    override fun onClick(v: View) {
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
         * @return A new instance of fragment TorchFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): TorchFragment {
            val fragment = TorchFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}