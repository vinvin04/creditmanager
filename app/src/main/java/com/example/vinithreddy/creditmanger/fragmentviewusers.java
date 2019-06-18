package com.example.vinithreddy.creditmanger;


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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.FragmentManager;

public class fragmentviewusers extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view=inflater.inflate(R.layout.viewallusersfrag,container,false);
        ListView userlistview=(ListView)view.findViewById(R.id.userlistview);
        android.app.FragmentManager fragmentManager=getActivity().getFragmentManager();
        android.app.Fragment fragmentA = fragmentManager.findFragmentByTag("fvu");
        userlistview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                MainActivity.pos=position;
                selectav sv=new selectav();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,sv,"sv");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,MainActivity.s);
        userlistview.setAdapter(arrayAdapter);
        return view;
    }
}