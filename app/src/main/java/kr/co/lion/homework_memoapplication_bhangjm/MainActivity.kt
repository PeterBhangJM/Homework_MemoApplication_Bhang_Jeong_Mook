package kr.co.lion.homework_memoapplication_bhangjm

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.homework_memoapplication_bhangjm.databinding.ActivityMainBinding
import kr.co.lion.homework_memoapplication_bhangjm.databinding.RowMainBinding
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // InputActivity의 런처
    lateinit var inputActivityLauncher: ActivityResultLauncher<Intent>

    //ShowInfoActivity의 런처
    lateinit var showInfoActivityLauncher:ActivityResultLauncher<Intent>

    // 메모의 정보를 담을 리스트
    // val memoList = mutableListOf<MemoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        initData()
        setToolbar()
        setView()
        // setEvent()

    }


    // 기본 데이터 및 객체 세팅 (런처 포함)
    fun initData (){
        // InputActivity 런처 등록
        val contract1 = ActivityResultContracts.StartActivityForResult()
        inputActivityLauncher = registerForActivityResult(contract1) {
            // 작업 결과가 OK라면
            if (it.resultCode == RESULT_OK){
                // 전달된 Intent 객체가 있다면
                if (it.data !=null){
                    // 메모 객체를 추출한다
                    if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                        val memoData = it.data?.getParcelableExtra("memoData", MemoData::class.java)
                        Util.memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    } else {
                        val memoData = it.data?.getParcelableExtra<MemoData>("memoData")
                        Util.memoList.add(memoData!!)
                        activityMainBinding.recyclerViewMain.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }

        // ShowActivity 런처  등록
        val contract2 = ActivityResultContracts.StartActivityForResult()
        showInfoActivityLauncher = registerForActivityResult(contract2){

        }
        // ModifyActivity 런처 등록
    }
    override fun onResume() {
        super.onResume()
        activityMainBinding.apply {
            // 리사이클러뷰 갱신
            recyclerViewMain.adapter?.notifyDataSetChanged()

        }
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
                    //  메뉴의 id로 분기할 수 있으면 분기
                    when (it.itemId) {
                        // 더하기 메뉴
                        R.id.menu_item_main -> {
                            // 슬라이드 2로 이동
                            // InputActivity를 실행한다.
                            val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                            inputActivityLauncher.launch(inputIntent)
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
    inner class RecyclerViewMainAdapter : RecyclerView.Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {
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
                this.rowMainBinding.root.setOnClickListener {
                    // // ShowInfoActivity를 실행한다.
                    val showInfoIntent = Intent(this@MainActivity, ShowInfoActivity::class.java)
                    // 선택한 항목 번째의 학생 객체를 Intent에 담아준다
                    showInfoIntent.putExtra("position", Util.memoList[adapterPosition])

                    showInfoActivityLauncher.launch(showInfoIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)

            return viewHolderMain
        }

        override fun getItemCount(): Int {
            // return 20
            return Util.memoList.size
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            // 날짜는 현재 날짜를 구해 사용한다.
            val date: LocalDate = LocalDate.now()
            holder.rowMainBinding.textViewDate.text = "작성 날짜 : ${date}"
            holder.rowMainBinding.textViewTitle.text = "제목 : ${Util.memoList[position].title} "
        }
    }


}