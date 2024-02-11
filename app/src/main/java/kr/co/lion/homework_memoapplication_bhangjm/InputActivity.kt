package kr.co.lion.homework_memoapplication_bhangjm

import android.content.Intent
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

        setToolbar()
        setView()

    }

    // toolbar 설정
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
        activityInputBinding.apply {
            // 내용 입력
            // 엔터키를 누르면 입력 완료 처리를 한다
            textFieldInputMemo.setOnEditorActionListener { v, actionId, event ->
                processInputDone()
                true
            }

        }

    }

    // 입력 완료 처리
    fun processInputDone() {
        activityInputBinding.apply {

            // 입력한 내용을 가져온다
            val title = textFieldInputTitle.text.toString()
            // 내용을 memo로 설정
            val memo = textFieldInputMemo.text.toString()

            // 입력받은 정보를 객체에 담아준다
            val meomoData = MemoData(title, memo)

            // 이전으로 돌아간다
            val resultIntent = Intent()
            resultIntent.putExtra("memoData", meomoData )

            setResult(RESULT_OK, resultIntent)
            finish()
            setResult(RESULT_OK)
            finish()

        }

    }
}