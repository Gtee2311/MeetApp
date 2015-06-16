package com.gracetee.meetapp.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gracetee.meetapp.Activity.ChatActivity;
import com.gracetee.meetapp.Activity.ConversationActivity;
import com.gracetee.meetapp.Model.Conversation;
import com.gracetee.meetapp.R;
import com.gracetee.meetapp.Utils.Const;
import com.gracetee.meetapp.Utils.Utils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class UserList a Fragment class. It shows a list of all users of
 * this app. It also shows the Offline/Online status of users.
 */

public class ChatFragment extends Fragment {

    /** The Contact list. */
    ChatActivity CA = (ChatActivity)getActivity();
    private ArrayList<ParseUser> uList = CA.getuList();

    /** The Conversation list. */
    private ArrayList<Conversation> convList;

    /** The user name of buddy. */
    private String buddy;

    /** The last message. */
    private String lastMessage;

    FragmentActivity context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUserList();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = (FragmentActivity) super.getActivity();
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    /**
     * Load list of users.
     */
    private void loadUserList()
    {
        final ProgressDialog dia = ProgressDialog.show(getActivity(), null,
                getString(R.string.alert_loading));
        if (uList != null && uList.size() > 0) {
            for (int i = uList.size() - 1; i >= 0; i--) {
                buddy = uList.get(i).getUsername();
                ParseQuery<ParseObject> send = ParseQuery.getQuery("Chat");
                send.whereEqualTo("sender", ChatActivity.user.getUsername());
                send.whereEqualTo("receiver", buddy);

                ParseQuery<ParseObject> receive = ParseQuery.getQuery("Chat");
                receive.whereEqualTo("receiver", ChatActivity.user.getUsername());
                receive.whereEqualTo("sender", buddy);

                List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
                queries.add(send);
                queries.add(receive);

                ParseQuery<ParseObject> compoundQuery = ParseQuery.or(queries);
                compoundQuery.setLimit(1)
                        .findInBackground(new FindCallback<ParseObject>() {

                            @Override
                            public void done(List<ParseObject> li, ParseException e) {
                                dia.dismiss();
                                if (li != null) {
//                                    if (li.size() == 0)
//                                        Toast.makeText(getActivity(),
//                                                R.string.msg_no_msg_found,
//                                                Toast.LENGTH_SHORT).show();

                                    if (li != null && li.size() > 0) {
                                        for (int i = li.size() - 1; i >= 0; i--) {
                                            ParseObject po = li.get(i);
                                            lastMessage = po.getString("message");
                                        }
                                    }
                                    ListView list = (ListView) getView().findViewById(R.id.list);
                                    list.setAdapter(new ChatAdapter());
                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                        @Override
                                        public void onItemClick(AdapterView<?> arg0,
                                                                View arg1, int pos, long arg3) {
                                            Bundle bundle = new Bundle();
                                            bundle.putString(Const.EXTRA_DATA, buddy);
                                            Intent in = new Intent(getActivity(), ConversationActivity.class);
                                            in.putExtras(bundle);
                                            startActivity(in);
                                        }
                                    });
                                } else {
                                    Utils.showDialog(
                                            getActivity(),
                                            getString(R.string.err_users) + " "
                                                    + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        };
    }

    private class ChatAdapter extends BaseAdapter
    {

        /* (non-Javadoc)
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount()
        {
            return convList.size();
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Conversation getItem(int arg0)
        {
            return convList.get(arg0);
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int arg0)
        {
            return arg0;
        }

        /* (non-Javadoc)
         * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
         */
        @Override
        public View getView(int pos, View v, ViewGroup arg2)
        {
            if (v == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                v = inflater.inflate(R.layout.chat_conversation_item, null);

            }

            Conversation c = getItem(pos);
            TextView lbl1 = (TextView) v.findViewById(R.id.buddy_name);
            lbl1.setText(c.getMsg());
            TextView lbl2 = (TextView) v.findViewById(R.id.buddy_conversation);
            lbl2.setText(c.getMsg());
//            lbl1.setCompoundDrawablesWithIntrinsicBounds(
//                    c.getBoolean("online") ? R.drawable.ic_online
//                            : R.drawable.ic_offline, 0, R.drawable.arrow, 0);

            return v;
        }

    }

}