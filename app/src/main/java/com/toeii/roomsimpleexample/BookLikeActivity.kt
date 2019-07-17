package com.toeii.roomsimpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.toeii.roomsimpleexample.db.BookBean
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.StringBuilder

class BookLikeActivity : AppCompatActivity() {

    private lateinit var mTvLike: TextView
    private var mStringBuilder:StringBuilder = StringBuilder("")

    private lateinit var mDatas: List<BookBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)

        mTvLike = findViewById(R.id.tv_like_datas)

        doAsync {

            // Query like data for Room
            mDatas = App.instance?.db?.bookDao()?.getAllByLike(true)!!
            if(mDatas.isNotEmpty()){
                for(bookBean:BookBean in mDatas){
                    mStringBuilder.append(bookBean.bookName)
                    mStringBuilder.append(",")
                }
            }

            uiThread {
                if(mDatas.isNotEmpty()){
                    mTvLike.text = "喜欢的书：$mStringBuilder"
                }else{
                    mTvLike.text = "喜欢的书：暂无（请在主页添加）"
                }
            }

        }

    }
}
