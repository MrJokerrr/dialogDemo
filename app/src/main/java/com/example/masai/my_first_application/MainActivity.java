package com.example.masai.my_first_application;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSimple;
    private Button mBtnList;
    private Button mBtnSingle;
    private Button mBtnMore;
    private Button mBtnPopupWindow;
    private Button mBtnPopShowToast;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnSimple = (Button) findViewById(R.id.btn_simple_dialog);
        mBtnList = (Button) findViewById(R.id.btn_list_dialog);
        mBtnSingle = (Button) findViewById(R.id.btn_single_dialog);
        mBtnMore = (Button) findViewById(R.id.btn_more_dialog);
        mBtnPopupWindow = (Button) findViewById(R.id.btn_popupwindow);

        mBtnSimple.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnSingle.setOnClickListener(this);
        mBtnMore.setOnClickListener(this);
        mBtnPopupWindow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_dialog:
                showSimpleDialog();
                break;
            case R.id.btn_list_dialog:
                showListDialog();
                break;
            case R.id.btn_single_dialog:
                showSingleDialog();
                break;
            case R.id.btn_more_dialog:
                showMutilDialog();
                break;
            case R.id.btn_popupwindow:
                showPopupWindow();
                break;
            case R.id.btn_pop:
                Toast.makeText(MainActivity.this, "显示吐司", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 自定义PopupWindow
     */
    private void showPopupWindow() {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_pop, null);
        mBtnPopShowToast = (Button) view.findViewById(R.id.btn_pop);
        mBtnPopShowToast.setOnClickListener(this);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.anim_pop_style);
        if (!popupWindow.isShowing()) {
//            popupWindow.showAsDropDown(mBtnPopupWindow, mBtnPopupWindow.getLayoutParams().width / 2, 0);
            popupWindow.showAtLocation(findViewById(R.id.activity_main), Gravity.CENTER | Gravity.BOTTOM, 0, 200);
        } else {
            popupWindow.dismiss();
        }
    }

    /**
     * 多选对话框
     */
    private void showMutilDialog() {
        final String[] items = {"张柏芝", "小S", "凤姐", "芙蓉姐姐"};
        final boolean[] selected = new boolean[]{false, true, false, false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("多选对话框 选择和谁xxoo");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        });
        builder.setPositiveButton("就这了...", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < selected.length; j++) {
                    if (selected[j]) {
                        sb.append(items[j] + " ");
                    }
                }
                Toast.makeText(MainActivity.this, "选择了" + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    /**
     * 单选对话框
     */
    private void showSingleDialog() {
        final String[] items = {"张三", "李四", "王五", "赵六"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("单选对话框");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "凤姐想约 " + items[i] + " 一起去开放", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定吗", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "自己约的炮,含着泪也要打完!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("我怂了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "不作死就不会死!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    /**
     * 列表对话框
     */
    private void showListDialog() {
        final String[] items = {"苹果", "锤子", "三星", "小米"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("列表对话框");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "选择了购买 " + items[i] + " 手机", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    /**
     * 一般对话框
     */
    private void showSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("一般对话框");
        builder.setMessage("是否确认退出");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }
}
