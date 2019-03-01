package com.anwesh.uiprojects.linkedsquarethreebarview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.squarethreebarview.SquareThreeBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SquareThreeBarView.create(this)
    }
}
