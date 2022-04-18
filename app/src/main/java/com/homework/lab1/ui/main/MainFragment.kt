package com.homework.lab1.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.homework.lab1.R
import com.homework.lab1.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewGlobal: View
    private lateinit var accountData: List<Balance>
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val layout = R.layout.main_fragment
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewGlobal = view

        CoroutineScope(Dispatchers.IO).launch {
            setAccountData(RetrofitClient.retrofitService.getBalanceList());
        }

        CoroutineScope(Dispatchers.IO).launch {
            setTariffs(RetrofitClient.retrofitService.getTariffsList());
        }

        CoroutineScope(Dispatchers.IO).launch {
            setUser(RetrofitClient.retrofitService.getUserList());
        }
    }

    private fun setUser(userList: List<User>) {
        activity?.runOnUiThread {
            val recyclerViewButtons = viewGlobal.findViewById<RecyclerView>(R.id.buttons_list)
            val adapter = UserButtonAdapter()
            adapter.submitList(mutableListOf(
                UserButton(
                    id = 1,
                    icon = R.drawable.ic_user,
                    text = userList[0].firstName.plus(" ").plus(userList[0].lastName)
                ),
                UserButton(
                    id = 2,
                    icon = R.drawable.ic_home,
                    text = userList[0].address
                ),
                UserButton(
                    id = 3,
                    icon = R.drawable.ic_menu,
                    text = getString(R.string.services_list)
                )
            ))
            recyclerViewButtons.adapter = adapter
        }
    }

    private fun setTariffs(tariffsList: List<Tariff>) {
        activity?.runOnUiThread {
            val recyclerViewTariff = viewGlobal.findViewById<RecyclerView>(R.id.tariff_list)
            val tariffAdapter = TariffAdapter()
            tariffAdapter.submitList(tariffsList)
            recyclerViewTariff.adapter = tariffAdapter
        }
    }

    private fun setAccountData(accountData: List<Balance>) {
        activity?.runOnUiThread {
            viewGlobal.findViewById<TextView>(R.id.personal_number).text =
                accountData[0].id.toString();
            viewGlobal.findViewById<TextView>(R.id.balance).text =
                getString(R.string.money_string, accountData[0].amount)
            viewGlobal.findViewById<TextView>(R.id.to_pay).text =
                getString(R.string.to_pay, "сентябрь", accountData[0].toPay)
        }
    }

}