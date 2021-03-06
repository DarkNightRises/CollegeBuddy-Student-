package majorproject.kone.in.collegebudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import majorproject.kone.in.collegebudy.R;

/**
 * Created by Kartikey on 7/6/2015.
 */
public class ViewPagerPageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private RecyclerView recyclerView;
    private Boolean isHidden = false;

    public static ViewPagerPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ViewPagerPageFragment fragment = new ViewPagerPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.viewpagermainpage, container, false);
        ImageView swipetostartiv = (ImageView) view.findViewById(R.id.swipetostartiv);
        TextView swipetostarttv = (TextView) view.findViewById(R.id.swipetostarttv);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivViewPager);
        if (mPage == 1) {
            swipetostartiv.setImageResource(0);
            swipetostarttv.setText("");
            imageView.setImageResource(R.drawable.loginbanner1);
        } //       recyclerView.setAdapter(new DirectoryCustomAdapter(getActivity(), 4));
        else if (mPage == 2) {

            swipetostartiv.setImageResource(0);
            swipetostarttv.setText("");
            imageView.setImageResource(R.drawable.loginbanner2);
        } else {

            imageView.setImageResource(R.drawable.loginbanner3);
            swipetostartiv.setImageResource(R.drawable.ic_arrow_forward_black_18dp);
            swipetostarttv.setText("Swipe to Start");
        }
        return view;
    }
}