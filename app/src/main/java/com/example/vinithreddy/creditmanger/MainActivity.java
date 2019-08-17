package com.example.vinithreddy.creditmanger;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,java.io.Serializable {
    public DrawerLayout drawer;
    public  SharedPreferences sharedPreferences;
    public  static int pos=-1;
    public static String uname;
    public static String uname2;
    public  static String uemail;
    public static int currcredits;
    public static int amount;
    public static String usr[];
    public static int curractivity=0;

    Blockchain vincoin;

    public  static  String s[]={"user1","user2","user3","user4","user5","user6","user7","user8","user9","user10"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        sharedPreferences=this.getSharedPreferences("com.example.vinithreddy.creditmanger",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("databasestats","notcreated").apply();


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragmenthome()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragmenthome()).commit();
                break;
            case R.id.nav_allusers: {

                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment fragmentA = fragmentManager.findFragmentByTag("fvu");
                if (fragmentA == null) {
                    //not exist
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new fragmentviewusers()).commit();
                }
                else{
                    //fragment exist
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentManager.findFragmentByTag("fvu")).commit();
                }

                break;
            }
            case R.id.nav_selectav: {
                FragmentManager fragmentManager = getSupportFragmentManager();

                Fragment fragmentA = fragmentManager.findFragmentByTag("sv");
                if (fragmentA == null) {
                    //not exist
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new selectav()).commit();
                }
                else{
                    //fragment exist
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentManager.findFragmentByTag("sv")).commit();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new selectav()).commit();
                break;
            }
            case R.id.nav_transfercredit: {

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new transfercredit()).commit();

            }
                break;
            case R.id.nav_share:
                Toast.makeText(this,"share",Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public static void performNoBackStackTransaction(final FragmentManager fragmentManager, String tag, Fragment fragment) {
        final int newBackStackLength = fragmentManager.getBackStackEntryCount() +1;

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment, tag)
                .addToBackStack(tag)
                .commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int nowCount = fragmentManager.getBackStackEntryCount();
                if (newBackStackLength != nowCount) {
                    fragmentManager.removeOnBackStackChangedListener(this);

                    if ( newBackStackLength > nowCount ) { // user pressed back
                        fragmentManager.popBackStackImmediate();
                    }
                }
            }
        });
    }

    public void database()
    {

        try
        {
            String sts=sharedPreferences.getString("databasestats","");
            SQLiteDatabase con = this.openOrCreateDatabase("task1", MODE_PRIVATE, null);
            if(sts.equals("notcreated")) {

                con.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,email VARCHAR,currcredits int(10))");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user1','user1@gmail.com',120)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user2','user2@gmail.com',130)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user3','user3@gmail.com',200)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user4','user4@gmail.com',220)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user5','user5@gmail.com',250)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user6','user6@gmail.com',300)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user7','user7@gmail.com',350)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user8','user8@gmail.com',400)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user9','user9@gmail.com',450)");
                con.execSQL("INSERT INTO users(name,email,currcredits) VALUES ('user10','user10@gmail.com',500)");
                con.execSQL("CREATE TABLE IF NOT EXISTS transfers(userfrom VARCHAR,userto VARCHAR,amount int(10),dateof date)");

                //blockchain integration
                vincoin=new Blockchain();

                String filePath = this.getFilesDir().getPath().toString() + "/data.dat";
                File f = new File(this.getFilesDir(), filePath);

                // Serialization
                try
                {
                    //Saving of object in a file
                    FileOutputStream file = new FileOutputStream("res\\data.dat");
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    // Method for serialization of object
                    out.writeObject(vincoin);

                    out.close();
                    file.close();

                    System.out.println("Object has been serialized");

                }

                catch(IOException ex)
                {
                    System.out.println("IOException is caught");
                    Log.i("blockchain test ser",ex.toString());
                }


            }
            usr=new String[1];
            usr[0]=s[pos];

            Cursor c=con.rawQuery("SELECT * FROM users WHERE name = ?",usr);
            int ni=c.getColumnIndex("name");
            int ei=c.getColumnIndex("email");
            int ci=c.getColumnIndex("currcredits");
            c.moveToFirst();
            uname=c.getString(ni);
            uemail=c.getString(ei);
            currcredits=c.getInt(ci);
            c.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void transfer()
    {
        try
        {
            SQLiteDatabase con = this.openOrCreateDatabase("task1", MODE_PRIVATE, null);//creation

            //insert transactions
            ContentValues insertValues = new ContentValues();
            insertValues.put("userfrom",uname);
            insertValues.put("Userto",uname2);
            insertValues.put("amount",amount);
            insertValues.put("dateof",System.currentTimeMillis());
            con.insert("transfers",null,insertValues);

            //blockchain integration
            String filePath = this.getFilesDir().getPath().toString() + "/data.dat";
            try
            {

                // Reading the object from a file
                FileInputStream file = new FileInputStream(filePath);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                vincoin = (Blockchain) in.readObject();

                in.close();
                file.close();

                System.out.println("Object has been deserialized ");

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
                Log.i("blockchain test dser1",ex.toString());
            }

            catch(ClassNotFoundException ex)
            {
                System.out.println("ClassNotFoundException is caught");
                Log.i("blockchain test dser2",ex.toString());
            }


            //if(vincoin.isBlockchainValid())
            vincoin.addBlock(uname,uname2,amount);

            //vincoin.isBlockchainValid()


            // Serialization
            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream(filePath);
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(vincoin);

                out.close();
                file.close();

                System.out.println("Object has been serialized");

            }

            catch(IOException ex)
            {
                System.out.println("IOException is caught");
                Log.i("blockchain test ser2",ex.toString());
            }
            for(int i=0;i<vincoin.chain.size();i++)
            Log.i("blockchain test",vincoin.chain.get(i).toString());

            Log.i("blockchain check test",Boolean.toString(vincoin.isBlockchainValid()));

            //change credits
            currcredits=currcredits-amount;

            String usr1[]=new String[1];
            usr1[0]=uname;

            //update user1
            ContentValues updatev = new ContentValues();
            updatev.put("currcredits",currcredits); //These Fields should be your String values of actual column name
            con.update("users", updatev, "name=?", usr1);

            //get user 2 credits
            String usr2[]=new String[1];
            Log.i("user2",uname2);
            usr2[0]=uname2;
            Cursor cursor=con.rawQuery("SELECT * FROM users WHERE name = ?",usr2);
            //Log.i("count",Integer.toString(cursor.getCount()));

            int ni2=cursor.getColumnIndex("name");
            int ei2=cursor.getColumnIndex("email");
            int ci2=cursor.getColumnIndex("currcredits");
            cursor.moveToFirst();
            String k=cursor.getString(ni2);
            String k1=cursor.getString(ei2);
            int k2=cursor.getInt(ci2);
            cursor.close();
           // Log.i("user2",k+k1+Integer.toString(k2));


            int user2updatedcredits=k2+amount;

            //Log.i("user2updated",Integer.toString(user2updatedcredits));

            //Log.i("user2 update before",Integer.toString(user2updatedcredits));

            //update user2 credits
            String us[]=new String[1];
            us[0]=uname2;
            ContentValues updatev2 = new ContentValues();
            updatev2.put("currcredits",user2updatedcredits); //These Fields should be your String values of actual column name
            con.update("users", updatev2, "name=?", us);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}