package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.InventoryContract.ItemEntry;

/**
 * {@link InventoryCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of inventory data as its data source. This adapter knows
 * how to create list items for each row of inventory data in the {@link Cursor}.
 */

public class InventoryCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link InventoryCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the inventory data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current item can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);

        // Find the columns of item attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_PRICE);
        int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);

        // Read the item attributes from the Cursor for the current item
        String itemName = cursor.getString(nameColumnIndex);
        final Integer itemQuantity = cursor.getInt(quantityColumnIndex);
        String itemPrice = cursor.getString(priceColumnIndex);
        final Integer itemId = cursor.getInt(idColumnIndex);

        // Update the TextViews with the attributes for the current item
        nameTextView.setText(itemName);
        quantityTextView.setText(String.valueOf(itemQuantity));
        priceTextView.setText(itemPrice);

        Button saleButton = (Button) view.findViewById(R.id.sold);
        saleButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (itemQuantity > 0) {
                   int newQuantity = itemQuantity - 1;
                   Uri productUri = ContentUris.withAppendedId(ItemEntry.CONTENT_URI, itemId);
                   ContentValues values = new ContentValues();
                   values.put(ItemEntry.COLUMN_ITEM_QUANTITY, newQuantity);
                   context.getContentResolver().update(productUri, values, null, null);
                   Toast.makeText(context, context.getString(R.string.success_item_sold), Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(context, context.getString(R.string.no_inventory), Toast.LENGTH_SHORT).show();
               }
           }
        });
    }
}






//    // If the sold button is clicked and the current quantity not null, reduce the quantity by 1
//    public void sold (View view){
//        mQuantityEditText = (TextView) findViewById(R.id.edit_item_quantity);
//        // Read from input fields
//        // Use trim to eliminate leading or trailing white space
//        String quantityString = mQuantityEditText.getText().toString().trim();
//        int quantity = Integer.parseInt(quantityString);
//        if (quantity == 0) {
//            //Show error message
//            Toast.makeText(this, "You can't sell an item you do not have", Toast.LENGTH_SHORT).show();
//            // Exist the method not to update the quantity
//            return;
//        }
//        quantity = quantity - 1;
//        mQuantityEditText.setText(Integer.toString(quantity));
//
//    }

//    Button sold = findViewById(R.id.sold_button);
//        sold.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String quantityNumber = mQuantityEditText.getText().toString().trim();
//            int quantityField = Integer.parseInt(quantityNumber);
//            if (quantityField > 0){
//                quantityField = quantityField - 1;
//                EditText textElement = findViewById(R.id.edit_quantity);
//                Log.i("Luis", "value of quantityField is: " +
//                        quantityField);
//                textElement.setText(quantityField);
//
//            }
//            else {
//                Toast.makeText(getApplicationContext(),"Out Stock Please Re Order", Toast.LENGTH_LONG).show();
//            }
//        }
//
//}