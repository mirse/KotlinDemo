package com.wdz.common.base.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 带标题recyclerView
 * @param <T>
 */
public abstract class MultiTypeAdapter<T> extends BaseRecyclerViewAdapter {
    private static final String TAG = "MultiTypeAdapter";
    private List<T> mList = new ArrayList<>();
    public MultiTypeAdapter(Context mContext, List<T> list) {
        super(mContext, list);
        mList = list;
    }


    @Override
    public int getItemViewType(int position) {
        if(mList.isEmpty() && getEmptyLayoutId()!=0) {
            return VIEW_TYPE_EMPTY;
        }
        if (position==0 && getHeadLayoutId()!=0){
            return VIEW_TYPE_HEAD;
        }
        //判断是否是标题的代码逻辑
        if (isTitleItem(getRealPosition(position))) {
            return VIEW_TYPE_TITLE;
        }
        return super.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int type, Object data, int position) {
        if (type==VIEW_TYPE_TITLE){
            bindTitleData(holder,(T) data,position);
        }
        else if (type == VIEW_TYPE_NORMAL){
            bindData(holder,(T) data,position);
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId;
        if(viewType == VIEW_TYPE_TITLE){
            layoutId = getTitleId();
            View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
            if(view!=null) {
                return new TitleViewHolder(view);
            }
        }

        return super.onCreateViewHolder(parent, viewType);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (isTitleOneLine()) {
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == VIEW_TYPE_TITLE
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
        }

    }

    /**
     *
     * 设置title视图
     * @return titleId
     */
    public abstract int getTitleId();

    /**
     * 设置titleItem行状态
     * @return titleItem是否独立一行
     */
    public abstract boolean isTitleOneLine();

    /**
     * 是否是title判断
     * @param position
     * @return 是否是titleItem
     */
    public abstract boolean isTitleItem(int position);

    /**
     * 绑定数据源
     * @param holder
     * @param data
     * @param position
     */
    public abstract void bindData(BaseViewHolder holder,T data, int position);

    /**
     * 绑定数据源
     * @param holder
     * @param data
     * @param position
     */
    public abstract void bindTitleData(BaseViewHolder holder,T data, int position);


    class TitleViewHolder extends BaseViewHolder{
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
