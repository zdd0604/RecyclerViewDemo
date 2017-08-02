package officeapp.zdd.com.recyclerviewdemo.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import officeapp.zdd.com.recyclerviewdemo.R;
import officeapp.zdd.com.recyclerviewdemo.entity.FolkInfoEntity;

public class ViewHolderFlokInfo extends TypeAbstractViewHolder<FolkInfoEntity> implements View.OnClickListener {
    @BindView(R.id.layout_1)
    LinearLayout linearLayout_1;
    @BindView(R.id.layout_1_textview)
    TextView layout_1_textview;
    @BindView(R.id.layout_1_editext)
    EditText layout_1_editext;

    @BindView(R.id.layout_2)
    LinearLayout linearLayout_2;
    @BindView(R.id.layout_2_textview)
    TextView layout_2_textview;
    @BindView(R.id.layout_2_editext)
    EditText layout_2_editext;

    @BindView(R.id.layout_3)
    LinearLayout linearLayout_3;
    @BindView(R.id.layout_3_textview)
    TextView layout_3_textview;
    @BindView(R.id.layout_3_editext)
    EditText layout_3_editext;

    @BindView(R.id.layout_4)
    LinearLayout linearLayout_4;
    @BindView(R.id.layout_4_textview)
    TextView layout_4_textview;
    @BindView(R.id.layout_4_editext)
    EditText layout_4_editext;
    @BindView(R.id.addview)
    Button addview;
    @BindView(R.id.delete_text)
    TextView delete_text;
    @BindView(R.id.item_line_id)
    TextView item_line_id;
    @BindView(R.id.recycler_layout)
    RelativeLayout recycler_layout;

    public static addViewOnClickListener listener;

    public static void setListener(addViewOnClickListener listener) {
        ViewHolderFlokInfo.listener = listener;
    }

    public ViewHolderFlokInfo(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    @Override
    public void bindViewHolder(FolkInfoEntity cargoMessage, final int postion) {
        if (cargoMessage.isOver()) {
            addview.setVisibility(View.VISIBLE);
        } else {
            addview.setVisibility(View.GONE);
        }
        item_line_id.setText(cargoMessage.getTitleID()+"");
        layout_1_textview.setText("姓名");
        layout_1_editext.setText(cargoMessage.getName());
        layout_2_textview.setText("关系");
        layout_2_editext.setText(cargoMessage.getRelation());
        layout_3_textview.setText("电话");
        layout_3_editext.setText(cargoMessage.getPhoneNumber());
        layout_4_textview.setText("地址");
        layout_4_editext.setText(cargoMessage.getAddress());
        addview.setText(R.string.folk_title);
        addview.setOnClickListener(this);
//        recycler_layout.setBackgroundResource(R.drawable.red_bg);
        delete_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteFlokClick(postion);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addview:
                listener.addFlokClick();
                break;
        }
    }


    /**
     * 点击事件的接口
     */
    public interface addViewOnClickListener{
        void addFlokClick();
        void deleteFlokClick(int postion);
    }
}