package com.alex.uidesign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.alex.uidesign.R
import com.diegodobelo.expandingview.ExpandingItem
import com.diegodobelo.expandingview.ExpandingList
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_expanding.*
import kotlinx.android.synthetic.main.expanding_bottom_sheet_dialog.*
import kotlinx.android.synthetic.main.expanding_bottom_sheet_dialog.view.*

class ExpandingActivity : AppCompatActivity() {

    private var mExpandingList: ExpandingList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanding)

        mExpandingList = findViewById(R.id.expanding_list_main)
        createItems()

        ivBack.setOnClickListener{
            finish()
        }
    }

    private fun createItems() {
        addItem("Ghost 1", arrayOf("Camisas", "Zapatos", "pantalones", "Shorts", "Sandalias", "Vestidos"), R.color.pink, R.drawable.ic_ghost)
        addItem("Ghost 2", arrayOf("Perro", "Gato", "Conejo"), R.color.blue, R.drawable.ic_ghost)
        addItem("Ghost 3", arrayOf("Motocicletas"), R.color.purple, R.drawable.ic_ghost)
        addItem("Ghost 4", arrayOf("Tan", "Te de limon", "Cafe"), R.color.yellow, R.drawable.ic_ghost)
        addItem("Ghost 5", arrayOf(), R.color.orange, R.drawable.ic_ghost)
        addItem("Ghost 6", arrayOf("Golf", "Football"), R.color.green, R.drawable.ic_ghost)
        addItem("Ghost 7", arrayOf("Ferrari", "Mazda", "Honda", "Toyota", "Fiat"), R.color.colorRed, R.drawable.ic_ghost)
        addItem("Ghost 8", arrayOf("PS4", "Xbox one", "PC gamer"), R.color.pink, R.drawable.ic_ghost)
        addItem("Ghost 9", arrayOf("Hamburguesas", "Helado", "Caramelos"), R.color.purple, R.drawable.ic_ghost)
    }

    private fun addItem(title: String, subItems: Array<String>, colorRes: Int, iconRes: Int) {
        val item = mExpandingList!!.createNewItem(R.layout.expanding_layout)

        if (item != null) {
            item.setIndicatorColorRes(colorRes)
            item.setIndicatorIconRes(iconRes)
            (item.findViewById(R.id.title) as TextView).text = title

            item.createSubItems(subItems.size)
            for (i in 0 until item.subItemsCount) {
                val view = item.getSubItemView(i)
                configureSubItem(item, view, subItems[i])
            }

            item.findViewById<ImageView>(R.id.add_more_sub_items).setOnClickListener{
//                showInsertDialog(object : OnItemCreated {
//                    override fun itemCreated(title: String) {
//                        val newSubItem = item.createSubItem()
//                        configureSubItem(item, newSubItem!!, title)
//                    }
//                })

                showBottomSheetDialogInsert(object : OnItemCreated {
                    override fun itemCreated(title: String) {
                        val newSubItem = item.createSubItem()
                        configureSubItem(item, newSubItem!!, title)
                    }
                })
            }

            item.findViewById<ImageView>(R.id.remove_item)
                .setOnClickListener{ mExpandingList!!.removeItem(item) }
        }
    }

    private fun configureSubItem(item: ExpandingItem?, view: View, subTitle: String) {
        (view.findViewById(R.id.sub_title) as TextView).text = subTitle
        view.findViewById<ImageView>(R.id.remove_sub_item)
            .setOnClickListener { item!!.removeSubItem(view) }
    }

    private fun showInsertDialog(positive: OnItemCreated) {
        var text = EditText(this)
        val builder = AlertDialog.Builder(this)
        builder.setView(text)
        builder.setTitle("Ingresa nuevo item")
        builder.setPositiveButton(android.R.string.ok
        ) { _, _ ->
            positive.itemCreated(text.text.toString())
        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.show()
    }

    private fun showBottomSheetDialogInsert(positive: OnItemCreated){
        val view = layoutInflater.inflate(R.layout.expanding_bottom_sheet_dialog, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        view.btnAgregar.setOnClickListener {
            positive.itemCreated(view.etItem.text.toString())
            dialog.dismiss()
        }

        view.btnCancelar.setOnClickListener { dialog.dismiss() }
    }

    internal interface OnItemCreated {
        fun itemCreated(title: String)
    }
}
