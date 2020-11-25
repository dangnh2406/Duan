package com.example.duanbanrauxanh.frangment;

import android.graphics.drawable.Drawable;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.adapter.BillDetaisAdapter;
import com.example.duanbanrauxanh.dao.BillDetaisDAO;
import com.example.duanbanrauxanh.model.Bill;
import com.example.duanbanrauxanh.model.BillDetai;
import com.example.duanbanrauxanh.model.Product;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class BillDetaisFragment extends Fragment {
    private TextView textView;
    private ListView listView;
    private Fragment fragment = null;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bill_detais, container, false);
        anhXa(view);
        filltoList();
        formatMoney();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.item, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void formatMoney() {
        //format tiền theo tỉ giá việt nam
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        Bundle bundle = getArguments();
        if (bundle != null) {
            double tong = (bundle.getDouble("total"));
            String giaSP = currencyVN.format(tong);
            textView.setText("Tiền Thanh Toán :\t" + giaSP);
        }
    }

    //đổ dữ liệu
    private void filltoList() {
        BillDetaisDAO billDetaisDAO = new BillDetaisDAO(getContext());
        String id = BillFragment.id();
        List<BillDetai> list = billDetaisDAO.getAlList(id);
        BillDetaisAdapter billDetaisAdapter = new BillDetaisAdapter(getContext(), list);
        listView.setAdapter(billDetaisAdapter);
    }

    private void anhXa(View view) {
        listView = view.findViewById(R.id.lv_bListBillDetais);
        textView = view.findViewById(R.id.totalMoney);
    }
}