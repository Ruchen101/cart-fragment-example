package za.co.clickandeat.clickeat.Fragments;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import za.co.clickandeat.clickeat.Adapters.CartListAdapter;
import za.co.clickandeat.clickeat.Global.Global;
import za.co.clickandeat.clickeat.Models.Product;
import za.co.clickandeat.clickeat.R;


public class CartFragment extends Fragment {


    private ListView cartListView;
    private ArrayList<Product> products = new ArrayList<>();

    //passes textview variable to adapter class to update checkout amount text in this class
    public static TextView subTotal;
    RelativeLayout overlay;
    ListView listView;


    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);


        overlay = (RelativeLayout) rootView.findViewById(R.id.overlay);
        listView = (ListView) rootView.findViewById(R.id.cart_list);

        generateCart();
       

        final Global globalVariables = (Global) getActivity().getApplicationContext();

        cartListView = (ListView) rootView.findViewById(R.id.cart_list);
        CartListAdapter cartListAdapter = new CartListAdapter(getActivity(), R.layout.activity_cart_row_item, products);
        cartListView.setAdapter(cartListAdapter);

        //set subtotal amount
        // TextView subTotal = (TextView) findViewById(R.id.total_amount_checkout);
        subTotal = (TextView) rootView.findViewById(R.id.total_amount_checkout);
        subTotal.setText(globalVariables.calculateSubTotal() + "");


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    //loops through cart in global context, adds to new array which is passed to adapter
    private void generateCart() {
        final Global globalVariables = (Global) getActivity().getApplicationContext();

        int cartLength = globalVariables.getMyProductsSize();
        for (int i = 0; i < cartLength; i++) {
            Product product = globalVariables.getMyProducts(i);
            products.add(product);

        }

    }

    //

}

