package com.example.newproject.view.ui.base

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.databinding.ItemLoadMoreBinding
import com.example.newproject.databinding.ItemLoadMoreHorizontalBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

//제네릭 T : Type U: ViewHolder -> 바인딩 되어있으니까 바인딩 되어있는 그값
abstract class LoadMoreBaseAdapter<T, U : ViewDataBinding>(
    val context: Context,
    val itemResourceId: Int,
    val recyclerView: RecyclerView,
    val loadMoreEnable: Boolean = true,
    val loadMoreSize: Int = 20,
    val nestedScrollView: NestedScrollView? = null,
    val horizontalScrollView: HorizontalScrollView? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val items: ArrayList<T?> = ArrayList()
    private val TYPE_ITEM = 0
    private val TYPE_PROGRESS = 1
    private val LOAD_MORE_DELAY = 1000L
    var LOAD_MORE_TRESHHOLD_LINEAR = 4
    var LOAD_MORE_TRESHHOLD_GRID = 2

    var onStartLoadMore: (() -> Unit)? = null
    var viewHideFab: (() -> Unit)? = null
    var viewShowFab: (() -> Unit)? = null
    //로드모어를 계속해야되는지에 대한 true/false값
    private var endLoadMore = true

    //현재 로드모어가 실행되고있는지에 대한 true/false 값
    private var onLoadMore = true

    val layoutManager by lazy {
        recyclerView.layoutManager
    }
    val spanCount by lazy {
        (layoutManager as? GridLayoutManager)?.spanCount
    }

    val defaultItemHeight by lazy {
        layoutManager?.getChildAt(0)?.height
    }
    val defaultItemWidth by lazy {
        layoutManager?.getChildAt(0)?.width
    }

    init {
        initLoadMoreByType()
        (layoutManager as? GridLayoutManager)?.run {
            spanSizeLookup = object :
                GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (items[position] == null) spanCount else 1
                }
            }
        }
    }

    private fun initLoadMoreByType() {
        //로드모어가 언제 시작될지에 대한 구문
        if (nestedScrollView != null) {
            nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                if(scrollY>oldScrollY){
                    hideFab()
                }else{
                    showFab()
                }
                if (items.size > 0 && v.getChildAt(v.getChildCount() - 1) != null) {
                    if (scrollY + (defaultItemHeight
                            ?: 0 * (if (layoutManager is GridLayoutManager) LOAD_MORE_TRESHHOLD_GRID else LOAD_MORE_TRESHHOLD_LINEAR))
                        >= v.getChildAt(v.getChildCount() - 1).measuredHeight - v.measuredHeight
                        && scrollY > oldScrollY
                    ) {
                        startLoadMore()
                    }
                }
            }
        } else if (horizontalScrollView != null) {
            horizontalScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (items.size > 0 && horizontalScrollView.getChildAt(horizontalScrollView.getChildCount() - 1) != null) {
                    if (scrollX + (defaultItemWidth
                            ?: 0 * LOAD_MORE_TRESHHOLD_LINEAR) >= (horizontalScrollView.getChildAt(
                            horizontalScrollView.getChildCount() - 1
                        )
                            .measuredWidth - horizontalScrollView.measuredWidth)
                        && scrollX > oldScrollX
                    ) {
                        startLoadMore()
                    }
                }
            }
        } else {
            recyclerView.clearOnScrollListeners()
            //리사이클러 리스너가 지속적으로 생성되서 loadMore가 반복적으로 발생하는 오류 있었음 위에 구문으로 리스너 초기화 하고 실행하면 괜찮음
            //이후 프로젝트 중에 발생하면 비슷하게 리스너 초기화도 돌려보가
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
                    when {
                        layoutManager is GridLayoutManager -> {
                            val lastIndex =
                                (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                            if (lastIndex + (spanCount!! * LOAD_MORE_TRESHHOLD_GRID) >= items.size) {
                                startLoadMore()
                            }
                        }
                        layoutManager is LinearLayoutManager -> {
                            val lastIndex =
                                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                            if (lastIndex + LOAD_MORE_TRESHHOLD_LINEAR >= items.size) {
                                startLoadMore()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun startLoadMore() {
        if (items.size > 0 && !endLoadMore && !onLoadMore) {
            //위에 조건식이 필요한 이유는 LoadMore 조건이 반복될때를 피하려고, (ex) 위아래로 리사이클러뷰 깔짝거릴때
            onLoadMore = true
            items.add(null)
            notifyItemInserted(items.size - 1)
            Handler(Looper.getMainLooper()).postDelayed({
                onStartLoadMore?.invoke()
            }, LOAD_MORE_DELAY)
        }
    }

    private fun hideFab(){
        viewHideFab?.invoke()
        //invoke -> 사용방법으론 함수에 이름이 없이 사용가능하다는데 인터페이스 같은 방법으로 사용인지 lateinit같은 방식인지 헷갈림
        //간단하게 invoke는 이름을 부여한 해당 이름만으로 사용이 가능한 연산자 처럼 함수를 사용한다 (ex. +연산자, 람다 -> 람다는 invoke 함수를 갖고있는 객체 라고 설명함)
    }
    private fun showFab(){
        viewShowFab?.invoke()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_PROGRESS -> LoadMoreViewHolder(
                if (horizontalScrollView == null) {
                    ItemLoadMoreBinding.inflate(
                        LayoutInflater.from(
                            context
                        ), parent, false
                    )
                } else {
                    ItemLoadMoreHorizontalBinding.inflate(
                        LayoutInflater.from(
                            context
                        ), parent, false
                    )
                }
            )
            else -> ContentViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    itemResourceId,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_ITEM) {
            onBindView(holder as ContentViewHolder<U>, position)
        } else {
            holder.itemView.setOnClickListener { }
        }
    }

    abstract fun onBindView(contentViewHolder: ContentViewHolder<U>, position: Int)

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int): Int = items[position]?.let { TYPE_ITEM } ?: TYPE_PROGRESS

    class LoadMoreViewHolder(mBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(mBinding.root)
    //바인딩된 최상위를 뷰홀더에 넣어줌, -> 안에 id 들은 자동 맵핑

    class ContentViewHolder<T : ViewDataBinding>(val mBinding: T) :
        RecyclerView.ViewHolder(mBinding.root)


    fun addItems(newItems: ArrayList<T>) {
        val firstAdd = items.size == 0
        //위에 items는 addItems의 매개변수가 아닌 위에 먼저 선언된 items
        if (!firstAdd) {
            //처음 인서트가 아닐때 loading을 없애는 부분
            items.indexOfLast { it == null }.takeIf { it != -1 }?.let {
                //index값을 만들때 마지막에 null을 넣으니까 널값을 찾게 되면 프로그래스를 지워야 하니까 (추가로 불러올 필요 X)
                items.removeAt(it)
                //프로그래스를 지우고 최신화
                notifyItemRemoved(it)
            }
        }
        val startIndex = items.size
        items.addAll(newItems)
        if (newItems.size > 0) {
            if (firstAdd) {
                notifyDataSetChanged()
            } else {
                notifyItemRangeChanged(startIndex, items.size)
            }
        }
        endLoadMore = newItems.size < loadMoreSize
        onLoadMore = false
    }
}