package com.example.duanbanrauxanh.frangment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.duanbanrauxanh.R;
import com.example.duanbanrauxanh.activity.HomeActivity;
import com.example.duanbanrauxanh.adapter.ProductAdapter;
import com.example.duanbanrauxanh.dao.ProductDAO;
import com.example.duanbanrauxanh.model.Product;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class SupemarketFragment extends Fragment {

    private ListView listView;
    private List<Product> list;
    private ProductAdapter productAdapter;
    private ProductDAO productDAO;
    private TextInputLayout searchProduct;
    private String search;
    private Toolbar toolbar;

    public SupemarketFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supemarket, container, false);
        listView = view.findViewById(R.id.lv_sList);
        searchProduct = view.findViewById(R.id.search_product);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);



        productDAO = new ProductDAO(getActivity());

        //tìm kiếm sản phẩm
        searchProduct.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search = searchProduct.getEditText().getText().toString().trim();

//                if (search.length() == 0) {
//                    filtoList();
//
//                }
//                //nếu list rỗng thì sẽ báo lên
//                else if (list.size() == 0) {
//                    Toast.makeText(getContext(), "Không có sản phẩm như vậy", Toast.LENGTH_SHORT).show();
//                } else {
                    searchProductions();
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        filtoList();
        updateData(list);
        return view;
    }

    //tìm kiếm
    private void searchProductions() {
        list = productDAO.searchProduct(search);
        productAdapter = new ProductAdapter(getContext(), list);
        listView.setAdapter(productAdapter);
    }

    // đổ dữ liệu vào list
    private void filtoList() {
        productDAO = new ProductDAO(getActivity());
        list = productDAO.getAllProduct();
        productAdapter = new ProductAdapter(getContext(), list);
        listView.setAdapter(productAdapter);
    }

    //load lại list viêw
    private void updateData(List<Product> list1) {
        list.clear();
        ProductDAO goodsDAO = new ProductDAO(getContext());
        list1 = goodsDAO.getAllProduct();
        list.addAll(list1);
        productAdapter.notifyDataSetChanged();

    }


}