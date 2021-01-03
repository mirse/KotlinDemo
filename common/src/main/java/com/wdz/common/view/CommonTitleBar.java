package com.wdz.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.wdz.common.R;
import com.wdz.common.util.ScreenUtils;


/**
 * 通用titleBar，高度自适应，宽度match
 * @author wdz
 */
public class CommonTitleBar extends ConstraintLayout {
    private OnClickListener onClickListener;
    private TextView tvTitleName;
    private ImageView backImg;
    private ImageView closeImg;
    private ConstraintLayout titleBarBg;
    private TypedArray typedArray;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView textRight;

    public CommonTitleBar(Context context) {
        this(context,null);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);

    }

    /**
     * 加载视图
     * @param context
     * @param attrs
     */
    private void initView(Context context, AttributeSet attrs) {
        initUi(context);
        initStyle(context,attrs);


    }

    /**
     * 初始化ui
     * @param context
     */
    private void initUi(Context context) {
        int statusBarHeight = ScreenUtils.getStatusBarHeight(context);
        View view = View.inflate(context, R.layout.title_bar_common, this);
        titleBarBg = findViewById(R.id.cl_root);
        tvTitleName = view.findViewById(R.id.tv_title);
        textRight = view.findViewById(R.id.text_right);
        backImg = view.findViewById(R.id.iv_nav_back);
        leftImg = view.findViewById(R.id.iv_left);
        closeImg = view.findViewById(R.id.iv_nav_close);
        rightImg = view.findViewById(R.id.iv_right);
        LayoutParams params = (LayoutParams) titleBarBg.getLayoutParams();
        params.height = dp2px(context,44) + statusBarHeight;
        params.width = LayoutParams.MATCH_PARENT;
        titleBarBg.setLayoutParams(params);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onBackPress();
                }
            }
        });
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener!=null){
                    onClickListener.onClosePress();
                }
            }
        });
        rightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onSettingPress();
                }
            }
        });
        textRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.onClickRightText();
                }
            }
        });
    }


    /**
     * 加载标题style
     * @param context
     * @param attrs
     */
    private void initStyle(Context context,AttributeSet attrs) {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar);
        //设置背景颜色
        if (typedArray.hasValue(R.styleable.CommonTitleBar_background_color)){
            titleBarBg.setBackgroundColor(typedArray.getColor(R.styleable.CommonTitleBar_background_color,0xFFFFFF));
        }
        if (typedArray.hasValue(R.styleable.CommonTitleBar_title)){
            setTitle();
        }
        //最左侧图标 -- back
        if (typedArray.hasValue(R.styleable.CommonTitleBar_icon_back)){
            backImg.setImageResource(typedArray.getResourceId(R.styleable.CommonTitleBar_icon_back,R.mipmap.ic_return));
        }
        //左二图标 -- 预留
        if (typedArray.hasValue(R.styleable.CommonTitleBar_icon_left)){
            leftImg.setImageResource(typedArray.getResourceId(R.styleable.CommonTitleBar_icon_left,R.mipmap.ic_return));
        }
        //最右侧图标 -- close
        if (typedArray.hasValue(R.styleable.CommonTitleBar_icon_right)){
            rightImg.setImageResource(typedArray.getResourceId(R.styleable.CommonTitleBar_icon_right,R.mipmap.ic_add));
        }
        //右二图标 -- 一般为设置图标...
        if (typedArray.hasValue(R.styleable.CommonTitleBar_icon_close)){
            closeImg.setImageResource(typedArray.getResourceId(R.styleable.CommonTitleBar_icon_close,R.mipmap.ic_add));
        }

        //右侧文字
        if (typedArray.hasValue(R.styleable.CommonTitleBar_text_right)){
            textRight.setText(typedArray.getString(R.styleable.CommonTitleBar_text_right));
        }
    }

    /**
     * 设置标题相关文字、颜色、大小、样式
     */
    private void setTitle() {
        tvTitleName.setText(typedArray.getString(R.styleable.CommonTitleBar_title));
        if (typedArray.hasValue(R.styleable.CommonTitleBar_title_size)){
            tvTitleName.setTextSize(typedArray.getDimensionPixelSize(R.styleable.CommonTitleBar_title_size,sp2px(getContext(),18)));
        }
        if (typedArray.hasValue(R.styleable.CommonTitleBar_title_color)){
            tvTitleName.setTextColor(typedArray.getColor(R.styleable.CommonTitleBar_title_color,0x333333));
        }
        if (typedArray.hasValue(R.styleable.CommonTitleBar_title_style)){
            //只有常规与加粗两种
            if (typedArray.getInt(R.styleable.CommonTitleBar_title_style,0) == 0){
                tvTitleName.setTypeface(Typeface.DEFAULT);
            }
            else if (typedArray.getInt(R.styleable.CommonTitleBar_title_style,0) == 1){
                tvTitleName.setTypeface(Typeface.DEFAULT_BOLD);
            }

        }

    }

    /**
     * 获取bar高度
     * @return
     */
    public int getBarHeight(){
        return titleBarBg.getHeight();
    }

    /**
     * 设置标题名称
     * @param titleName
     */
    public void setTitleName(String titleName){
        tvTitleName.setText(titleName);
    }



    /**
     * 设置TitleBar的点击事件
     * @param onClickListener
     */
    public void setOnTitleBarClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    /**
     * 点击监听
     */
    public interface OnClickListener{
        /**
         * 点击返回按钮
         */
        void onBackPress();
        /**
         * 点击关闭按钮
         */
        void onClosePress();
        /**
         * 点击设置按钮
         */
        void onSettingPress();
        /**
         * 点击右侧文本
         */
        void onClickRightText();
    }



    /**
     * sp -> px
     *
     * @param context
     * @param spValue
     * @return
     */
    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dp->px
     * @param context  context
     * @param dipValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
