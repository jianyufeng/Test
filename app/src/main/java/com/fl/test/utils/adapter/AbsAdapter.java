package com.fl.test.utils.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/3/29.
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
    public Context context;
    private List<T> datas;
    private int LayoutID;

    public AbsAdapter(Context context, List<T> datas, int LayoutID) {
        this.context = context;
        this.datas = datas;
        this.LayoutID = LayoutID;
    }

    public void addData(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public void refreshData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(LayoutID, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        showItemConten(viewHolder, datas.get(position), parent);
        return convertView;
    }

    public abstract void showItemConten(ViewHolder viewHolder, T data, ViewGroup parent);

    public class ViewHolder {
        private SparseArray<View> viewCaches = new SparseArray<>();//已经查找到的UI控件的缓存
        public View itemView;

        private ViewHolder(View itemView) {
            this.itemView = itemView;
        }

        /**
         * 得到item中的View
         *
         * @return
         */
        public View getLayoutView(int itemChildViewId) {

            return getChildView(itemChildViewId);
        }

        public GridView getItemGridView(int id) {
            GridView gv = (GridView) getChildView(id);

            return gv;
        }

        private View getChildView(int itemChildViewId) {
            View view = viewCaches.get(itemChildViewId);

            if (view == null) {
                view = itemView.findViewById(itemChildViewId);
                if (view != null) {
                    viewCaches.put(itemChildViewId, view);
                }
            }
            return view;
        }

        public void setTextViewContent(int itemChildViewId, String content) {
            TextView childView = (TextView) getChildView(itemChildViewId);
            if (childView != null) {
                childView.setText(content);
            }
        }

        //此处使用了Xutils框架的bitmap模块
        public void setImageViewContent(int itemChildViewId, String url) {

            final ImageView childView = (ImageView) getChildView(itemChildViewId);

            if (childView != null) {
                x.image().bind(childView, url);
            }
        }
    }
}
