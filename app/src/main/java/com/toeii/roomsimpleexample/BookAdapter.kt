package com.toeii.roomsimpleexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toeii.roomsimpleexample.db.BookBean
import kotlinx.android.synthetic.main.view_list_book_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookAdapter(var context: Context,var datas:List<BookBean>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_list_book_item,null)
        return BookHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(datas[position].isLike!!){
            holder.itemView.iv_book_icon.setImageResource(R.mipmap.icon_likeed)
        }else{
            holder.itemView.iv_book_icon.setImageResource(R.mipmap.icon_unlike)
        }
        holder.itemView.tv_book_title.text = datas[position].bookName

        holder.itemView.iv_book_icon.setOnClickListener{

            doAsync {
                // Room update data
                datas[position].isLike = !datas[position].isLike!!
                App.instance?.db?.bookDao()?.updateBook(datas[position])

                uiThread {
                    notifyDataSetChanged()
                }
            }

        }

        holder.itemView.iv_delete_icon.setOnClickListener {

            doAsync {
                // Room delete data
                val delResult = App.instance?.db?.bookDao()?.delete(datas[position])
                if(null != delResult){
                    uiThread {
                        datas -= datas[position]
                        notifyDataSetChanged()
                    }
                }

            }

        }

    }

    class BookHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}