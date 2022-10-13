package Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibo.mygym.PersonDetails;
import com.ibo.mygym.R;

import java.util.List;

import Model.Person;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.myAdapter> {

    List<Person> personList;
    Context context;

    public ListAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.myAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new myAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.myAdapter holder, int position) {
        Person person=personList.get(position);
        holder.tv_aId.setText(person.getId()+"");
        holder.tv_aName.setText(person.getName());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class myAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_aId,tv_aName;

        public myAdapter(@NonNull View itemView) {
            super(itemView);
            tv_aId=itemView.findViewById(R.id.a_tvid);
            tv_aName=itemView.findViewById(R.id.a_tvname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Person person=personList.get(position);
            Intent intent=new Intent(context, PersonDetails.class);
            intent.putExtra("id",person.getId());
            intent.putExtra("name",person.getName());
            intent.putExtra("lastName",person.getLastName());
            intent.putExtra("startDate",person.getStartDate());
            intent.putExtra("endDate",person.getEndDate());
            intent.putExtra("description",person.getDescription());
            context.startActivity(intent);
        }
    }
}
