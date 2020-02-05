package com.alex.uidesign.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alex.uidesign.R
import kotlinx.android.synthetic.main.fragment_bye.view.*

/**
 * A simple [Fragment] subclass.
 */
class ByeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bye, container, false)

        view.btnNext.setOnClickListener { v ->
            this.activity!!.finish()
        }

        return view
    }


}
