package apk.karmak.retrofitapp.main.Analyzes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.main.Analyzes.modal.CatalogModel;
import apk.karmak.retrofitapp.main.Analyzes.modal.NewsModal;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.RecyclerViewHolder> {

    private ArrayList<CatalogModel> courseDataArrayList;
    private Context context;

    public CatalogAdapter(ArrayList<CatalogModel> recyclerDataArrayList, Context context) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_maket, parent, false);


        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        CatalogModel modal = courseDataArrayList.get(position);

        holder.title.setText(modal.getName());
        holder.day.setText(modal.getTime_result());
        holder.price.setText(modal.getPrice()+" Р");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

                TextView tv23 = bottomSheetDialog.findViewById(R.id.textView23);
                tv23.setText(modal.getName());
                TextView tv25 = bottomSheetDialog.findViewById(R.id.textView25);
                tv25.setText(modal.getDescription());
                TextView tv27 = bottomSheetDialog.findViewById(R.id.textView27);
                tv27.setText(modal.getPreparation());
                TextView tv30 = bottomSheetDialog.findViewById(R.id.textView30);
                tv30.setText(modal.getBio());
                TextView tv31 = bottomSheetDialog.findViewById(R.id.textView31);
                tv31.setText(modal.getTime_result());
                Button btn = bottomSheetDialog.findViewById(R.id.button);
                btn.setText("Добавить за "+modal.getPrice()+" ₽");

                ImageButton Ibtn = bottomSheetDialog.findViewById(R.id.imageButton2);
                Ibtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.cancel();
                    }
                });


                bottomSheetDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View view;

        private Button btn;
        private TextView title;
        private TextView day;
        private TextView price;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
//            card_item = itemView.findViewById(R.id.card_item);

            btn = itemView.findViewById(R.id.catalogBtn);
            title = itemView.findViewById(R.id.textView20);
            day = itemView.findViewById(R.id.textView21);
            price = itemView.findViewById(R.id.textView22);
        }
    }
}


