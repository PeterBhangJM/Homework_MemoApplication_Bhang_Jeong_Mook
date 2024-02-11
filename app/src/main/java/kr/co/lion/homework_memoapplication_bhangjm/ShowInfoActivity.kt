package kr.co.lion.homework_memoapplication_bhangjm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {

    lateinit var activityShowInfoBinding: ActivityShowInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowInfoBinding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(activityShowInfoBinding.root)
    }

    // 런처 설정

    //  툴바 설정
    fun setToolbar () {
        activityShowInfoBinding.apply {
            toolbarShowInfo.apply {
                // 타이틀
                title = "메모 보기"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
        }

    }


    // View 설정
    fun setView(){
        activityShowInfoBinding.apply {

        }
    }
}