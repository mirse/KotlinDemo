package com.wdz.common.base;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;


/**
 * 获取权限继承PermissionActivity
 * @author dezhi.wang
 */
public abstract class PermissionActivity extends AppCompatActivity {
    private static final String TAG = "PermissionActivity";
    private String mPermission;
    private final int REQUEST_ONLY = 1;
    private final int REQUEST_MORE = 2;
    private ArrayList<String> denyList = new ArrayList<>();
    public void initMorePermission(String...permission){
        if (hasPermission(permission)){
            alreadyGetPermission();
        }
        else{
            ActivityCompat.requestPermissions(this,permission,REQUEST_ONLY);
        }
    }
    public boolean hasPermission(String...permissions){
        denyList.clear();
        for (String permission:permissions) {
            if (ContextCompat.checkSelfPermission(getApplication(),permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public boolean hasDenied(int[] grantResults){
        for (int grantResult:grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ONLY){
            if (grantResults.length>0){
                if (hasDenied(grantResults)){
                    onGetPermission();
                }
                else {
                    onDenyPermission();
                }

            }

//            if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                for (int i=0;i<grantResults.length;i++){
//                    Log.i(TAG,grantResults[i]+" permissions"+permissions[i]);
//                }
//                onGetPermission();
//            }
//            else{
//                onDenyPermission();
//            }
        }
    }

    /**
     * 已经获得权限
     */
    protected abstract void alreadyGetPermission();
    /**
     * 获得权限
     */
    protected abstract void onGetPermission();

    /**
     * 拒绝了权限
     */
    protected abstract void onDenyPermission();
}
