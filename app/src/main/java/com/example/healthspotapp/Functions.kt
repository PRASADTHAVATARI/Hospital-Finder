package com.example.healthspotapp

import android.app.Activity
import android.text.Spanned
import android.util.Log
import android.widget.Toast
import androidx.core.text.HtmlCompat




fun spannaed(message: String?):Spanned {
    return HtmlCompat.fromHtml("$message",HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)
}
fun Activity.anyToast(any :Any?){
    Toast.makeText(this, "$any", Toast.LENGTH_SHORT).show()
}
fun logview(message:Any?){
    Log.i("TestCasesViewPoint","$message")
}

/**/