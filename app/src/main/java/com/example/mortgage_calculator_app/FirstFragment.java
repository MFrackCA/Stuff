package com.example.mortgage_calculator_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mortgage_calculator_app.databinding.FragmentFirstBinding;

import java.io.Serializable;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MortgageCalculator mc = new MortgageCalculator();

        binding.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Grab inputs from User
                String Principal = binding.mortgage.getText().toString();
                String Interest = binding.interest.getText().toString();
                String Amortization = binding.Amortization.getText().toString();

                if (TextUtils.isEmpty(Principal) || TextUtils.isEmpty(Interest) || TextUtils.isEmpty(Amortization)) {
                    // Display a toast message if any of the fields are empty
                    Toast.makeText(getContext(), "Please fill in all fields before calculation", Toast.LENGTH_SHORT).show();
                } else {
                    //User data inputs for mortgage calculation
                    mc.setPrincipal(Principal);
                    mc.setInterest(Interest);
                    mc.setAmortization(Amortization);

                    // bundle to transfer data
                    Bundle data = new Bundle();
                    data.putSerializable("User_input", (Serializable) mc);

                    // Send data and navigate to second screen
                    NavHostFragment.findNavController(FirstFragment.this)
                            .navigate(R.id.action_FirstFragment_to_SecondFragment, data);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}