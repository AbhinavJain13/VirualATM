package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ramakant.virualatm.R;

import java.util.List;

import Utils.MiniStatementData;

/**
 * Created by User on 15/03/2016.
 */
public class AdapterMiniStatement  extends RecyclerView.Adapter<AdapterMiniStatement.CustomHolder>{

    List<MiniStatementData> data;
    Context context;

    public AdapterMiniStatement(Context context,List<MiniStatementData> data )
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ministatemnt_item, parent, false);
        CustomHolder vh = new CustomHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder
    {
           TextView txtDate, txtTransactnAmnt, txtAccnNum, txtBalance, txtNotes, txtFlag;


        public CustomHolder(View itemView) {
            super(itemView);
            txtAccnNum = (TextView) itemView.findViewById(R.id.txtAccnNum);
            txtTransactnAmnt = (TextView) itemView.findViewById(R.id.txtTransacAmnt);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            txtBalance = (TextView) itemView.findViewById(R.id.txtBalance);
            txtNotes = (TextView) itemView.findViewById(R.id.txtNotes);
            txtFlag = (TextView) itemView.findViewById(R.id.txtFlag);

        }
    }
}
