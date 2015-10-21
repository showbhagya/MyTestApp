package ehc.mytest2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.ListIterator;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ListView theList;
    private FruitsAdapter theAdapter;
    public boolean goneFlag;
    private int selectedIndex;
    private SparseBooleanArray mSelectedItemsIds;
    List<MainActivity> Mainactivitylist;
    private String[] fruits;
    public ArrayList<String> fruititems;
    private CheckBox checkbox;
    private KeyEvent event;
    private boolean normal;
    ArrayList<String> selectedFruits = new ArrayList<String>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theList = (ListView) findViewById(R.id.theList);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Main Activity");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }*/
        fruits = getResources().getStringArray(R.array.fruits);
        fruititems = new ArrayList<String>(Arrays.asList(fruits));
        theAdapter = new FruitsAdapter(fruititems);
        ArrayList list = new ArrayList();

        /*theList.setChoiceMode(theList.CHOICE_MODE_MULTIPLE_MODAL);
        theList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener()
        {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                list_items.add(fruititems.get(position));
                count = count + 1;
                mode.setTitle(count + "items selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_name:
                        for (String msg : list_items) {
                            fruititems.remove(msg);
                        }
                        count = 0;
                        mode.finish();

                        return true;
                    default:
                        return false;
                }
            }


            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });*/


        theList.setAdapter(theAdapter);
        theList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //fruits item=this.fruits.get(position);
                Log.d("test123", "setOnItemLongClickListener :");
                selectedIndex = position;
                checkbox = (CheckBox) view.findViewById(R.id.check_box);
//                checkbox.setVisibility(View.GONE);
                checkbox.setChecked(true);
                goneFlag = true;
//                setGoneFlag(goneFlag);
                theAdapter.notifyDataSetChanged();


                return true;
            }
        });
    }


    public static int[] addInt(int[] series, int newInt) {
        //create a new array with extra index
        int[] newSeries = new int[series.length + 1];

        //copy the integers from series to newSeries
        for (int i = 0; i < series.length; i++) {
            newSeries[i] = series[i];
        }
//add the new integer to the last index
        newSeries[newSeries.length - 1] = newInt;
        return newSeries;

    }

    public void setGoneFlag(boolean goneFlag) {
        this.goneFlag = goneFlag;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //do something
            Log.d("test123", "selected index " + selectedIndex);
            Toast.makeText(this, "Settings menu was clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.about) {
            //do something
            Toast.makeText(this, "About menu was clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_name) {
            //do something
            if (checkbox.isChecked()) {
                fruititems.removeAll(selectedFruits);
                checkbox.setChecked(false);
                checkbox.setVisibility(View.GONE);
                theAdapter.notifyDataSetChanged();
                Toast.makeText(this, "delete menu was clicked", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == R.id.home) {
            checkbox.setVisibility(View.GONE);
            theAdapter.notifyDataSetChanged();
            Toast.makeText(this, "About menu was clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_CALL && event.isTracking()
                && !event.isCanceled()) {
            // if the call key is being released, AND we are tracking
            // it from an initial key down, AND it is not canceled,
            // then handle it.
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }







           /* @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_name:
                        // Calls getSelectedIds method from ListViewAdapter Class
                       *//* SparseBooleanArray selected = theList
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                MainActivity selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                listviewadapter.remove(selecteditem);
                            }
                        }*//*
                        fruititems.remove(selectedIndex);
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }
*/

    private class FruitsAdapter extends BaseAdapter {


        public FruitsAdapter(ArrayList<String> fruits) {


            Log.d("test123", "fruitsitems " + fruititems);
        }


        @Override
        public int getCount() {
            Log.d("test123", "count :" + fruititems.size());
            return fruititems.size();
        }

        @Override
        public Object getItem(int position) {
            Log.d("test123", "fruitsitems with pos " + fruititems.get(position));
            return fruititems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item_layout, null);
            }
            TextView listItem = (TextView) convertView.findViewById(R.id.list_item);
            final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.check_box);
//            checkbox.setVisibility(View.VISIBLE);
            if (goneFlag)
                checkbox.setVisibility(View.VISIBLE);
            else
                checkbox.setVisibility(View.GONE);
            Log.d("test123", "postion: " + position);
            checkbox.setTag(position);

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("test123", "setOnCheckedChangeListener" + ((Integer) checkbox.getTag()));
                    String fruit = fruititems.get(((Integer) checkbox.getTag()));
                    Log.d("test1", "selected Fruites: " + selectedFruits);
                    if (selectedFruits.contains(fruit)) {
                        selectedFruits.remove(fruit);
                    } else {
                        selectedFruits.add(fruit);
                    }
                    Log.d("test123", "selectedFruits : " + selectedFruits);


                }
            });
            listItem.setText(fruititems.get(position));

            return convertView;
        }
    }
}



