package com.example.vinithreddy.creditmanger;
import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;

public class fragmenthome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.homefrag,container,false);
        ImageView im=(ImageView)view.findViewById(R.id.imallusers);

        int count=getFragmentManager().getBackStackEntryCount();
        Log.i("count home ",Integer.toString(count));

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                Fragment f=fragmentManager.findFragmentByTag("fvu");
                if(f==null) {
                    fragmentviewusers fvu = new fragmentviewusers();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fvu, "fvu");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else
                {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, f, "fvu");
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }
}