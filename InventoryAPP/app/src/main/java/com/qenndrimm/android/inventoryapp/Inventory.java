package com.qenndrimm.android.inventoryapp;

import android.app.Application;
import android.content.ContentValues;

import com.qenndrimm.android.inventoryapp.data.DBUtils;
import com.qenndrimm.android.inventoryapp.data.InventoryContract;

/**
 * Created by qenndrimm on 7/4/2016.
 */
public class Inventory extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createSupplierRecord();
        createProductRecord();
    }

    @Override public void onTerminate() {
        super.onTerminate();
    }

    private void createSupplierRecord() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME, "Retail Net");
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID, 109);
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE, 80074);
        DBUtils.getInstance(this)
                .insertIntoDB(InventoryContract.SupplierEntry.TABLE_NAME, contentValues);

        contentValues = new ContentValues();
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_NAME, "Vector E-Commerce");
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_SUPPLIER_ID, 105);
        contentValues.put(InventoryContract.SupplierEntry.COLUMN_NAME_PHONE, 94801);
        DBUtils.getInstance(this)
                .insertIntoDB(InventoryContract.SupplierEntry.TABLE_NAME, contentValues);
    }

    private void createProductRecord() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, "Reebok Instapump FuryRoad");
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 10);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE,  14999.00);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 105);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_IMAGE_URL,
                "http://assets.myntassets.com/h_480,q_95,w_360/v1/assets/images/1232602/2016/3/9/11457520152505-Reebok-Classic-Unisex-Black-Printed-Instapump-Fury-Road-Running-Shoes-6021457520152064-1.jpg");
        DBUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);

        contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, "UCB Sneakers");
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 25);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, 23467.65);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 109);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_IMAGE_URL,
                "http://img5a.flixcart.com/image/shoe/4/z/a/904-16p8yesh3374i-united-colors-of-benetton-42-400x400-imaeezj6yurvhrhd.jpeg");
        DBUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);

        contentValues = new ContentValues();
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRODUCT_NAME, "Adidas Springblade");
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_QUANTITY, 30);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_PRICE, 9599.00);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_SUPPLIER_ID, 105);
        contentValues.put(InventoryContract.ProductEntry.COLUMN_NAME_IMAGE_URL,
                "http://assets.myntassets.com/w_1080,q_80/v1/assets/images/1236903/2016/4/7/11460028930404-Adidas-Men-Neon-Pink-Springblade-SOLYCE-Running-Shoes-71460028929984-1.jpg");
        DBUtils.getInstance(this)
                .insertIntoDB(InventoryContract.ProductEntry.TABLE_NAME, contentValues);
    }
}