package kr.co.lion.homework_memoapplication_bhangjm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityInputBinding
import java.time.LocalDate

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding: ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)


    }

    // toolbar 설정
    @RequiresApi(Build.VERSION_CODES.O)
    fun setToolbar() {
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
                    when (it.itemId) {
                        R.id.menu_input_done -> {
                            // 입력 처리
                            processInputDone()
                        }
                    }
                    true
                }
            }
        }
    }


    // View 설정
    fun setView() {

    }

    // 입력 완료 처리
    @RequiresApi(Build.VERSION_CODES.O)
    fun processInputDone() {
        activityInputBinding.apply {
            // 입력한 내용을 가져온다

            val title = textFieldInputTitle.text.toString()
            // val memo = textFieldInputMemo.text.toString()

        }

    }
}