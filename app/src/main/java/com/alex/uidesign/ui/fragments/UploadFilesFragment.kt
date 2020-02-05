package com.alex.uidesign.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alex.uidesign.R
import kotlinx.android.synthetic.main.fragment_upload_files.view.*

/**
 * A simple [Fragment] subclass.
 */
class UploadFilesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View = inflater.inflate(R.layout.fragment_upload_files, container, false)

        view.btnNext.setOnClickListener {
                v -> Navigation.findNavController(v).navigate(R.id.action_uploadFilesFragment_to_byeFragment)
        }

        return view
    }


}
