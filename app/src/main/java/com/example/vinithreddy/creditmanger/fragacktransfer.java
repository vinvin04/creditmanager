package com.example.vinithreddy.creditmanger;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class fragacktransfer extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragacktransfer,container,false);
        TextView from=(TextView)view.findViewById(R.id.from);
        from.setText(MainActivity.uname);
        TextView to=(TextView)view.findViewById(R.id.to);
        to.setText(MainActivity.uname2);
        TextView amountt=(TextView)view.findViewById(R.id.amount);
        String am=Integer.toString(MainActivity.amount);
        amountt.setText(am);
        Button button= (Button) view.findViewById(R.id.done);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            android.app.FragmentManager fragmentManager=getActivity().getFragmentManager();
            getFragmentManager().popBackStack();
            //getFragmentManager().popBackStack();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,new fragmenthome(),null);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            }
        });
        return view;
    }
}
