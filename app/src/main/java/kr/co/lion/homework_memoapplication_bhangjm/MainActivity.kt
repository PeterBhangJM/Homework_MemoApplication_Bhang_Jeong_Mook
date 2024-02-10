package kr.co.lion.homework_memoapplication_bhangjm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityMainBinding
import kr.co.lion.homework_memoapplication_bhangjm.databinding.RowMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setLauncher()
        setToolbar()
        setView()
        setEvent()

    }

    // 런처 설정
    fun setLauncher (){

    }


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
                    when (it.itemId) {
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
    fun setView(){
        activityMainBinding.apply {
            // RecyclerView
            recyclerViewMain.apply {
                // 어뎁터
                adapter = RecyclerViewMainAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(this@MainActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }


    // 이벤트 설정
    fun setEvent(){

    }


    // RecyclerView 어뎁터 및 설정
    inner class RecyclerViewMainAdapter :
        RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {
        // 뷰홀더 클라스
        inner class ViewHolderMain(rowMainBinding: RowMainBinding) :
            RecyclerView.ViewHolder(rowMainBinding.root) {
            val rowMainBinding: RowMainBinding

            init {
                this.rowMainBinding = rowMainBinding
                // 항목 클릭시 전체가 클릭이 될 수 있도록
                // 가로 세로 길이를 설정해준다.
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // 항목을 눌렀을 때의 리스너
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            return 100
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // 날짜는 현재 날짜를 구해 사용한다.
            val date: LocalDate = LocalDate.now()
            holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${date}"
            holder.rowMainBinding.textViewTitle.text = "제목 : "
        }
    }


}