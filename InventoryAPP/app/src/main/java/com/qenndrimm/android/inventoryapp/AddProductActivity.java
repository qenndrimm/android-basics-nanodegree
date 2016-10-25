package com.qenndrimm.android.inventoryapp;

/**
 * Created by qenndrimm on 7/4/2016.
 */
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import com.qenndrimm.android.inventoryapp.data.DBUtils;
import com.qenndrimm.android.inventoryapp.data.InventoryContract;

public class AddProductActivity extends AppCompatActivity {

    private EditText inputProductName;
    private EditText inputProductQuantity;
    private EditText inputProductPrice;
    private EditText inputProductImage;
    private TextInputLayout inputLayoutProductName;
    private TextInputLayout inputLayoutProductQuantity;
    private TextInputLayout inputLayoutProductPrice;
    private TextView inputLayoutProductImage;
    private Spinner supplierSpinner;

    private ArrayList<String> getSupplierList() {
        ArrayList<String> supplierList = new ArrayList<>();
        Cursor cursor =
                DBUtils.getInstance(this).readFromDB(InventoryContract.SupplierEntry.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String supplierName = cursor.getString(
                        cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME));
                supplierList.add(supplierName);
            } while (cursor.moveToNext());
        }
        return supplierList;
    }

    private long getSupplierId(String supplierName) {
        String selection = InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME + " LIKE ?";
        String[] selectionArgs = { "%" + supplierName + "%" };
        Cursor cursor = DBUtils.getInstance(this)
                .readFromDB(InventoryContract.SupplierEntry.TABLE_NAME, null, selection, selectionArgs);
        cursor.moveToFirst();
        return cursor.getLong(
                cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        inputProductName = (EditText) findViewById(R.id.input_product_name);
        inputProductQuantity = (EditText) findViewById(R.id.input_product_quantity);
        inputProductPrice = (EditText) findViewById(R.id.input_product_price);
        inputProductImage = (EditText) findViewById(R.id.input_product_image);
        inputLayoutProductName = (TextInputLayout) findViewById(R.id.input_layout_product_name);
        inputLayoutProductQuantity = (TextInputLayout) findViewById(R.id.input_layout_product_quantity);
        inputLayoutProductImage = (TextView) findViewById(R.id.input_layout_product_image);
        inputLayoutProductPrice = (TextInputLayout) findViewById(R.id.input_layout_product_price);
        supplierSpinner = (Spinner) findViewById(R.id.product_suppliers);
        Button addProductBtn = (Button) findViewById(R.id.product_add);

        ArrayList<String> supplierList = getSupplierList();
        ArrayAdapter<String> spinnerAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, supplierList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierSpinner.setAdapter(spinnerAdapter);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                boolean formValid = true;
                String newProductName = inputProductName.getText().toString();
                int newProductQuantity = 0;
                try {
                    newProductQuantity = Integer.parseInt(inputProductQuantity.getText().toString());
                    inputLayoutProductQuantity.setErrorEnabled(false);
                } catch (NumberFormatException ex) {
                    inputLayoutProductQuantity.setError(getString(R.string.err_msg_quantity));
                    formValid = false;
                }
                Double newProductPrice = 0.0;
                try {
                    newProductPrice = Double.parseDouble(inputProductPrice.getText().toString());
                    inputLayoutProductPrice.setErrorEnabled(false);
                } catch (NumberFormatException ex) {
                    inputLayoutProductPrice.setError(getString(R.string.err_msg_price));
                    formValid = false;
                }
                String newProductImage = inputProductImage.getText().toString();

                if (newProductImage.equals("")) {
                    inputLayoutProductImage.setError(getString(R.string.err_msg_name_empty));
                    formValid = false;
                } else {
                    inputLayoutProductName.setErrorEnabled(false);
                }

                if (newProductName.equals("")) {
                    inputLayoutProductName.setError(getString(R.string.err_msg_name_empty));
                    formValid = false;
                } else {
                    inputLayoutProductName.setErrorEnabled(false);
                }

                String selectedSupplierName = supplierSpinner.getSelectedItem().toString();
                long supplierId = getSupplierId(selectedSupplierName);
                if (formValid) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME,
                            newProductName);
                    contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY,
                            newProductQuantity);
                    contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, newProductPrice);
                    contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, supplierId);
                    contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_IMAGE_URL, newProductImage);
                    DBUtils.getInstance(getBaseContext())
                            .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);
                    MainActivity.refreshCursor();
                    finish();
                }
            }
        });
    }
}