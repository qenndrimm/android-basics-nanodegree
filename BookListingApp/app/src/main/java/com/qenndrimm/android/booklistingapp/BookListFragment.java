package com.qenndrimm.android.booklistingapp;

/**
 * Created by qenndrimm on 7/3/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookListFragment extends Fragment {

    private BookAdapter mBookAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mBookAdapter = new BookAdapter(getActivity(), new ArrayList<Book>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.list_book);
        listView.setAdapter(mBookAdapter);
        listView.setEmptyView(rootView.findViewById(R.id.text_empty_books));

        final EditText txtSearchBook = (EditText) rootView.findViewById(R.id.text_search_book);

        txtSearchBook.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean result = false;
                if (EditorInfo.IME_ACTION_SEARCH == actionId) {
                    final String bookName = v.getText().toString().trim();
                    if (bookName.isEmpty()) {
                        Utils.showToast(getActivity().getApplicationContext(),
                                getString(R.string.no_book_provided));
                    } else if (Utils.hasActiveNetwork(getActivity().getApplicationContext())) {
                        Utils.hideKeyboard(getActivity(), v.getWindowToken());
                        resetDisplay();
                        new FetchBookTask(mBookAdapter).execute(bookName);
                        result = true;
                    } else {
                        Utils.showToast(getActivity().getApplicationContext(),
                                getString(R.string.no_active_network));
                    }
                }
                return result;
            }
        });

        return rootView;
    }

    private void resetDisplay() {
        mBookAdapter.clear();
    }
}
