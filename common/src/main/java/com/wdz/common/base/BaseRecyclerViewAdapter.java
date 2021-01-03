package com.wdz.common.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 适用于单一item布局
 *
 * @author wdz
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    //数据源
    private List<T> mList = new ArrayList<>();
    //头布局
    private static final int VIEW_TYPE_HEADER = 0;
    //普通item布局
    private static final int VIEW_TYPE_NORMAL = 1;
    //空布局
    private static final int VIEW_TYPE_EMPTY = 2;


    private final LayoutInflater mInflater;

    /**
     * 获取空布局layoutId
     *
     * @return =0 代表没有emptyView
     */
    public abstract int getEmptyLayoutId();
    /**
     * 获取头view
     *
     * @return =0 代表没有headView
     */
    public abstract int getHeaderLayoutId();

    /**
     * 获取layoutId
     *
     * @return
     */
    public abstract int getLayoutId();

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public Context getmContext() {
        return mContext;
    }


    /**
     * 给MyItemList设置数据
     *
     * @param list
     */
    public void setDatas(List<T> list) {
        if(mList!=null) {
            mList.clear();
            mList.addAll(list);
            refreshDatas();
        }
        else{
            ;
        }
    }
    //插入具体数据
    public void setDatas(int position , T t){
        if(mList!=null&&mList.size()>=position+1){
            mList.set(position,t);
            refreshDatas();
        }
        else{
            ;
        }
    }

    /**
     * 增加数据
     * @param t
     */
    public void addData(T t){
        if(mList!=null){
            mList.add(t);
            refreshDatas();
        }
        else{

        }
    }

    //删除具体数据
    public void deleteData(int position){
        if(mList!=null&&mList.size()>=position+1){
            mList.remove(position);
            refreshDatas();
        }
        else{

        }
    }
    //清空
    public void deleteAll(){
        if(mList!=null){
            mList.clear();
            refreshDatas();
        }
    }

    /**
     * 获取所有的内容
     * @return
     */
    public List<T> getDatas(){
        return mList;
    }

    /**
     * 刷新数据
     */
    public void refreshDatas() {
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int uiPosition) {
        if(uiPosition==0&&getHeaderLayoutId()!=0){
            return VIEW_TYPE_HEADER;
        }
        if(getEmptyLayoutId()!=0&&mList.isEmpty()){
            return VIEW_TYPE_EMPTY;
        }
        return VIEW_TYPE_NORMAL;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = getLayoutId();
        if (viewType == VIEW_TYPE_HEADER){
            layoutId = getHeaderLayoutId();
            View view = mInflater.inflate(layoutId, parent, false);
            if(view!=null) {
                return new HeadViewHolder(view);
            }
        }
        else if(viewType == VIEW_TYPE_NORMAL){
            layoutId = getLayoutId();
            View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
            if(view!=null) {
                return new BaseViewHolder(view);
            }
        }
        else if(viewType == VIEW_TYPE_EMPTY){
            layoutId = getEmptyLayoutId();
            View view = mInflater.inflate(layoutId, parent, false);
            if(view!=null) {
                return new EmptyViewHolder(view);
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapter.BaseViewHolder holder, final int position) {



        int type = getItemViewType(position);
        if (type == VIEW_TYPE_HEADER) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClickHeader();
                    }
                }
            });
            bindHeaderData(holder);
        } else if (type == VIEW_TYPE_NORMAL) {
            holder.getView(position);
            final int realPosition = mesurePosition(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                    if (onItemClickListener != null) {
                        onItemClickListener.onClickNormal(realPosition);
                    }
                }
            });

            bindData(holder,mList.get(realPosition),realPosition);

        }

        //全部都加上长按监听
        holder.itemView.setLongClickable(true);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener != null) {
                    int realPosition = mesurePosition(position);
                    onItemLongClickListener.onLongClick(realPosition);
                }
                return true;
            }
        });

    }


    /**
     * 绘测postion的实际位置，由于headItem的影响
     * @param position
     * @return
     */
    public int mesurePosition(int position){
        if(position>0&&getHeaderLayoutId()!=0){
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
         * 头部点击事件
         */
        void onClickHeader();

        /**
         * 通用点击事件
         * @param position
         */
        void onClickNormal(int position);
    }

    public interface OnItemLongClickListener{
        void onLongClick(int position);
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


    /**
     * 将数据与view绑定
     *
     * @param holder
     * @param data
     * @param position 点击位置
     */
    public abstract void bindData(BaseViewHolder holder, T data, int position);

    /**
     * 将数据与view绑定
     *
     * @param holder
     */
    public abstract void bindHeaderData(BaseViewHolder holder);


    @Override
    public int getItemCount() {
        int itemCount = mList.size();
        if (0 != getEmptyLayoutId() && itemCount == 0) {
            //总数0变为1
            itemCount++;
        }
        if (0 != getHeaderLayoutId()) {
            itemCount++;
        }
        return itemCount;
    }

    /**
     * 设置headView是否需要单独一列
     *
     * @return
     */
    public abstract boolean isHeaderOnlyLine();

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (isHeaderOnlyLine()) {
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == VIEW_TYPE_HEADER
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
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

    class EmptyViewHolder extends BaseViewHolder{

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class HeadViewHolder extends BaseViewHolder{
        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
