package com.example.geniusplazaapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.geniusplazaapp.adapters.UsersAdapter
import com.example.geniusplazaapp.objects.Data
import com.example.geniusplazaapp.objects.Users
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.dialog_new_user.view.*
import android.annotation.SuppressLint
import android.widget.Toast
import com.example.geniusplazaapp.objects.UserResponse
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    var userData: ArrayList<Users>? = null
    var service: APIService? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        val dividerItemDecoration = DividerItemDecoration(
            this@MainActivity,
            (recyclerview.layoutManager as LinearLayoutManager).getOrientation()
        )
        recyclerview.addItemDecoration(dividerItemDecoration)

        userData = arrayListOf()
        service = UserDataApi().getClient(this@MainActivity).create(APIService::class.java)


        service!!.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith (object : DisposableObserver<Data>() {

                override fun onComplete() {}

                override fun onNext(t: Data) {
                    userData?.let {
                        it.addAll(t.data)
                        recyclerview.adapter = UsersAdapter(it)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR", e.message)
                }

            })

        fab.setOnClickListener {
            val dialog = AlertDialog.Builder(this@MainActivity).create()
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_new_user, null)

            val button1 = dialogView.buttonSubmit
            val button2 = dialogView.buttonCancel
            val firstNameField = dialogView.f_name
            val lastNameField = dialogView.l_name
            val emailField = dialogView.email

            button2.setOnClickListener {
                dialog.dismiss() }
            button1.setOnClickListener {
                service!!.createUser(firstNameField.text.toString(), lastNameField.text.toString(), emailField.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableObserver<UserResponse>() {

                        override fun onError(e: Throwable) {
                            Log.e("ERROR", e.message)
                        }

                        override fun onNext(t: UserResponse) {
                            val newUser = Users()
                            newUser.firstName = t.firstName
                            newUser.lastName = t.lastName
                            newUser.email = t.email
                            newUser.id = t.id.toInt()

                            userData!!.add(newUser)
                            recyclerview.adapter!!.notifyDataSetChanged()

                            Log.e("SUCCESS", t.toString())
                        }

                        override fun onComplete() {
                        }
                })
                dialog.dismiss()
            }

            dialog.setView(dialogView);
            dialog.show();

        }
    }
}
