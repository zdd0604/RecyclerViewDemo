package officeapp.zdd.com.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import officeapp.zdd.com.recyclerviewdemo.R;
import officeapp.zdd.com.recyclerviewdemo.common.Constant;
import officeapp.zdd.com.recyclerviewdemo.entity.FolkInfoEntity;
import officeapp.zdd.com.recyclerviewdemo.entity.FriendInfoEntity;
import officeapp.zdd.com.recyclerviewdemo.entity.TypeOneEntity;
import officeapp.zdd.com.recyclerviewdemo.viewholder.ViewHolderFlokInfo;
import officeapp.zdd.com.recyclerviewdemo.viewholder.ViewHolderFriendInfo;
import officeapp.zdd.com.recyclerviewdemo.viewholder.ViewHolderMyInfo;

/**
 * Created by Admin on 2017/7/19.
 */

public class RecyclerAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TypeOneEntity> listOne;
    private List<FolkInfoEntity> listTwo;
    private List<FriendInfoEntity> listThree;
    private List<Integer> types = new ArrayList<>();
    private Map<Integer, Integer> mPositions = new HashMap<>();
    private LayoutInflater layoutInflater;

    public RecyclerAdaper(Context context,
                          List<TypeOneEntity> listOne,
                          List<FolkInfoEntity> listTwo,
                          List<FriendInfoEntity> listThree) {
        layoutInflater = LayoutInflater.from(context);
        this.listOne = listOne;
        this.listTwo = listTwo;
        this.listThree = listThree;

        addlistBytype(Constant.LAYOUT_TYPE_ONE, listOne);
        addlistBytype(Constant.LAYOUT_TYPE_TWO, listTwo);
        addlistBytype(Constant.LAYOUT_TYPE_THREE, listThree);
    }

    public void addlistBytype(int type, List list) {
        mPositions.put(type, types.size());
        for (int i = 0; i < list.size(); i++) {
            types.add(type);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Constant.LAYOUT_TYPE_ONE:
                return new ViewHolderMyInfo(layoutInflater.inflate(R.layout.layout_type_one, parent, false));
            case Constant.LAYOUT_TYPE_TWO:
                return new ViewHolderFlokInfo(layoutInflater.inflate(R.layout.layout_recycler_item, parent, false));
            case Constant.LAYOUT_TYPE_THREE:
                return new ViewHolderFriendInfo(layoutInflater.inflate(R.layout.layout_recycler_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int intemtype = getItemViewType(position);
        int realPosition = position - mPositions.get(intemtype);
        switch (intemtype) {
            case Constant.LAYOUT_TYPE_ONE:
                ((ViewHolderMyInfo) holder).bindViewHolder(listOne.get(realPosition), position);
                break;
            case Constant.LAYOUT_TYPE_TWO:
                ((ViewHolderFlokInfo) holder).bindViewHolder(listTwo.get(realPosition), position);
                break;
            case Constant.LAYOUT_TYPE_THREE:
                ((ViewHolderFriendInfo) holder).bindViewHolder(listThree.get(realPosition), position);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    /**
     *  刷新布局2的数据
     * @param list
     */
    public void refreshListTypeTwo(List<FolkInfoEntity> list) {
        types.clear();
        this.listTwo = list;
        addlistBytype(Constant.LAYOUT_TYPE_ONE, listOne);
        addlistBytype(Constant.LAYOUT_TYPE_TWO, listTwo);
        addlistBytype(Constant.LAYOUT_TYPE_THREE, listThree);
        if (list.size() > 0) {
            for (int i = mPositions.get(Constant.LAYOUT_TYPE_TWO); i < types.size(); i++) {
                this.notifyItemChanged(i);
            }
        } else {
            this.notifyItemChanged(2);
        }
    }

    /**
     * 刷新布局3的数据
     * @param list
     */
    public void refreshListTypeThree(List<FriendInfoEntity> list) {
        types.clear();
        this.listThree = list;
        addlistBytype(Constant.LAYOUT_TYPE_ONE, listOne);
        addlistBytype(Constant.LAYOUT_TYPE_TWO, listTwo);
        addlistBytype(Constant.LAYOUT_TYPE_THREE, listThree);
        if (list.size() > 0) {
            for (int i = mPositions.get(Constant.LAYOUT_TYPE_THREE); i < types.size(); i++) {
                this.notifyItemChanged(i);
            }
        } else {
            this.notifyItemChanged(2);
        }
    }
}
