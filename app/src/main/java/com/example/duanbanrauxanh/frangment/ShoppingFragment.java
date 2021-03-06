package com.example.duanbanrauxanh.frangment;

import android.content.DialogInterface;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanbanrauxanh.Class.FuntionClass;
import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.activity.HomeActivity;
import com.example.duanbanrauxanh.adapter.ShopAdapter;
import com.example.duanbanrauxanh.dao.BillDAO;
import com.example.duanbanrauxanh.dao.BillDetaisDAO;
import com.example.duanbanrauxanh.dao.ProductDAO;
import com.example.duanbanrauxanh.interFace.onItemClicks;
import com.example.duanbanrauxanh.model.Bill;
import com.example.duanbanrauxanh.model.BillDetai;
import com.example.duanbanrauxanh.model.Product;

import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class ShoppingFragment extends Fragment implements View.OnClickListener, onItemClicks {

    private double tong;
    private TextView tv_total;
    private ProductDAO proDAO;
    private double quantitynew = 0;
    private ProductDAO productDAO;
    private Product productList;
    private double amoutUpdate;
    private Button btn_pay;
    private EditText ed_codeProduct, ed_cilent;
    private ListView listView;
    private BillDAO billDAO;
    private BillDetaisDAO billDetaiDao;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_shopping, container, false);
        anhXa(view);
        dataBase();
        filltoList(view);
        btn_pay.setOnClickListener(this::onClick);
        getMoney();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getMoney() {
        final List<Product> list = ((HomeActivity) getActivity()).productList;
        double total = 0;
        for (Product g : list) {
            double money = g.getAmount() * (g.getPrices() - (g.getPrices() * g.getSale() / 100));
            total += money;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String giaSP = currencyVN.format(total);
        tv_total.setText(giaSP + "");
        tong = total;
    }

    private void dataBase() {
        billDetaiDao = new BillDetaisDAO(getContext());
        billDAO = new BillDAO(getContext());
        productList = new Product();
        productDAO = new ProductDAO(getContext());
    }

    private void anhXa(View view) {
        listView = view.findViewById(R.id.lv_aProduct);
        btn_pay = view.findViewById(R.id.btn_aPay);
        tv_total = view.findViewById(R.id.tv_aTotal);
        ed_cilent = view.findViewById(R.id.ed_sNameClient);
        ed_codeProduct = view.findViewById(R.id.ed_sCodeProduct);
    }

    @Override
    public void onClick(View view) {
        final List<Product> list = ((HomeActivity) getActivity()).productList;
        Bill bill = new Bill();
        bill.setClientName(ed_cilent.getText().toString());
        bill.setIdBill(ed_codeProduct.getText().toString());
        bill.setDateAddBill(FuntionClass.getNows());
        bill.setTotal(tong);
        billDAO.insertBill(bill);
        // Add billDetai

        BillDetai billDetai = new BillDetai();
        Product product = new Product();
        proDAO = new ProductDAO(getContext());


        for (int i = 0; i < list.size(); i++) {
            billDetai.setIdBill(ed_codeProduct.getText().toString());
            billDetai.setIdProduct(list.get(i).getProductName());
            billDetai.setBuyAcount(list.get(i).getAmount());
            billDetai.setImportPrices(list.get(i).getPrices());
            billDetai.setTotal(list.get(i).getAmount() * (list.get(i).getPrices() - (list.get(i).getPrices() * list.get(i).getSale() / 100)));
            billDetaiDao.insertBildetai(billDetai);
        }
        //tinh lai so luong san pham de cap nhat vao kho
        for (Product prod : list) {
            product = proDAO.getProduct(prod.iD);
            quantitynew = product.amount - prod.getAmount();
            product.setAmount(quantitynew);
            proDAO.updateProduct(product);

        }
        Toast.makeText(getContext(), " Mua Thành Công ", Toast.LENGTH_SHORT).show();
        list.clear();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new ShoppingFragment()).commit();
    }

    public void filltoList(View view) {
        final List<Product> list = ((HomeActivity) getActivity()).productList;
        ShopAdapter productAdapter = new ShopAdapter(getContext(), list, this::onItemClickListShoping);
        listView.setAdapter(productAdapter);
    }


    @Override
    public void onItemClickListShoping(int i) {
        List<Product> list = ((HomeActivity) getActivity()).productList;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        builder.setMessage("Nhập số Lượng ");
        final View view1 = layoutInflater.inflate(R.layout.alert_amount, null);
        EditText edt_update = view1.findViewById(R.id.sl_amount);
        builder.setView(view1);
        Product product = list.get(i);
        //tìm sản phẩm theo id  để check xem kho hàng có đủ số lượng không
        productList = productDAO.checkAmountProduct(list.get(i).iD);
        builder.setPositiveButton("Update Amout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int s) {
                amoutUpdate = Double.parseDouble(edt_update.getText().toString().trim());
                if (amoutUpdate > productList.amount) {
                    Toast.makeText(getContext(), "Số lượng không đủ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    product.setAmount(amoutUpdate);
                    list.add(product);
                    Toast.makeText(getContext(), "Thanh Cong", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.action_hContainer, new ShoppingFragment()).commit();
                    list.remove(i);
                }
            }
        });

        builder.setNeutralButton("hủy ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialogUpdate = builder.create();
        alertDialogUpdate.show();
    }
}
