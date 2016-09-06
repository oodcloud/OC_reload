package cn.oddcloud.www.oc_reload.View.old_Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Adapter.TopicArticleGridAdapter;


/**
 * Created by florentchampigny on 24/04/15.
 */
public class SubjectArticleFragment extends Fragment {

   private GridView gridView;
    private ObservableScrollView mScrollView;
    public static SubjectArticleFragment newInstance() {
        return new SubjectArticleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_themenew, container, false);
        gridView=(GridView)view. findViewById(R.id.gridview);
        gridView.setAdapter(new TopicArticleGridAdapter(getContext()));

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);

    }
}
