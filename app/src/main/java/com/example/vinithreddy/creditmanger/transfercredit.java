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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class transfercredit extends Fragment implements AdapterView.OnItemSelectedListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.transfercredit,container,false);

        MainActivity.curractivity=1;

        TextView username=(TextView)view.findViewById(R.id.user1);
        username.setText(MainActivity.uname);

        Spinner spinner = (Spinner) view.findViewById(R.id.userdrop);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.userlistdrop, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        int count=getFragmentManager().getBackStackEntryCount();
        Log.i("count transfer",Integer.toString(count));

        final EditText et=(EditText)view.findViewById(R.id.amount);

        Button button= (Button) view.findViewById(R.id.transferop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String s=et.getText().toString();
                if(s.equals(""))
                {
                    Toast.makeText(getContext(),"select amount to transfer",Toast.LENGTH_SHORT).show();
                }
                else {
                    int am = Integer.parseInt(s);
                    MainActivity.amount = am;

                    ((MainActivity) getActivity()).transfer();
                    android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
                    getFragmentManager().popBackStack();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new fragacktransfer(), null);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });

        return view;
    }
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        MainActivity.uname2=parent.getItemAtPosition(pos).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}