package chen.mybottom;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction, fragmentTransactionShow;
    private HomeFragment homeFragment;
    private NewFragment newFragment;
    private CenterFragment centerFragment;
    private OldFragment oldFragment;
    private MineFragment mineFragment;
    //当前显示的fragment
    private Fragment mShowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

        fragmentTransactionShow = fragmentManager.beginTransaction();
        if (homeFragment != null) {
            fragmentTransactionShow.show(homeFragment);
        } else {
            mShowFragment = new HomeFragment();
            fragmentTransactionShow.add(R.id.container_main, mShowFragment);
        }
        fragmentTransactionShow.commit();
    }

    private void initListener() {

        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initView() {

        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        mShowFragment = new HomeFragment();
        initData();
    }

    private void initData() {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = fragmentManager.beginTransaction();
        //先隐藏已经显示的fragment
        hideFragments(fragmentTransaction);

        switch (item.getItemId()) {
            case R.id.home:
                if (homeFragment != null) {
                    fragmentTransaction.show(homeFragment);
                } else {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.container_main, homeFragment);
                }
                break;

//            case R.id.news:
//                if (newFragment != null) {
//                    fragmentTransaction.show(newFragment);
//                } else {
//                    newFragment = new NewFragment();
//                    fragmentTransaction.add(R.id.container_main, newFragment);
//                }
//                break;

            case R.id.release:
                if (centerFragment != null) {
                    fragmentTransaction.show(centerFragment);
                } else {
                    centerFragment = new CenterFragment();
                    fragmentTransaction.add(R.id.container_main, centerFragment);
                }

                break;
//            case R.id.old:
//                if (oldFragment != null) {
//                    fragmentTransaction.show(oldFragment);
//                } else {
//                    oldFragment = new OldFragment();
//                    fragmentTransaction.add(R.id.container_main, oldFragment);
//                }
//                break;
            case R.id.me:
                if (mineFragment != null) {
                    fragmentTransaction.show(mineFragment);
                } else {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.container_main, mineFragment);
                }
                break;

        }
        fragmentTransaction.commit();
        return true;
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (newFragment != null) {
            fragmentTransaction.hide(newFragment);
        }
        if (centerFragment != null) {
            fragmentTransaction.hide(centerFragment);
        }
        if (oldFragment != null) {
            fragmentTransaction.hide(oldFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }


}
