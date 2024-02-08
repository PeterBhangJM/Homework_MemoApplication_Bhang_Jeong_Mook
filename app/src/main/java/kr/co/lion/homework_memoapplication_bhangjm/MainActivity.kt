package kr.co.lion.homework_memoapplication_bhangjm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }

    // 런처 설정


    // 툴바 설정


    // View 설정


    // 이벤트 설정


    // RecyclerView 어뎁터 및 설정


}