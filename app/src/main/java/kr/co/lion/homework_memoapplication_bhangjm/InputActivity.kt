package kr.co.lion.homework_memoapplication_bhangjm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)


    }

    // toolbar 설정
    fun setToolbar (){
        activityInputBinding.apply {
            toolbarInput.apply {
                // 타이틀
                title = "메모 작성"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.memu_input)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_input_done -> {
                            // 입력 처리
                        }
                    }
                    true
                }
            }
        }
    }


    // View 설정
    fun setView (){

    }
}