package com.bhakti_sangrahalay.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bhakti_sangrahalay.R
import com.bhakti_sangrahalay.model.SuggestionModel
import com.bhakti_sangrahalay.ui.activity.BaseActivity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class SuggestionActivity : BaseActivity(), OnClickListener {
    private lateinit var nameTV: TextView
    private lateinit var phoneTV: TextView
    private lateinit var emailTV: TextView
    private lateinit var suggestionTV: TextView
    private lateinit var nameET: EditText
    private lateinit var phoneET: EditText
    private lateinit var emailET: EditText
    private lateinit var suggestionET: EditText
    private lateinit var submitButton: Button
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggetion_layout)
        setTitle(resources.getString(R.string.suggestion))
        nameTV = findViewById(R.id.nameTv)
        phoneTV = findViewById(R.id.phTV)
        emailTV = findViewById(R.id.emailTV)
        suggestionTV = findViewById(R.id.suggestionTV)
        nameET = findViewById(R.id.nameET)
        phoneET = findViewById(R.id.phET)
        emailET = findViewById(R.id.emailET)
        suggestionET = findViewById(R.id.suggestionET)
        submitButton = findViewById(R.id.submit_request_btn)
        submitButton.setOnClickListener(this)
        setTypeface()
    }

    override fun attachViewModel() {
    }

     override fun setTypeface() {
        nameTV.typeface = mediumTypeface
        phoneTV.typeface = mediumTypeface
        emailTV.typeface = mediumTypeface
        suggestionTV.typeface = mediumTypeface
        nameET.typeface = mediumTypeface
        phoneET.typeface = mediumTypeface
        emailET.typeface = mediumTypeface
        suggestionET.typeface = mediumTypeface
        submitButton.typeface = mediumTypeface
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.submit_request_btn -> {
                //finish()
                val name = nameET.text.toString()
                val phoneNumber = phoneET.text.toString()
                val email = emailET.text.toString()
                val suggestion = suggestionET.text.toString()

                // validating the text fields if empty or not.
                when {
                    TextUtils.isEmpty(name) -> {
                        nameET.setError("Please enter Name")
                    }
                    TextUtils.isEmpty(phoneNumber) -> {
                        phoneET.setError("Please enter Phone Number")
                    }
                    TextUtils.isEmpty(email) -> {
                        emailET.setError("Please enter Email Address")
                    }
                    TextUtils.isEmpty(suggestion) -> {
                        suggestionET.setError("Please enter Suggestion")
                    }
                    else -> {
                        // calling method to add data to Firebase Firestore.
                        addDataToFirestore(name, phoneNumber, email, suggestion)
                    }
                }
            }
        }
    }

    private fun addDataToFirestore(name: String, phoneNumber: String, email: String, suggestion: String) {

        // creating a collection reference
        // for our Firebase Firetore database.
        val dbCourses: CollectionReference = db.collection("Suggestion")

        // adding our data to our courses object class.
        val suggestionModel = SuggestionModel(name, phoneNumber, email, suggestion)

        // below method is use to add data to Firebase Firestore.
        dbCourses.add(suggestionModel).addOnSuccessListener { // after the data addition is successful
            // we are displaying a success toast message.
            Toast.makeText(this@SuggestionActivity, "Your Suggestion has been sent sucessfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e -> // this method is called when the data addition process is failed.
            // displaying a toast message when data addition is failed.
            Toast.makeText(this@SuggestionActivity, "Fail to send Suggestion \n$e", Toast.LENGTH_SHORT).show()
        }
    }
}