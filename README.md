# ToastedRecyclerView

A RecyclerView library that is **easy and safe** to implement. To learn more about this library project check out our blog post on [toastdroid.com](http://toastdroid.com/2014/09/03/unlocking-recyclerview/)

---

## Download

Sample app link on its way.
In your build.gradle

```groovy

dependencies {
    compile 'com.twotoasters.RecyclerViewLib:library:1.0.+@aar'
}
```

## Background on RecyclerView
To learn more about RecyclerView checkout [this blog post](http://www.grokkingandroid.com/first-glance-androids-recyclerview/).

RecyclerView is Android's replacement for ListView. Currently it is only available as a release candidate which currently has restrictions allowing it only to be used in `L` apps.

This library has made it easy to get full access to RecyclerView and CardView has been thrown in for fun. All `L` related code has been removed from this library to make it safe to publish an app on the place store and not have explosions when `L` is released.
 
## What's Changed
- RecyclerView has predictive animations turned on, this not not possible without pulling out the source code.
    - There is a setter on the RecyclerView for this in case it causes issues.
    - There is also a setter to turn on debug logging in case you run into an issue.  
- The main thing which has changed is that all `L` code and dependencies on RC1 libraries have been removed. The biggest limitation is that if you use the CardView from this library, it will use the support CardView even on `L`.

## What's Added
- **PendingItemAnimator**: This abstract class is strongly based on the support libraries `DefaultItemAnimator`. This makes your animations always occur in a particular order. 
**Remove -> Move -> Add**
- **DisplayUtil**: Simple helper class to give you windows dimensions and convert dp->px and px->dp. Hopefully it will help out with your sick animations that you'll be creating.

## How to use it
Requires support-v4 for `ViewPropertyAnimatorCompat` for `PendingItemAnimator`. I am classically opposed to supporting anything before v15 and highly recommend that you don't waste your time (see [Death of Gingerbread](http://toastdroid.com/2014/03/05/2013-the-death-of-gingerbread-2/)) but this is a v-7 support library at heart so that is how it is designed. Also you don't even have to use that class it you don't want to and then you can just use the normal ViewPropertyAnimator.

Really I recommend just looking at the [Sample activity](https://github.com/twotoasters/RecyclerViewSample/blob/master/sample/src/main/java/com/twotoasters/recycled/RecycleActivity.java) and going from there. The RecyclerView use requires a few thing but you can see them all in `onCreate`. There are also a hand handful of [animations included in the library itself](https://github.com/twotoasters/RecyclerViewSample/tree/master/library/src/main/java/com/twotoasters/anim) but it does use a nice fade in and fade out animation by default. Feel free to make pull requests to add more animations, just remember to add them to the sample or else your animations will not be accepted.

Here is some helpful code to start you out on your path.

    java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        
        mRecyclerView = findWidgetById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 4);
        mRecyclerView.setAdapter(getAdapter());
        mRecyclerView.setItemAnimator(new radItemAnimator);
    }

You have to manage your data yourself in your adapter. The key difference to ListView adapters is that you now have a `notifyItemInserted()` and a `notifyItemRemoved()`. These are the methods that you have to use to get animations.

    java
    public void addToList(Item name, int position) {
        mNames.add(position, name);
        notifyItemInserted(position);
    }

    public void removeItemFromList(Item name) {
        int position = mNames.indexOf(name);
        mNames.remove(position);
        notifyItemRemoved(position);
    }

## GridViews and CursorAdapters oh my!
Here comes the sad part. So far the Android team has only released the `LinearLayoutManager` and `RecyclerView.Adapter`. These things are cool but they are of limit use to many people. If you want to give creating a LayoutManager a try I would love to get a pull request. While I won't require that you added it to the sample I need prof that it doesn't explode... I would also love to have pull request for different adapters, same requirements apply.


## License

    Copyright 2014 Two Toasters
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
       
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
