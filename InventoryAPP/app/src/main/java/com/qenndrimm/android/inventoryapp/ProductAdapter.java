package com.qenndrimm.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qenndrimm.android.inventoryapp.data.DBUtils;
import com.qenndrimm.android.inventoryapp.data.InventoryContract;

/**
 * Created by qenndrimm on 7/4/2016.
 */
public class ProductAdapter extends CursorAdapter {

    private Context context;
    private LayoutInflater cursorInflater;

    public ProductAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        this.context = context;
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return cursorInflater.inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view,final Context context, Cursor cursor) {
        LinearLayout productContainer = (LinearLayout) view.findViewById(R.id.listContainer);
        TextView productName = (TextView) view.findViewById(R.id.productName);
        TextView productQuantity = (TextView) view.findViewById(R.id.productQuantity);
        TextView productPrice = (TextView) view.findViewById(R.id.productPrice);
        ImageView productImage = (ImageView) view.findViewById(R.id.productImage);
        ImageButton saleBtn = (ImageButton) view.findViewById(R.id.saleButton);


        final int id = cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry._ID));
        final String title = cursor.getString(
                cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME));
        final int quantity =
                cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY));
        final double price =
                cursor.getDouble(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME_PRICE));
        final long supplierId = cursor.getLong(
                cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID));
        final String imageUrl = cursor.getString(
                cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_NAME_IMAGE_URL));

        productName.setText(title);
        productQuantity.setText("Quantity: " + quantity);
        productPrice.setText("INR: " + price);

        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                int newQuantity = quantity - 1;
                ContentValues values = new ContentValues();
                values.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, newQuantity);
                String selection = InventoryContract.ProductEntry._ID + " = ?";
                String[] selectionArgs = { String.valueOf(id) };
                DBUtils.getInstance(context)
                        .updateEntry(InventoryContract.ProductEntry.TABLE_NAME, values, selection,
                                selectionArgs);
                MainActivity.refreshCursor();
            }
        });

        productContainer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("Id", id);
                bundle.putString("Title", title);
                bundle.putInt("Quantity", quantity);
                bundle.putDouble("Price", price);
                bundle.putLong("SupplierId", supplierId);
                bundle.putString("ImageUrl", imageUrl);
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }
}