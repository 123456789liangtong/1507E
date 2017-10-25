package bawei.com.zonghe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bawei.com.zonghe.R;

/**
 * Created by la on 2017/10/25.
 */

public class Fragment02 extends Fragment{

    private RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);

        recycler = view.findViewById(R.id.recycler_f2);

        return view;
    }
}
