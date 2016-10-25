package com.qenndrimm.android.inventoryapp;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qenndrimm.android.inventoryapp.data.DBUtils;
import com.qenndrimm.android.inventoryapp.data.InventoryContract;

/**
 * Created by qenndrimm on 7/4/2016.
 */
public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImage;

    TextView productTitle;
    TextView productQuantity;
    TextView productPrice;

    Button increaseQuantity;
    Button decreaseQuantity;
    Button orderMore;
    Button deleteProduct;

    int productId = 0;
    int quantity = 0;

    long supplierId = 0;
    double price = 0;

    String title = null;
    String imageUrl = null;

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //set the id to each references
        productTitle = (TextView) findViewById(R.id.detailProductName);
        productQuantity = (TextView) findViewById(R.id.detailProductQuantity);
        productPrice = (TextView) findViewById(R.id.detailProductPrice);
        productImage = (ImageView) findViewById(R.id.detailProductImage);
        increaseQuantity = (Button) findViewById(R.id.btnIncrease);
        decreaseQuantity = (Button) findViewById(R.id.btnDecrease);
        orderMore = (Button) findViewById(R.id.btnOrderMore);
        deleteProduct = (Button) findViewById(R.id.btnDelete);


        // get the intent content and set them to respective objects
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            productId = bundle.getInt("Id");
            title = bundle.getString("Title");
            quantity = bundle.getInt("Quantity");
            price = bundle.getDouble("Price");
            supplierId = bundle.getLong("SupplierId");
            imageUrl = bundle.getString("ImageUrl");
        }

        productTitle.setText(title);
        productQuantity.setText("Quantity : " + quantity);
        productPrice.setText("Price : INR" + price);

        // to increase quantity of the item
        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int newQuantity = quantity + 1;
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, newQuantity);
                String selection = InventoryContract.ProductEntry._ID + " = ?";
                String[] selectionArgs = { String.valueOf(productId) };
                DBUtils.getInstance(getBaseContext())
                        .updateEntry(InventoryContract.ProductEntry.TABLE_NAME, values, selection,
                                selectionArgs);
                setQuantity(newQuantity);
                MainActivity.refreshCursor();
            }
        });

        // to decrease the quantity of the selected item
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int newQuantity = quantity - 1;
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, newQuantity);
                String selection = InventoryContract.ProductEntry._ID + " = ?";
                String[] selectionArgs = { String.valueOf(productId) };
                DBUtils.getInstance(getBaseContext())
                        .updateEntry(InventoryContract.ProductEntry.TABLE_NAME, values, selection,
                                selectionArgs);
                setQuantity(newQuantity);
                MainActivity.refreshCursor();
            }
        });


        //to delete the product
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                new AlertDialog.Builder(context).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(getBaseContext().getResources().getString(R.string.delete_dialog_title))
                        .setMessage(
                                getBaseContext().getResources().getString(R.string.delete_dialog_message) + title)
                        .setPositiveButton(getString(R.string.dialog_answer_yes),
                                new DialogInterface.OnClickListener() {
                                    @Override public void onClick(DialogInterface dialog, int which) {
                                        String selection = InventoryContract.ProductEntry._ID + " = ?";
                                        String[] selectionArgs = { String.valueOf(productId) };
                                        DBUtils.getInstance(context)
                                                .deleteEntries(InventoryContract.ProductEntry.TABLE_NAME, selection,
                                                        selectionArgs);
                                        MainActivity.refreshCursor();
                                        finish();
                                    }
                                })
                        .setNegativeButton(getString(R.string.dialog_answer_no), null)
                        .show();
            }
        });

        // to order more from suppplier
        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int number = getSupplierNumber(supplierId);
                String url = "tel:" + number;
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(phoneIntent);
            }
        });
    }

    private void setQuantity(int newQuantity) {
        quantity = newQuantity;
        productQuantity.setText("Quantity : " + quantity);
    }

    private int getSupplierNumber(long supplierId) {
        String selection = InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID + " = ?";
        String[] selectionArgs = { String.valueOf(supplierId) };
        Cursor cursor = DBUtils.getInstance(getBaseContext())
                .readFromDB(InventoryContract.SupplierEntry.TABLE_NAME, null, selection, selectionArgs);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE));
    }
}