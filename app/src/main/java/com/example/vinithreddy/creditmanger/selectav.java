package com.example.vinithreddy.creditmanger;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class selectav extends Fragment implements AdapterView.OnItemSelectedListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ((MainActivity)getActivity()).database();
        View view=inflater.inflate(R.layout.selectav,container,false);

        TextView username=(TextView)view.findViewById(R.id.username);
        username.setText(MainActivity.uname);

        TextView emaail=(TextView)view.findViewById(R.id.email);
        emaail.setText(MainActivity.uemail);

        TextView credits=(TextView)view.findViewById(R.id.currcredits);
        credits.setText(Integer.toString(MainActivity.currcredits));

        final Button bu = view.findViewById(R.id.transfer);
        bu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                android.app.FragmentManager fragmentManager=getActivity().getFragmentManager();
                transfercredit tc=new transfercredit();
                getFragmentManager().popBackStack();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,tc,null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}