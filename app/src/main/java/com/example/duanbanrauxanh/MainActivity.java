package com.example.duanbanrauxanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanbanrauxanh.activity.CreateAccountActivity;
import com.example.duanbanrauxanh.activity.HomeActivity;
import com.example.duanbanrauxanh.dao.AccountDAO;
import com.example.duanbanrauxanh.model.User;

public class MainActivity extends AppCompatActivity {
    Button singin;
    Button create;
    CheckBox checkBox;
    EditText edt_user, edt_pass;
    String checkUser;
    String checkPass;
    String perfername = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anhXa();
        restoringPreferences();

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkaAccount();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "CREAT ACCOUNT", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));

//                Intent callIntend = new Intent(Intent.ACTION_CALL);
//                callIntend.setData(Uri.parse("tel:0359424773"));
//                startActivity(callIntend);

            }
        });
    }


    //check tai khoan mat khau
    private void checkaAccount() {
        AccountDAO accountDAO = new AccountDAO(getApplicationContext());
        checkUser = edt_user.getText().toString().trim();
        checkPass = edt_pass.getText().toString().trim();
        boolean checkedUser = accountDAO.checkUserPass(checkUser, checkPass);
        if (checkUser.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui long dien tai khoan", Toast.LENGTH_SHORT).show();
            return;
        } else if (checkPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Vui long dien mat khau", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkedUser) {
            Toast.makeText(getApplicationContext(), "Thong tin mat khau hoac tai khoan khong dung", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(getApplicationContext(), " Dang Nhap Thanh cong", Toast.LENGTH_SHORT).show();
           //dong man hinh hien tai

            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            //va khoi tao activity moi
            MainActivity.this.finish();
        }
    }


    //phần ánh xạ
    private void anhXa() {
        singin = findViewById(R.id.btn_singin);
        create = findViewById(R.id.createaccount);
        checkBox = findViewById(R.id.checkboxPass);
        edt_pass = findViewById(R.id.edt_cPass);
        edt_user = findViewById(R.id.edt_cUser);
    }


    protected void onPause() {

        super.onPause();
        //gọi hàm lưu trạng thái ở đây
        save();
    }

    @Override
    protected void onResume() {

        super.onResume();
        //gọi hàm đọc trạng thái ở đây
        restoringPreferences();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK)
            super.onActivityResult(requestCode, resultCode, data);
    }

    public void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences
                (perfername, MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        boolean bchk = pre.getBoolean("checked", false);
        if (bchk) {
            //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
            String user = pre.getString("user", "");
            String pwd = pre.getString("pwd", "");

            checkUser = edt_user.getText().toString().trim();
            checkPass = edt_pass.getText().toString().trim();

            checkUser = user;
            checkPass = pwd;

            edt_user.setText(checkUser);
            edt_pass.setText(checkPass);
        }
        checkBox.setChecked(bchk);
    }

    public void save() {
//tao doi tuong shar
        SharedPreferences sharedPreferences = getSharedPreferences(perfername, MODE_PRIVATE);
//tao doi tuong  ederter de  luu thay doi
        SharedPreferences.Editor editor = sharedPreferences.edit();


        checkUser = edt_user.getText().toString().trim();
        checkPass = edt_pass.getText().toString().trim();

        String userr = checkUser;
        String passw = checkPass;

        boolean bchk = checkBox.isChecked();
        if (!bchk) {
            //xóa mọi lưu trữ trước đó
            editor.clear();
        } else {
            editor.putString("user", userr);
            editor.putString("pwd", passw);
            editor.putBoolean("checked", bchk);
        }
        //luu xuong file
        editor.commit();

    }

}