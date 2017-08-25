package refresh.gl.cpm.pullrefreshviews;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import refresh.gl.cpm.pullrefreshviews.ui.JDHeader;
import refresh.gl.cpm.pullrefreshviews.ui.MyPtrFrameLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.ptr)
    MyPtrFrameLayout ptr;

    List<String> data = new ArrayList<>();

    MyAdapter adapter;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        ptr.changeJDHeader(new JDHeader(this));
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addData("添加的数据");
                        ptr.refreshComplete();
                    }
                },3000);
            }
        });

    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            data.add("数据" + i);
        }
        adapter = new MyAdapter(this, data);
        listview.setAdapter(adapter);
    }
}
