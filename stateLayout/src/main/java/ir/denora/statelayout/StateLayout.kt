package ir.denora.statelayout

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi

class StateLayout : FrameLayout {

    //  region Constructors
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(attrs, defStyleAttr, defStyleRes)
    }
    //  endregion

    var onStateChangedListener: OnStateChangedListener? = null

    private lateinit var userDefinedDefaultState: State

    private lateinit var loadingLayoutView: View
    private lateinit var emptyLayoutView: View
    private lateinit var notFoundLayoutView: View
    private lateinit var noNetworkLayoutView: View
    private lateinit var errorLayoutView: View

    private var state: State = ContentState
        set(value) {
            //  Check and ignore the request if we are setting the same value
            if (field == value) return
            //  Set the field
            field = value
            //  Change UI
            changeState()
            //  Inform user using the listener
            onStateChangedListener?.onStateChanged(value)
        }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //  Check if there are more than one child
        if (childCount > 1) throw IllegalStateException("StateLayout can host only one direct child")
        //  Set default state which user has set
        state = userDefinedDefaultState
    }

    private fun init(attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        //  Get attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StateLayout, defStyleAttr, defStyleRes)
        //  Apply attribute values entered by user
        applyAttrValues(typedArray)
        //  Recycle is needed
        typedArray.recycle()
    }

    private fun applyAttrValues(typedArray: TypedArray) {

        val loadingLayoutResId = typedArray.getResourceId(
            R.styleable.StateLayout_loadingLayout,
            R.layout.default_loading_layout
        )
        loadingLayoutView = LayoutInflater.from(context).inflate(loadingLayoutResId, null)

        val emptyLayoutResId = typedArray.getResourceId(
            R.styleable.StateLayout_emptyLayout,
            R.layout.default_empty_layout
        )
        emptyLayoutView = LayoutInflater.from(context).inflate(emptyLayoutResId, null)

        val notFoundLayoutResId = typedArray.getResourceId(
            R.styleable.StateLayout_notFoundLayout,
            R.layout.default_not_found_layout
        )
        notFoundLayoutView = LayoutInflater.from(context).inflate(notFoundLayoutResId, null)

        val noNetworkLayoutResId = typedArray.getResourceId(
            R.styleable.StateLayout_noNetworkLayout,
            R.layout.default_no_network_layout
        )
        noNetworkLayoutView = LayoutInflater.from(context).inflate(noNetworkLayoutResId, null)

        val errorLayoutResId = typedArray.getResourceId(
            R.styleable.StateLayout_errorLayout,
            R.layout.default_error_layout
        )
        errorLayoutView = LayoutInflater.from(context).inflate(errorLayoutResId, null)


        val defaultLayout = typedArray.getInt(R.styleable.StateLayout_defaultLayout, 0)
        userDefinedDefaultState = when (defaultLayout) {
            0 -> ContentState
            1 -> LoadingState
            2 -> EmptyState
            3 -> NotFoundState
            4 -> NoNetworkState
            5 -> ErrorState
            else -> ContentState
        }
    }

    private fun changeState() {
        if (childCount > 1)
            removeViewAt(1)

        val view = when (state) {
            ContentState -> return
            LoadingState -> loadingLayoutView
            EmptyState -> emptyLayoutView
            NotFoundState -> notFoundLayoutView
            NoNetworkState -> noNetworkLayoutView
            ErrorState -> errorLayoutView
        }

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )

        addView(view, layoutParams)
    }

    fun showContent() {
        state = ContentState
    }

    fun showLoading() {
        state = LoadingState
    }

    fun showEmpty() {
        state = EmptyState
    }

    fun showNotFound() {
        state = NotFoundState
    }

    fun showNoNetwork() {
        state = NoNetworkState
    }

    fun showError() {
        state = ErrorState
    }

}
