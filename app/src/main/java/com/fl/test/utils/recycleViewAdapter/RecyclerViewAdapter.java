package com.fl.test.utils.recycleViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fl.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_HEADER = 0;
    private static final int ITEM_VIEW_TYPE_ITEM = 1;
    private static final int ITEM_VIEW_TYPE_FOOTER = 2;
    private final View header;
    private final View footer;
    private int headerCount = 0;
    private int footerCount = 0;
    private final List<T> beans;

    public RecyclerViewAdapter(View header, View footer, List<T> beans) {
        headerCount = header == null ? 0 : 1;
        footerCount = footer == null ? 0 : 1;
        this.header = header;
        this.footer = footer;
        this.beans = beans;
    }

    public boolean isHeader(int position) {
        if (headerCount == 0) {
            return false;
        }
        return position == 0;
    }

    public boolean isFooter(int position) {
        if (footerCount == 0) {
            return false;
        }
        return position == beans.size() + headerCount;// add 1 for footer
//      return position == getItemCount()-headerCount;// or Subtract 1 for footer
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_HEADER) {
            return new ViewHolder(header);
        }
        if (viewType == ITEM_VIEW_TYPE_FOOTER) {
            return new ViewHolder(footer);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tv, parent, false);
        return new ViewHolder<T>(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (isHeader(position)) {
            return;
        }
        if (isFooter(position)) {
            return;
        }
        T bean = beans.get(position - headerCount);
        holder.tv.setText(bean.toString());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return ITEM_VIEW_TYPE_HEADER;
        }
        if (isFooter(position)) {
            return ITEM_VIEW_TYPE_FOOTER;
        }
        return ITEM_VIEW_TYPE_ITEM;
    }

    /**
     * 获得实际View的数量，包括header和footer
     */
    @Override
    public int getItemCount() {
        return beans.size() + headerCount + footerCount;
    }


    class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            if (onItemClickLitener != null) {
                itemView.setOnClickListener(this);
            }
            if (onItemLongClickLitener != null) {
                itemView.setOnLongClickListener(this);
            }

        }

        @Override
        public void onClick(View v) {
            //getPosition()被废弃了
            onItemClickLitener.onItemClick(v, getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickLitener.onItemLongClick(v, getLayoutPosition());
            return true;
        }
    }

    private static OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public interface OnItemClickLitener {
        /**
         * 长按回调，注意position，指的是在Adapter中的位置，已经算上header
         *
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);
    }

    private static OnItemLongClickLitener onItemLongClickLitener;

    public void setOnItemLongClickLitener(OnItemLongClickLitener onItemLongClickLitener) {
        this.onItemLongClickLitener = onItemLongClickLitener;
    }

    public interface OnItemLongClickLitener {
        /**
         * 长按回调，注意position，指的是在Adapter中的位置，已经算上header
         *
         * @param view
         * @param position
         */
        void onItemLongClick(View view, int position);
    }

    /**
     * 在position位置插入item
     *
     * @param position:指的是item在beans中的位置，而不是在Adapter中的位置； 所以在notifyItemInserted的时候需要算上header
     * @param item
     */
    public void addItemAt(int position, T item) {
        beans.add(position, item);
        notifyItemInserted(position + headerCount);
    }

    /**
     * 在底部添加Items
     *
     * @param items
     */
    public void addItemsAtLast(ArrayList<T> items) {
        int size = beans.size();
        beans.addAll(items);
        notifyItemRangeInserted(size + headerCount, items.size());
    }

    /**
     * 移除position位置的Item
     *
     * @param position :指的是item在beans中的位置，而不是在Adapter中的位置；
     *                 所以在notifyItemInserted的时候需要算上header
     */
    public void removeItemAt(int position) {
        beans.remove(position);
        notifyItemRemoved(position + headerCount);
    }

    /**
     * 获得中间Item的数量，不包括header和footer
     *
     * @return
     */
    public int getItemSize() {
        return beans.size();
    }

}
/**
 * 使用案例
 */
//    View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.footer_loading, null);
//    adapterR = new RecyclerViewAdapter<String>(inflate,null,datas);
//    card_recycler_view.setAdapter(adapterR);
//    adapterR.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
//        @Override
//        public void onItemClick(View view, int position) {
//            if (adapterR.isHeader(position)) {
//                Toast.makeText(getApplicationContext(), "header", Toast.LENGTH_SHORT).show();
//            } else if (adapterR.isFooter(position)) {
//                Toast.makeText(getApplicationContext(), "footer ", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), " item " + position, Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//    adapterR.setOnItemLongClickLitener(new RecyclerViewAdapter.OnItemLongClickLitener() {
//        @Override
//        public void onItemLongClick(View view, int position) {
//            if (adapterR.isHeader(position)) {
//                Toast.makeText(getApplicationContext(), "header clicked", Toast.LENGTH_SHORT).show();
//            } else if (adapterR.isFooter(position)) {
//                Toast.makeText(getApplicationContext(), "footer clicked", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "header item " + position, Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//    card_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            boolean isBottom=manager.findLastCompletelyVisibleItemPosition()>=adapterR.getItemCount()-1;
//            if (isBottom){
//                ArrayList<String> datas=new ArrayList<String>();
//                for (int i = 0; i < 5; i++) {
//                    datas.add("----"+i);
//                }
//                adapterR.addItemsAtLast(datas);
//            }
//        }
//
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//        }
//    });

