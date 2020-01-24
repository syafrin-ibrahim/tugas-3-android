package com.example.mysubmission3.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.mysubmission3.DetailMovie;
import com.example.mysubmission3.DetailTvShow;
import com.example.mysubmission3.R;
import com.example.mysubmission3.adapter.MovieAdapter;
import com.example.mysubmission3.adapter.TvshowAdapter;
import com.example.mysubmission3.model.Movie;
import com.example.mysubmission3.model.Tvshow;
import com.example.mysubmission3.vm.MovieVM;
import com.example.mysubmission3.vm.TvShowVM;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class F_Tvshow extends Fragment {
    private MenuItem mSearchItem;
    private SearchView stv;
    private RecyclerView rvTvshow;
    private TvshowAdapter tsp;
    private ProgressBar progressBar;
    private TvShowVM tv, tvfilter;
    private String SearchString;
    F_Movie f;
    private static final String SEARCH_K = "search";
    public F_Tvshow() {
        // Required empty public constructor
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

            if(stv != null){
                SearchString = stv.getQuery().toString();
                outState.putString(SEARCH_K, SearchString);
                super.onSaveInstanceState(outState);
            }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_f__tvshow, container, false);

           setHasOptionsMenu(true);



         if (savedInstanceState != null) {
            SearchString = savedInstanceState.getString(SEARCH_K);
        }
        tsp = new TvshowAdapter();
        tsp.notifyDataSetChanged();
        progressBar = (ProgressBar)view.findViewById(R.id.bar);
        progressBar.setVisibility(View.VISIBLE);
        initializeRecycler(view);
        showMVTvShowList();
        return view;
    }


    private void initializeRecycler(View v) {
        rvTvshow = (RecyclerView)v.findViewById(R.id.rv_tvshow);
        rvTvshow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvshow.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvTvshow.setAdapter(tsp);
    }

    private void showMVTvShowList(){

        tv = new ViewModelProvider(getActivity(),new ViewModelProvider.NewInstanceFactory()).get(TvShowVM.class);
        tv.getListTvShows().observe(getActivity(), new Observer<ArrayList<Tvshow>>() {
            @Override
            public void onChanged(ArrayList<Tvshow> tvshows) {
                if(tvshows != null){
                    tsp.setData(tvshows);

                }

                showLoading(false);
            }
        });
        tv.setTvShow();
        tsp.setOnItemClickCallBack(new TvshowAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Tvshow data) {
                Intent h = new Intent(getActivity(), DetailTvShow.class);
                h.putExtra(DetailTvShow.EXTRA_TVSHOW, data);
                startActivity(h);
            }
        });
    }

    private void showMVFilterTvShow(String t) {
        tvfilter = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory())
                .get(TvShowVM.class);

        tvfilter.getFilterTvShow().observe(getActivity(), new Observer<ArrayList<Tvshow>>() {
            @Override
            public void onChanged(ArrayList<Tvshow> tvshows) {
                if(tvshows != null){
                    tsp.filterTvShow(tvshows);

                }
                showLoading(false);
            }
        });
        tvfilter.setFilter(t);
        tsp.setOnItemClickCallBack(new TvshowAdapter.onItemClickCallBack() {
            @Override
            public void onItemClicked(Tvshow data) {
                Intent hx = new Intent(getActivity(), DetailTvShow.class);
                hx.putExtra(DetailTvShow.EXTRA_TVSHOW, data);
                startActivity(hx);
            }
        });

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {


        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    {
            inflater.inflate(R.menu.main, menu);
            mSearchItem = menu.findItem(R.id.search);
            stv = (SearchView)mSearchItem.getActionView();

            stv.setMaxWidth(Integer.MAX_VALUE);
            stv.setQueryHint(getResources().getString(R.string.search_title));

            stv.setIconified(false);

                if (SearchString != null && !SearchString.isEmpty()) {
                    mSearchItem.expandActionView();
                    stv.setQuery(SearchString, true);
                    //  sv.clearFocus();
                    stv.setFocusable(true);
                    showMVFilterTvShow(SearchString);
                }



            stv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    String t = newText.toLowerCase();
                    if (TextUtils.isEmpty(t)) {

                        showMVTvShowList();

                    }
                    showMVFilterTvShow(t);
                    return true;
                }
            });
            mSearchItem.setActionView(stv);
            super.onCreateOptionsMenu(menu, inflater);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_change_settings){
            Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
