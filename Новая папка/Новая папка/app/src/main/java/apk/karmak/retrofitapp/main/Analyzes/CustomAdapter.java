package apk.karmak.retrofitapp.main.Analyzes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import apk.karmak.retrofitapp.R;
import apk.karmak.retrofitapp.main.Analyzes.modal.NewsModal;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RecyclerViewHolder> {

    private ArrayList<NewsModal> courseDataArrayList;
    private Context context;

    public CustomAdapter(ArrayList<NewsModal> recyclerDataArrayList, Context context) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_maket, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        NewsModal modal = courseDataArrayList.get(position);
        Picasso.get().load(modal.getImage()).into(holder.img);
        holder.name.setText(modal.getName());
        holder.desk.setText(modal.getDescription());
        holder.price.setText(modal.getPrice()+" Р");







    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View view;

        private ImageView img;
        private TextView name;
        private TextView desk;
        private TextView price;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
//            card_item = itemView.findViewById(R.id.card_item);

            img = itemView.findViewById(R.id.imageView3);
            name = itemView.findViewById(R.id.textView17);
            desk = itemView.findViewById(R.id.textView18);
            price = itemView.findViewById(R.id.textView19);
        }
    }
}
