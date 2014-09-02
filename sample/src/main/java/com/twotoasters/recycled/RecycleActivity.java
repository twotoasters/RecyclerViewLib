package com.twotoasters.recycled;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.twotoasters.android.support.v7.widget.LinearLayoutManager;
import com.twotoasters.android.support.v7.widget.RecyclerView;
import com.twotoasters.android.support.v7.widget.RecyclerView.ItemAnimator;
import com.twotoasters.layoutmanager.GridLayoutManager;
import com.twotoasters.recycled.factory.ItemAnimationFactory;
import com.twotoasters.recycled.factory.NameFactory;

import java.util.ArrayList;


public class RecycleActivity extends Activity {

    private static final String KEY_NAMES = "names";
    private static final String KEY_ANIMATION_INDEX = "animationIndex";
    private static final String KEY_LAYOUT_GRID = "layoutManager";

    private ArrayList<Item> mNames = NameFactory.getListOfNames();
    private int mAnimationIndex = 0;

    private Redapter mAdapter;
    private RecyclerView mRecyclerView;

    private String[] mAnimationArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle);
        mRecyclerView = findWidgetById(R.id.recyclerview);
        restoreState(savedInstanceState);

        mAnimationArray = getResources().getStringArray(R.array.animations);
        changeAnimation(mAnimationIndex);

        mRecyclerView.setAdapter(getAdapter());
    }

    private void setLayoutManager(boolean shouldBeGrid) {
        if (mRecyclerView != null) {
            mRecyclerView.getItemAnimator().endAnimations();
            if (shouldBeGrid) {
                mRecyclerView.setLayoutManager(new GridLayoutManager(RecycleActivity.this));
            } else {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
            }
            // The recycle pool has to be cleared after the layout manager is changed.
            mRecyclerView.getRecycledViewPool().clear();
        }
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mNames = (ArrayList<Item>) savedInstanceState.getSerializable(KEY_NAMES);
            mAnimationIndex = savedInstanceState.getInt(KEY_ANIMATION_INDEX);
            setLayoutManager(savedInstanceState.getBoolean(KEY_LAYOUT_GRID, false));
        } else {
            Toast.makeText(this, R.string.tap_to_remove, Toast.LENGTH_LONG).show();
            setLayoutManager(false);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(KEY_NAMES, mNames);
        outState.putInt(KEY_ANIMATION_INDEX, mAnimationIndex);
        if (mRecyclerView != null) {
            outState.putBoolean(KEY_LAYOUT_GRID, mRecyclerView.getLayoutManager() instanceof GridLayoutManager);
        }

        super.onSaveInstanceState(outState);
    }

    public <T extends View> T findWidgetById(int resId) {
        return (T) findViewById(resId);
    }

    private Redapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new Redapter(mNames);
        }
        return mAdapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycle, menu);
        for (int i = 0; i < mAnimationArray.length; i++) {
            menu.add(1, i, i, mAnimationArray[i]);
            MenuItem item = menu.findItem(i);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
        setupSwitch();
        return true;
    }

    private void setupSwitch() {
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setCustomView(R.layout.layout_switcher);

        Switch switcher = (Switch) getActionBar().getCustomView();
        switcher.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                setLayoutManager(checked);
            }
        });
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
                if (changeAnimation(id)) return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isInAnimationArray(int id) {
        return id >= 0 && id < mAnimationArray.length;
    }

    private boolean changeAnimation(int index) {
        if (!isInAnimationArray(mAnimationIndex)) return false;

        mAnimationIndex = index;
        ItemAnimator itemAnimator = ItemAnimationFactory.getAnimator(index);

        mRecyclerView.setItemAnimator(itemAnimator);
        getActionBar().setTitle(mAnimationArray[index]);
        return true;
    }
}
