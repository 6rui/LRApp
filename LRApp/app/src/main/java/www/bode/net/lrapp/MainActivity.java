package www.bode.net.lrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.wpy.circleviewpager.widget.CycleView;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CycleView cycleView = (CycleView) findViewById(R.id.cycleView);
//        int[] imgs = new int[] { R.mipmap.ic_pic1,
//                                 R.mipmap.ic_pic2,
//                                 R.mipmap.ic_pic3,
//                                 R.mipmap.ic_pic4,
//                                 R.mipmap.ic_pic5, };
//        assert cycleView != null;
//        cycleView.setItems(imgs,
//                           getSupportFragmentManager(),
//                           new CycleView.OnItemClickListener() {
//                               @Override
//                               public void onItemClick(int position) {
//                                   Log.d("liao", position + "");
//                               }
//
//                               @Override
//                               public void onLoadImage(ImageView imageView,
//                                                       String url) {
//                               }
//                           });
    }
}
