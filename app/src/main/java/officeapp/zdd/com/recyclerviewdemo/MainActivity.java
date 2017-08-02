package officeapp.zdd.com.recyclerviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import officeapp.zdd.com.recyclerviewdemo.adapter.RecyclerAdaper;
import officeapp.zdd.com.recyclerviewdemo.common.Constant;
import officeapp.zdd.com.recyclerviewdemo.entity.FolkInfoEntity;
import officeapp.zdd.com.recyclerviewdemo.entity.FriendInfoEntity;
import officeapp.zdd.com.recyclerviewdemo.entity.TypeOneEntity;
import officeapp.zdd.com.recyclerviewdemo.viewholder.ViewHolderFlokInfo;
import officeapp.zdd.com.recyclerviewdemo.viewholder.ViewHolderFriendInfo;
import officeapp.zdd.com.recyclerviewdemo.viewholder.WrapContentLinearLayoutManager;

public class MainActivity extends AppCompatActivity implements
        ViewHolderFriendInfo.addViewOnClickListener,
        ViewHolderFlokInfo.addViewOnClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    //使用的时候改为你后台返回的数据即可
    //1布局的数据
    private List<TypeOneEntity> listOne;
    //2布局的数据
    private List<FolkInfoEntity> listTwo;
    //三布局的数据
    private List<FriendInfoEntity> listThree;
    private RecyclerAdaper recyclerAdapter;
    private int titleID = 0;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Constant.HANDLERTYPE_0:
                    //初始化数据刷新
                    refreshUI();
                    break;
                case Constant.HANDLERTYPE_1:
                    //刷新2布局
                    recyclerAdapter.refreshListTypeTwo(listTwo);
                    break;
                case Constant.HANDLERTYPE_2:
                    //刷新3布局
                    recyclerAdapter.refreshListTypeThree(listThree);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 加载布局
     */
    private void initView() {
        ViewHolderFriendInfo.setListener(this);
        ViewHolderFlokInfo.setListener(this);
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();
        listThree = new ArrayList<>();

        listOne.add(new TypeOneEntity("2017/07/20", "23人", "家属以及朋友", "多云转晴"));
        listTwo.add(new FolkInfoEntity(titleID++, "李四", "叔侄", "13503560243", "北京市海淀区", true));
        listThree.add(new FriendInfoEntity(titleID++, "王麻子", "13503560243", "北京市朝阳区", true));
        mHandler.sendEmptyMessage(Constant.HANDLERTYPE_0);
    }

    /**
     * 刷新界面
     */
    private void refreshUI() {
        recyclerAdapter = new RecyclerAdaper(this, listOne, listTwo, listThree);
        recycler.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    /**
     * 添加家属点击事件
     */
    @Override
    public void addFlokClick() {
        listTwo.add(new FolkInfoEntity(titleID++, "李四" + titleID, "叔侄" + titleID,
                "13503560243" + titleID, "北京市海淀区" + titleID, true));
        setTwoIsOver(listTwo);
        mHandler.sendEmptyMessage(Constant.HANDLERTYPE_1);
    }

    /**
     * 删除的点击事件
     *
     * @param postion
     */
    @Override
    public void deleteFlokClick(int postion) {
        if (postion > listTwo.size())
            return;

        if (listTwo.size() == 1) {
            Toast.makeText(this, "至少保留一条家属数据", Toast.LENGTH_SHORT).show();
            return;
        }

        listTwo.get(postion - 1).setDelete(true);
        Iterator iter = listTwo.iterator();
        while (iter.hasNext()) {
            FolkInfoEntity entity = (FolkInfoEntity) iter.next();
            if (entity.isDelete()) {
                iter.remove();
            }
        }
        setTwoIsOver(listTwo);
        mHandler.sendEmptyMessage(Constant.HANDLERTYPE_1);
    }

    /**
     * 添加朋友的点击事件
     */
    @Override
    public void addFriendClick() {
        listThree.add(new FriendInfoEntity(titleID++, "王麻子" + titleID,
                "13503560243" + titleID, "北京市朝阳区" + titleID, true));
        setThreeIsOver(listThree);
        mHandler.sendEmptyMessage(Constant.HANDLERTYPE_2);
    }

    /**
     * 删除的点击事件
     *
     * @param postion
     */
    @Override
    public void deleteFriendClick(int postion) {
        if ((postion - listTwo.size()) > listThree.size())
            return;

        if (listThree.size() == 1) {
            Toast.makeText(this, "至少保留一条朋友数据", Toast.LENGTH_SHORT).show();
            return;
        }


        listThree.get(postion - listTwo.size() - 1).setDelete(true);

        Iterator iter = listThree.iterator();
        while (iter.hasNext()) {
            FriendInfoEntity entity = (FriendInfoEntity) iter.next();
            if (entity.isDelete()) {
                iter.remove();
            }
        }

        setThreeIsOver(listThree);
        mHandler.sendEmptyMessage(Constant.HANDLERTYPE_2);
    }


    /**
     * 设置添加按钮的显示
     *
     * @param listTwo
     */
    private void setTwoIsOver(List<FolkInfoEntity> listTwo) {
        for (int i = 0; i < listTwo.size(); i++) {
            if (i == listTwo.size() - 1) {
                listTwo.get(i).setOver(true);
            } else {
                listTwo.get(i).setOver(false);
            }
        }
    }

    /**
     * 设置添加按钮的显示
     *
     * @param listThree
     */
    private void setThreeIsOver(List<FriendInfoEntity> listThree) {
        for (int i = 0; i < listThree.size(); i++) {
            if (i == listThree.size() - 1) {
                listThree.get(i).setOver(true);
            } else {
                listThree.get(i).setOver(false);
            }
        }
    }

    @Override
    public void onRefresh() {
        //最后调用结束刷新的方法
        setRefreshing(false);
    }

    /**
     * 停止刷新的方法
     *
     * @param refreshing
     */
    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
