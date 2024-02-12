package kr.co.lion.homework_memoapplication_bhangjm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityModifyBinding

class ModifyActivity : AppCompatActivity() {

    lateinit var activityModifyBinding: ActivityModifyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityModifyBinding = ActivityModifyBinding.inflate(layoutInflater)
        setContentView(activityModifyBinding.root)

        setToolbar()
        setView()

    }

    // toobar 설정
    fun setToolbar(){
        activityModifyBinding.apply {
            toolbarModify.apply {
                // 타이틀
                title = "메모 수정"
                // Back
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                // 메뉴
                inflateMenu(R.menu.menu_modify)
                setNavigationOnClickListener {
                    modifyData()
                    finish()
                    true
                }
            }
        }

    }

    // View 설정
    fun setView(){
        activityModifyBinding.apply {

            //  순서값 추출
            val position = intent.getIntExtra("position", 0)
            // position 번째 객체를 추출한다
            val memoModify = Util.memoList[position]

            // 기본 공통 사항 (작업 날짜 빼고 일단)
            textFieldModifyTitle.setText(memoModify.title)
            textFieldModifyMemo.setText(memoModify.memo)

        }

    }

    // 수정 처리
    fun modifyData(){
        // 위치 값을 가져 온다
        val position = intent.getIntExtra("position", 0)
        // position 번째 객체를 가져온다
        val memoModify = Util.memoList[position]

        activityModifyBinding.apply {
            // 기본 공통 사항
            memoModify.title = textFieldModifyTitle.text.toString()
            memoModify.memo = textFieldModifyMemo.text.toString()
            // 작성 날짜 ; 이걸 어떻게 한다?

        }

    }
}