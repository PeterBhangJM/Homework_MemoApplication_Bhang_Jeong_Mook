package kr.co.lion.homework_memoapplication_bhangjm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityShowInfoBinding

class ShowInfoActivity : AppCompatActivity() {

    lateinit var activityShowInfoBinding: ActivityShowInfoBinding

    // Activity 런처
    lateinit var modifyActivityLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowInfoBinding = ActivityShowInfoBinding.inflate(layoutInflater)
        setContentView(activityShowInfoBinding.root)

        setLauncher()
        setToolbar()
        setView()
    }

    // 런처 설정
    fun setLauncher(){
        // ModifyActivity 런처
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyActivityLauncher = registerForActivityResult(contract1){

        }
    }

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
                // 메뉴
                inflateMenu(R.menu.menu_showinfo)
                setOnMenuItemClickListener {
                    // 사용자가 선택한 메뉴 항목의 id로 분기한다
                    when (it.itemId){
                        // 수정
                        R.id.menu_item_modification ->{
                            // ModifyActivity 런처
                            val modifyIntent = Intent(this@ShowInfoActivity, ModifyActivity::class.java)

                            // 메모 순서값을 저장한다
                            val position = intent.getIntExtra("position", 0)
                            modifyIntent.putExtra("position", position)

                            modifyActivityLauncher.launch(modifyIntent)
                        }

                        // 삭제
                        R.id.memu_item_delete -> {
                            // 항목 순서값을 가져온다
                            val position = intent.getIntExtra("position", 0)
                            // 항목 번째 객체를 리스트에서 제거한다
                            Util.memoList.removeAt(position)
                            finish()
                        }
                    }

                    true
                }
            }
        }

    }

    // View 설정
    fun setView(){
        activityShowInfoBinding.apply {
        // TextView
            textViewShowInfo.apply {
                // 항목 순서값을 가져온다
                val position = intent.getIntExtra("position", 0)
                // 포지션 번째 객체를 추출한다
                val memoShow = Util.memoList[position]

                // 기본 사항들
                text = "작성 날짜 :  \n"
                append("제목 : ${memoShow.title}\n")
                append("내용 : ${memoShow.memo}\n")

            }
        }
    }

    // 다른 곳 갔다 왓을 때 출력내용을 다시 구성
    override fun onResume() {
        super.onResume()
        setView()
    }
}