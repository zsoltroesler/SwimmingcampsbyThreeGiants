package com.example.android.swimmingcampsbythreegiants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private WebView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This method called to justify the description text
        description = (WebView) findViewById(R.id.webview_description);
        String text = "<html><body>"
                + "<p align=\"justify\">"
                + getString(R.string.text)
                + "</p> "
                + "</body></html>";
        description.loadData(text, "text/html", "utf-8");


        // Click on the call line to initiate a call
        LinearLayout call = (LinearLayout) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to initiate a call
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.tellNumber)));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // Click on the email line to compose an email
        LinearLayout email = (LinearLayout) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to call implicit intent email app
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                // "mailto:" only email apps can handle it
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.emailAddress)});
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailSubject));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        // Click on the web line to open a browser
        LinearLayout web = (LinearLayout) findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to call implicit intent web browser app
                Uri webpage = Uri.parse(getString(R.string.url));
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }
}
