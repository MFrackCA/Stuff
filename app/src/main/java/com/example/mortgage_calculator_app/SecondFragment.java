package com.example.mortgage_calculator_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mortgage_calculator_app.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private Object mortgage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Get values from view 1
        Bundle data = getArguments();

        MortgageCalculator mc = (MortgageCalculator) data.getSerializable("User_input");

        // Set Views for Mortgage payment schedule
        binding.v2principal.setText(String.valueOf((double) mc.getPrincipal()));
        binding.v2amortization.setText(String.valueOf((int) mc.getAmortization()) + " Years");
        binding.v2interest.setText(String.valueOf((double) mc.getInterest()) + "%");
        binding.v2monthly.setText("$"+ String.valueOf((int) mc.monthlyPayment()));

        binding.intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber("9057213190");
            }
        });

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);

    }

}