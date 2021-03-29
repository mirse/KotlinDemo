package com.wdz.common.databind;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.wdz.common.base.adapter.BaseRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * 通用adapter定义normalItem与emptyItem
 * @author wdz
 */
public abstract class BaseBindRecyclerViewAdapter extends RecyclerView.Adapter<BaseBindRecyclerViewAdapter.BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    /**
     * 数据源
     */
    private List mList = new ArrayList<>();
    /**
     * 普通item布局
     */
    static final int VIEW_TYPE_NORMAL = 1;
    /**
     * 空布局
     */
    static final int VIEW_TYPE_EMPTY = 2;
    /**
     * 带标题布局
     */
    static final int VIEW_TYPE_TITLE = 3;
    /**
     * 头布局
     */
    static final int VIEW_TYPE_HEAD = 4;




    /**
     * 获取layoutId
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 获取头布局layoutId
     *
     * @return =0 代表没有HeadView
     */
    public abstract int getHeadLayoutId();

    /**
     * 获取空布局layoutId
     *
     * @return =0 代表没有emptyView
     */
    public abstract int getEmptyLayoutId();


    /**
     * 将数据与view绑定
     *
     * @param type
     * @param data
     * @param position 点击位置
     */
    public abstract void onBindViewHolder(ViewDataBinding binding,BaseViewHolder viewHolder,int type, Object data, int position);




    public BaseBindRecyclerViewAdapter( List list) {

        this.mList =list;
    }

    /**
     * 返回数据源
     * @return
     */
    protected List getData(){
        return mList;
    }



    @Override
    public int getItemViewType(int position) {
        if(mList.isEmpty() && getEmptyLayoutId()!=0){
            return VIEW_TYPE_EMPTY;
        }
        if (position==0 && getHeadLayoutId()!=0){
            return VIEW_TYPE_HEAD;
        }
        return VIEW_TYPE_NORMAL;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = null;
        if(viewType == VIEW_TYPE_EMPTY){
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getEmptyLayoutId(), parent, false);

        }
        else if(viewType == VIEW_TYPE_HEAD){
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getHeadLayoutId(), parent, false);
        }
        else if(viewType == VIEW_TYPE_NORMAL){
            viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        }
        return viewDataBinding == null? null:new BaseViewHolder(viewDataBinding.getRoot());
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        int type = getItemViewType(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (type!= VIEW_TYPE_EMPTY && type!=VIEW_TYPE_HEAD){
            final int realPosition = getRealPosition(position);
            final Object object = mList.get(realPosition);
            onBindViewHolder(binding,holder,type,mList.get(realPosition),realPosition);

            //标题不添加点击事件
            if (type!=VIEW_TYPE_TITLE){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickNormal(object,realPosition);
                        }
                    }
                });
                //全部都加上长按监听
                holder.itemView.setLongClickable(true);
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (onItemLongClickListener != null) {
                            onItemLongClickListener.onLongClick(object,realPosition,v);
                        }
                        return true;
                    }
                });
            }

        }
    }

    /**
     * 获取正确点击位置
     * @param position
     * @return
     */
    public int getRealPosition(int position){
        if(position>0&&getHeadLayoutId()!=0){
            return position-1;
        }
        else{
            return position;
        }
    }


    /**
     * 点击事件
     */
    public interface OnItemClickListener {
        /**
         * 通用点击事件
         * @param object
         * @param position
         */
        void onClickNormal(Object object, int position);
    }

    public interface OnItemLongClickListener{
        /**
         * 通用长点击事件
         * @param object
         * @param position
         * @param view
         */
        void onLongClick(Object object, int position, View view);
    }

    /**
     * 设置点击监听
     *
     * @param listener
     */
    public void setOnClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }



    @Override
    public int getItemCount() {
        int itemCount = mList.size();
        if (0 != getEmptyLayoutId() && itemCount == 0) {
            //总数0变为1
            return 1;
        }
        else if (getHeadLayoutId()!=0){
            itemCount += 1;
        }
        return itemCount;
    }


    class HeadViewHolder extends BaseViewHolder{
        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class EmptyViewHolder extends BaseViewHolder{
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views;

        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray<>();
        }

        /**
         * 获取id所对应的控件
         *
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = views.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                views.put(id, view);
            }
            return view;
        }
    }





}
