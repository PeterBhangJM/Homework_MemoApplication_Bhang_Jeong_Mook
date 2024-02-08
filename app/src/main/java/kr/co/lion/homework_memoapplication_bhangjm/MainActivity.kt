package kr.co.lion.homework_memoapplication_bhangjm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

    }

    // 런처 설정


    // 툴바 설정
    fun setToolbar() {
        activityMainBinding.apply {
            toolbarMain.apply {
                // 타이틀
                title = "메모 관리"
                // 메뉴
                inflateMenu(R.menu.menu_main)
                // 리스너
                setOnMenuItemClickListener {
                    when (it.itemId){
                        // 더하기 메뉴
                        R.id.menu_item_main -> {
                            // 슬라이드 2로 이동
                        }
                        // 생략가능한 else 구문
                        else -> {
                        }
                    }

                    true
                }

            }
        }
    }


    // View 설정


    // 이벤트 설정


    // RecyclerView 어뎁터 및 설정


}