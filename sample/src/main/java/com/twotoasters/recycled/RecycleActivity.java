package com.twotoasters.recycled;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.twotoasters.android.support.v7.widget.LinearLayoutManager;
import com.twotoasters.android.support.v7.widget.RecyclerView;
import com.twotoasters.recycled.factory.ItemAnimationFactory;
import com.twotoasters.recycled.factory.NameFactory;


public class RecycleActivity extends Activity {

    private Redapter adapter;
    private RecyclerView recyclerView;

    private String[] animationArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        animationArray = getResources().getStringArray(R.array.animations);
        getActionBar().setTitle(animationArray[0]); // We'll always be on zero when the activity is started.

        recyclerView = getWidgetById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 4);
        recyclerView.setAdapter(getAdapter());
    }

    public <T extends View> T getWidgetById(int resId) {
        return (T) findViewById(resId);
    }

    private Redapter getAdapter() {
        if (adapter == null) {
            adapter = new Redapter(NameFactory.getListOfNames());
        }
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycle, menu);
        for (int i = 0; i < animationArray.length; i++) {
            menu.add(1, i, i, animationArray[i]);
            MenuItem item = menu.findItem(i);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                getAdapter().addToList(NameFactory.getRandomName());
                return true;
            default:
                if (isInAnimationArray(id)) {
                    return changeAnimation(id);
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isInAnimationArray(int id) {
        return id >= 0 && id < animationArray.length;
    }

    private boolean changeAnimation(int index) {
        recyclerView.setItemAnimator(ItemAnimationFactory.getAnimator(index));
        getActionBar().setTitle(animationArray[index]);
        return true;
    }
}
