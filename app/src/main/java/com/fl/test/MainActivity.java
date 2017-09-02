package com.fl.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fl.test.bean.Bean;
import com.fl.test.utils.http.OkHttpClientManager;
import com.fl.test.utils.screeUtils.ScreeUtils;
import com.fl.test.utils.stringUtils.StringUtils;

import org.xutils.x;

import okhttp3.Request;

public class MainActivity extends Activity {

//    private PullSwipeRefreshLayout pullSwipeRefreshLayout;
//    private TabHost tabHost;
//    private RecyclerView card_recycler_view;
//    private RenQiAdapter2 adapter;
//    private MyListView lv;
//    private MyAdapter<String> adapter1;
//    private RecyclerViewAdapter<String> adapterR;
TextView tv;
    private ImageView ig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.Ext.init(getApplication());
        x.Ext.setDebug(true);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        ig = (ImageView) findViewById(R.id.ig);
        String url = "http://192.168.0.188/index.php/index/ApiApply/apply/userId/8";
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<Bean>(){
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Bean bean) {
                tv.append(bean.getData().getProvince().get(0).getRegion_name()+"\n");
                tv.append("\n"+bean.getData().getMeetingType().get(0).getHuiyi_type());
                tv.append("\n"+bean.getData().getLecturerInfo().get(3).getXingming());
                tv.append("\n"+bean.getData().getLecturerInfo().get(1).getJs_img());
                OkHttpClientManager.displayImage(ig,"http://192.168.0.188/"+bean.getData().getLecturerInfo().get(0).getJs_img());
            }
        });

//        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                Bean bean = new Gson().fromJson(result, Bean.class);
//                tv.append(bean.getData().getProvince().get(0).getRegion_name());
//                tv.append(bean.getData().getMeetingType().get(0).getHuiyi_type());
//
//
//
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//        card_recycler_view = (RecyclerView) findViewById(R.id.card_recycler_view);
//        card_recycler_view.setHasFixedSize(true);
//        //表示两列，并且是竖直方向的瀑布流
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
////        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL_LIST);
//        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
////        card_recycler_view.addItemDecoration(dividerGridItemDecoration);
//        final GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2, LinearLayoutManager.VERTICAL, false);
//        card_recycler_view.setLayoutManager(manager);
//        datas = new ArrayList<>();
//        for (int i = 0; i < 18; i++) {
//            datas.add("item" + i);
//        }
//        lv = (MyListView) findViewById(R.id.lv);
//        adapter1 = new MyAdapter<String>(datas);
//        lv.setAdapter(adapter1);
//        adapter1.addAll(datas);
//        adapter = new RenQiAdapter2(datas);
//        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.footer_loading, null);
//        adapterR = new RecyclerViewAdapter<String>(inflate,null,datas);
////        card_recycler_view.setAdapter(adapterR);
//        adapterR.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                if (adapterR.isHeader(position)) {
//                    Toast.makeText(getApplicationContext(), "header", Toast.LENGTH_SHORT).show();
//                } else if (adapterR.isFooter(position)) {
//                    Toast.makeText(getApplicationContext(), "footer ", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), " item " + position, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        adapterR.setOnItemLongClickLitener(new RecyclerViewAdapter.OnItemLongClickLitener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//                if (adapterR.isHeader(position)) {
//                    Toast.makeText(getApplicationContext(), "header clicked", Toast.LENGTH_SHORT).show();
//                } else if (adapterR.isFooter(position)) {
//                    Toast.makeText(getApplicationContext(), "footer clicked", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "header item " + position, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    card_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            boolean isBottom=manager.findLastCompletelyVisibleItemPosition()>=adapterR.getItemCount()-1;
//            if (isBottom){
//                ArrayList<String> datas=new ArrayList<String>();
//                for (int i = 0; i < 5; i++) {
//                   datas.add("----"+i);
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
    }
    public void add(View view) {
        TextView tv = (TextView) findViewById(R.id.tv);
        ImageView ig = (ImageView) findViewById(R.id.ig);
        ImageView ig1 = (ImageView) findViewById(R.id.ig1);
        WebView wb = (WebView) findViewById(R.id.wb);

//        LinearLayout layout = (LinearLayout) findViewById(R.id.liID);
//        tv.append("\n"+NetUtils.isConnected(getApplicationContext())+"\n"
//        +NetUtils.isGpsEnable(getApplicationContext()));


            tv.append("\n" + ScreeUtils.getStatusHeight(getApplicationContext()) + "\n"
                    + StringUtils.keywordMadeRed("jianyufeng", "jian"));
        wb.loadData(StringUtils.keywordMadeRed("jianyufeng", "jian") + StringUtils
                .addHtmlRedFlag("jianyufeng"), "text/html", "UTF-8");


//                PhoneUtils.callPhones(getApplicationContext(), "18729561936");
//        PhoneUtils.sendMessage(getApplicationContext(), "18729561936", "你好");

    }
}

//    class MyAdapter<String> extends BaseAdapter {
//
//        private List<String> datas;
//
//        public MyAdapter(List<String> datas) {
//            this.datas = datas;
//        }
//
//        public void addAll(List<String> datas) {
//            this.datas.addAll(datas);
//        }
//
//        @Override
//        public int getCount() {
//            return datas.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return datas.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        public Bitmap getCircleBitMap(Bitmap bitmap) {
//            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(output);
//            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
//            RectF rectF = new RectF(rect);
//            Paint paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setFilterBitmap(true);
//            canvas.drawOval(rectF, paint);
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            canvas.drawBitmap(bitmap, rect, rectF, paint);
//            return output;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder = null;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_tv, parent, false);
//                viewHolder = new ViewHolder();
//                viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
//                viewHolder.tv1 = (LinearLayout) convertView.findViewById(R.id.tv1);
//                viewHolder.ig = (ImageView) convertView.findViewById(R.id.ig);
//                ViewHolder finalViewHolder = viewHolder;
//                final ViewHolder finalViewHolder2 = viewHolder;
//                final ViewHolder finalViewHolder1 = viewHolder;
//                convertView.setOnTouchListener(new View.OnTouchListener() {
//                    private int lastX, lastY;
//
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        //获取到手指处的横坐标和纵坐标
//                        int x = (int) event.getX();
//                        int y = (int) event.getY();
//                        switch (event.getAction()) {
//                            case MotionEvent.ACTION_DOWN:
//                                lastX = x;
//                                lastY = y;
//                                break;
//                            case MotionEvent.ACTION_MOVE:
//                                //计算移动的距离
//                                int offX = x - lastX;
//                                int offY = y - lastY;
//                                //调用layout方法来重新放置它的位置
//
//                                finalViewHolder1.tv1.scrollBy(-offX, -offY);
//
//                                break;
//                        }
//                        return true;
//                    }
//                });
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            String string = datas.get(position);
//            viewHolder.tv.setText(string + "");
//            Bitmap bitmap = BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.a1));
//            Bitmap bg = BitmapUtils.getBitmapFromDrawable(getResources().getDrawable(R.drawable.ic_launcher));
////            Bitmap thumbnailBitmap = BitmapUtils.emboss(bitmap);
//            viewHolder.ig.setImageDrawable(
//                    BlurImage.BlurImages(bitmap, getApplicationContext()));
//            return convertView;
//        }
//
//        class ViewHolder {
//            TextView tv;
//            LinearLayout tv1;
//            ImageView ig;
//        }
//    }
//
//    boolean ispressed;



//        AnimationSet animationSet = new AnimationSet(true);
//        AlphaAnimation alphaAnimation = AnimationUtils.getAlphaAnimation(1, 0);
////        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation();

//        tv.startAnimation(AnimationUtils.getAmplificationAnimation(5000));

//        tv.append("\n"+ScreeUtils.getScreenHeight(getApplicationContext())+"\n"+ScreeUtils.getScreenWidth(getApplicationContext()));
//        tv.append("\n"+ScreeUtils.getStatusHeight(getApplicationContext()));


//        String ss = NetUtils.isGpsEnable(getApplicationContext()) + "";
//        T.showLong(getApplicationContext(),11);
//        if (!NetUtils.isGpsEnable(getApplicationContext())) {
//            NetUtils.openGpsSetting(this);
//        }
//        if (!NetUtils.isConnected(getApplicationContext())) {
//            NetUtils.openSetting(this);
//        }
//        tv.append(AppUtils.getAppName(getApplicationContext()));
//        tv.append(AppUtils.getVersionName(getApplicationContext()));
//        tv.append(AppUtils.getVersionCode(getApplicationContext())+"");

//    private List<String> datas;
//
//    public class RenQiAdapter2 extends RecyclerView.Adapter<RenQiAdapter2.ViewHolder> implements View.OnClickListener {
//        private List<String> data;//填充数据的集合
//
//        public RenQiAdapter2(List<String> data) {
//            this.data = data;
//        }
//
//        public void setData(List<String> data) {
//            this.data = data;
//        }
//
//        public void addData(int position) {
//            datas.add(position, "Insert One");
//            notifyItemInserted(position);
//        }
//
//        public void removeData(int position) {
//            datas.remove(position);
//            notifyItemRemoved(position);
//        }
//
//        @Override
//        public RenQiAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_detail_item, parent, false);
//            ViewHolder vh = new ViewHolder(view);
//            view.setOnClickListener(this);
//            return vh;
//        }
//
//        @Override
//        public void onBindViewHolder(RenQiAdapter2.ViewHolder holder, int position) {
//            holder.itemView.setTag(data.get(position));
//            holder.iv_store.setImageResource(ic_launcher);
//            holder.iv_store.setTag(data.get(position));
//            holder.tv_date.setText("item");
//            holder.tv_price.setText(data.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return data.size();
//        }
//
//        @Override
//        public void onClick(View v) {
//            if (null != mOnItemClickListener) {
//                mOnItemClickListener.onClick(v, (String) v.getTag());
//            }
//        }
//
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            ImageView iv_store;
//            TextView tv_date;
//            TextView tv_price;
//
//            public ViewHolder(View itemView) {
//                super(itemView);
//                iv_store = (ImageView) itemView.findViewById(R.id.iv_store);
//                tv_date = (TextView) itemView.findViewById(R.id.tv_date);
//                tv_price = (TextView) itemView.findViewById(R.id.tv_price);
//            }
//        }
//    }
//
//    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
//
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
//        this.mOnItemClickListener = listener;
//    }
//
//    public static interface OnRecyclerViewItemClickListener {
//        void onClick(View view, String itemData);
//    }
//
//
//}


//    ImageView ig = (ImageView) findViewById(R.id.ig);
//    String url ="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1457437487,655486635&fm=111&gp=0.jpg";
//    x.image().bind(ig, url);
////        OkHttpClientManager.displayImage(ig, url);
//    OkHttpClientManager.getAsyn("https://api.github.com/gists/c2a7c39532239ff261be", new OkHttpClientManager.ResultCallback<String>() {
//        @Override
//        public void onError(Request request, Exception e) {
//            Log.i("onError", e.getMessage());
//        }
//
//        @Override
//        public void onResponse(String response) {
//            Log.i("onError", response + "");
//        }
//    });
//
//    Log.i(";;;", MemoryStatus.formatSize(MemoryStatus.getTotalExternalMemorySize()));
//    Log.i(";;;", MemoryStatus.formatSize(MemoryStatus.getAvailableExternalMemorySize()));
//    tabHost = getTabHost();
//    TabHost.TabSpec tab1 = tabHost.newTabSpec("1")
//            .setIndicator("d111",getResources().getDrawable(R.drawable.ic_launcher))
//            .setContent(new Intent(this,MyActivity.class));
//    TabHost.TabSpec tab2 = tabHost.newTabSpec("1")
//            .setIndicator("d111",getResources().getDrawable(android.R.drawable.arrow_up_float))
//            .setContent(new Intent(this,MyActivity2.class));
//    tabHost.addTab(tab2);
//    tabHost.addTab(tab1);
//DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
//db = helper.getWritableDatabase();
//        daoMaster = new DaoMaster(db);
//        daoSession = daoMaster.newSession();
//        getNoteDao();
//        String textColumn=NoteDao.Properties.Text.columnName;
//        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
//        cursor = db.query(getNoteDao().getTablename(),getNoteDao().getAllColumns(),
//        null,null,null,null,orderBy);
//        String[] from = {textColumn, NoteDao.Properties.Comment.columnName};
//        int[] to = {android.R.id.text1, android.R.id.text2};
//
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from,
//        to);
//        lv.setAdapter(adapter);



