package www.bode.net.lrapp.LrBanner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import www.bode.net.lrapp.R;

/**
 * 自定义轮播控件 Created by Liu on 2016-05-30.
 */
public class LrBanner extends RelativeLayout implements ViewPager.OnPageChangeListener {
    /**
     * 全局上下文
     */
    private Context mContext;

    /**
     * 存放本地轮播图片地址的数组
     */
    private int mImages[];

    /**
     * 实现轮播的主控件
     */
    private ViewPager mViewPager;

    /**
     * 作为指示器的布局
     */
    private LinearLayout mIndicator;

    /**
     * 存放ImageView控件的数组
     */
    private SparseArray<ImageView> mImageViews;

    /**
     * 当前所处的页码
     */
    private int mCurrentPager;

    /**
     * 指示器小圆圈之前的间隔
     */
    private final int mIndicatorDivider = 8;

    /**
     * 轮播延迟时间
     */
    private final int mDelayTime = 2000;
    /**
     * 实现延迟的handler
     */
    private Handler mHandler;

    /**
     * 构造方法
     */
    public LrBanner(Context context) {
        this(context, null);
    }

    public LrBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initData();
        initViewPager();
        initIndicator();
        RelativeLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        addView(mViewPager);
        addView(mIndicator, lp);
        mHandler = new Handler();
        mHandler.postAtTime(autoPlayTask, mDelayTime);
    }

    /**
     * 自动播放的线程
     */
    Runnable autoPlayTask = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(autoPlayTask, mDelayTime);
            if (mCurrentPager == mImages.length - 1) {
                mCurrentPager = 0;
                mViewPager.setCurrentItem(mCurrentPager, false);
            } else {
                mCurrentPager++;
                mViewPager.setCurrentItem(mCurrentPager);
            }
        }
    };

    /**
     * 初始化数据
     */
    private void initData() {
        mImageViews = new SparseArray<>();
        mImages = new int[]{R.mipmap.ic_pic1,
                R.mipmap.ic_pic2,
                R.mipmap.ic_pic3,
                R.mipmap.ic_pic4,
                R.mipmap.ic_pic5};
        mCurrentPager = 0;

    }

    /**
     * 初始化轮播显示内容的控件
     */
    private void initViewPager() {
        mViewPager = new ViewPager(mContext);
        BannerPagerAdapter pagerAdapter = new BannerPagerAdapter();
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    /**
     * 初始化轮播指示器控件
     */
    private void initIndicator() {
        mIndicator = new LinearLayout(mContext);
        mIndicator.setGravity(Gravity.CENTER);
        mIndicator.setHorizontalGravity(LinearLayout.HORIZONTAL);
        addIndicatorChild();
    }

    /**
     * 根据实际需要轮播的图片数为指示器添加item（ImageView）
     */
    private void addIndicatorChild() {
        mIndicator.removeAllViews();
        int indicatorCount = mImages.length;
        for (int i = 0; i < indicatorCount; i++) {
            ImageView indicatorImageView = new ImageView(mContext);
            if (i == mCurrentPager) {
                indicatorImageView.setImageResource(R.mipmap.ic_page_indicator_focused);
            } else {
                indicatorImageView.setImageResource(R.mipmap.ic_page_indicator);
            }
            indicatorImageView.setScaleType(ImageView.ScaleType.CENTER);
            indicatorImageView.setPadding(mIndicatorDivider,
                    mIndicatorDivider,
                    mIndicatorDivider,
                    mIndicatorDivider);
            mIndicator.addView(indicatorImageView);
            mImageViews.put(i, indicatorImageView);
        }
    }


    /**
     * ViewPager 事件监听
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addIndicatorChild();
        mCurrentPager = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 内部类，ViewPager数据适配器
     */
    private class BannerPagerAdapter extends PagerAdapter {
        private SparseArray<ImageView> pagerImageViews = new SparseArray<>();

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageResource(mImages[position]);
            container.addView(imageView);
            pagerImageViews.put(position, imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
