package com.toeii.roomsimpleexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toeii.roomsimpleexample.db.BookBean
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTvLike: TextView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var mBookAdapter: BookAdapter
    private lateinit var mDatas: List<BookBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        mRecyclerView = findViewById(R.id.rv_book)
        mTvLike = findViewById(R.id.tv_like)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = mLayoutManager

        mTvLike.setOnClickListener {
            startActivity(Intent(this,BookLikeActivity::class.java))
        }

        doAsync {
            // Query data for Room
            mDatas = App.instance?.db?.bookDao()?.getAll()!!
            if(mDatas.isEmpty()){
                initData()
            }
            uiThread {
                mBookAdapter = BookAdapter(this@BookActivity,mDatas)
                mRecyclerView.adapter = mBookAdapter
                mBookAdapter.notifyDataSetChanged()
            }

        }

    }

    private fun initData() {

        val bookStore = arrayOf(   "《窗外》",
            "《幸运草》",
            "《六个梦》 ",
            "《烟雨蒙蒙》",
            "《菟丝花》",
            "《几度夕阳红》 ",
            "《潮声》",
            "《船》",
            "《紫贝壳》",
            "《寒烟翠》 ",
            "《月满西楼》",
            "《翦翦风》",
            "《彩云飞》",
            "《庭院深深》 ",
            "《星河》",
            "《水灵》",
            "《白狐》",
            "《海鸥飞处》 ",
            "《心有千千结》",
            "《一帘幽梦》",
            "《浪花》",
            "《碧云天》 ",
            "《女朋友》",
            "《在水一方》",
            "《水灵》",
            "《白狐》",
            "《幸运草》",
            "《秋歌》",
            "《人在天涯》 ",
            "《我是一片云》",
            "《月朦胧·鸟朦胧》",
            "《雁儿在林梢》",
            "《一颗红豆》 ",
            "《彩霞满天》",
            "《金盏花》",
            "《梦的衣裳》",
            "《聚散两依依》 ",
            "《却上心头》",
            "《问斜阳》",
            "《燃烧吧!火鸟》",
            "《昨夜之灯》 ",
            "《匆匆,太匆匆》 ",
            "《失火的天堂》",
            "《我的故事》 ",
            "《冰儿》 ",
            "《剪不断的乡愁》",
            "《雪珂》",
            "《望夫崖》",
            "《又见一帘幽梦》")
        val books: MutableList<BookBean> = mutableListOf()

        for ((index, bookName) in bookStore.withIndex()){
            val book = BookBean(index,bookName,false)
            books.add(book)
        }

        App.instance?.db?.bookDao()?.insertAll(books)

        mDatas = books
    }
}
