package officeapp.zdd.com.recyclerviewdemo.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import officeapp.zdd.com.recyclerviewdemo.R;
import officeapp.zdd.com.recyclerviewdemo.entity.TypeOneEntity;

public class ViewHolderMyInfo extends TypeAbstractViewHolder<TypeOneEntity> implements View.OnClickListener {
    @BindView(R.id.layout_tone_1_content)
    TextView layout_tone_1_content;
    @BindView(R.id.layout_tone_2_content)
    TextView layout_tone_2_content;
    @BindView(R.id.layout_tone_3_content)
    TextView layout_tone_3_content;
    @BindView(R.id.layout_tone_4_content)
    TextView layout_tone_4_content;

    public ViewHolderMyInfo(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void bindViewHolder(TypeOneEntity cargoMessage, int postion) {
        layout_tone_1_content.setText(cargoMessage.getCurrentDate());
        layout_tone_2_content.setText(cargoMessage.getCurrentPeople());
        layout_tone_3_content.setText(cargoMessage.getClassify());
        layout_tone_4_content.setText(cargoMessage.getTodayWeather());
    }
}