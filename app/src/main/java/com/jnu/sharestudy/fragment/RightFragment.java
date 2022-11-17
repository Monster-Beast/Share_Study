package com.jnu.sharestudy.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.sharestudy.R;
import com.jnu.sharestudy.adapter.RightRecycleViewAdapter;
import com.jnu.sharestudy.bean.Goods;

import java.util.ArrayList;

public class RightFragment extends Fragment {
private RecyclerView recyclerView;
public static RightRecycleViewAdapter rightRecycleViewAdapter;


    public RightFragment getInstance(ArrayList<Goods> data){
        RightFragment rightFragment=new RightFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("data",data);
        rightFragment.setArguments(bundle);
        return rightFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.right_recylerview,container,false);
        recyclerView= view.findViewById(R.id.right_recyclerview);
        if(getArguments()!=null){
            ArrayList<Goods> data=(ArrayList<Goods>) getArguments().getSerializable("data");
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            rightRecycleViewAdapter=new RightRecycleViewAdapter(getActivity(),data);
            recyclerView.setAdapter(rightRecycleViewAdapter);

        }

        return view;
    }
}
