package com.alex.uidesign.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alex.uidesign.R
import kotlinx.android.synthetic.main.fragment_delivery.view.*

/**
 * A simple [Fragment] subclass.
 */
class DeliveryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view:View = inflater.inflate(R.layout.fragment_delivery, container, false)

        view.btnNext.setOnClickListener {
                v -> Navigation.findNavController(v).navigate(R.id.action_deliveryFragment_to_uploadFilesFragment)
        }

        return view
    }


}
