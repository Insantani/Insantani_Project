package com.williamhenry.insantani;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        TextView privacy= (TextView) findViewById(R.id.privacyPolicy);
        privacy.setText("Privacy Policy\n\n" +
                "This privacy policy has been compiled to better serve those who are concerned with how their 'Personally identifiable information' (PII) is being used online. PII, as used in US privacy law and information security, is information that can be used on its own or with other information to identify, contact, or locate a single person, or to identify an individual in context. Please read our privacy policy carefully to get a clear understanding of how we collect, use, protect or otherwise handle your Personally Identifiable Information in accordance with our website.\n" +
                "\n" +
                "What personal information do we collect from the people that visit our blog, website or app?\n" +
                "\n" +
                "When ordering or registering on our site, as appropriate, you may be asked to enter your name, email address, mailing address, phone number or other details to help you with your experience.\n" +
                "\n" +
                "When do we collect information?\n" +
                "\n" +
                "We collect information from you when you register on our site, place an order, respond to a survey or enter information on our site.\n" +
                "\n" +
                "\n" +
                "How do we use your information?\n" +
                "\n" +
                "We may use the information we collect from you when you register, make a purchase, sign up for our newsletter, respond to a survey or marketing communication, surf the website, or use certain other site features in the following ways:\n" +
                "\n" +
                "      • To allow us to better service you in responding to your customer service requests.\n" +
                "      • To administer a contest, promotion, survey or other site feature.\n" +
                "      • To quickly process your transactions.\n" +
                "\n" +
                "How do we protect visitor information?\n" +
                "\n" +
                "Our website is scanned on a regular basis for security holes and known vulnerabilities in order to make your visit to our site as safe as possible.\n" +
                "\n" +
                "We do not use Malware Scanning.\n" +
                "\n" +
                "We do not use an SSL certificate\n" +
                "      • We do not need an SSL because:\n" +
                "we need to spend extra money on it\n" +
                "\n" +
                "Do we use 'cookies'?\n" +
                "\n" +
                "We do not use cookies for tracking purposes\n" +
                "\n" +
                "You can choose to have your computer warn you each time a cookie is being sent, or you can choose to turn off all cookies. You do this through your browser (like Internet Explorer) settings. Each browser is a little different, so look at your browser's Help menu to learn the correct way to modify your cookies.\n" +
                "\n" +
                "If you disable cookies off, some features will be disabled that make your site experience more efficient and some of our services will not function properly.\n" +
                "\n" +
                "However, you can still place orders .\n" +
                "\n" +
                "\n" +
                "Third Party Disclosure\n" +
                "\n" +
                "We do not sell, trade, or otherwise transfer to outside parties your personally identifiable information unless we provide you with advance notice. This does not include website hosting partners and other parties who assist us in operating our website, conducting our business, or servicing you, so long as those parties agree to keep this information confidential. We may also release your information when we believe release is appropriate to comply with the law, enforce our site policies, or protect ours or others' rights, property, or safety. \n" +
                "\n" +
                "However, non-personally identifiable visitor information may be provided to other parties for marketing, advertising, or other uses.\n" +
                "\n" +
                "Third party links\n" +
                "\n" +
                "We do not include or offer third party products or services on our website.\n" +
                "\n" +
                "Google\n" +
                "\n" +
                "Google's advertising requirements can be summed up by Google's Advertising Principles. They are put in place to provide a positive experience for users. https://support.google.com/adwordspolicy/answer/1316548?hl=en \n" +
                "\n" +
                "We have not enabled Google AdSense on our site but we may do so in the future.\n" +
                "\n" +
                "California Online Privacy Protection Act\n" +
                "\n" +
                "CalOPPA is the first state law in the nation to require commercial websites and online services to post a privacy policy. The law's reach stretches well beyond California to require a person or company in the United States (and conceivably the world) that operates websites collecting personally identifiable information from California consumers to post a conspicuous privacy policy on its website stating exactly the information being collected and those individuals with whom it is being shared, and to comply with this policy. - See more at: http://consumercal.org/california-online-privacy-protection-act-caloppa/#sthash.0FdRbT51.dpuf\n" +
                "\n" +
                "According to CalOPPA we agree to the following:\n" +
                "Users can visit our site anonymously\n" +
                "Once this privacy policy is created, we will add a link to it on our home page, or as a minimum on the first significant page after entering our website.\n" +
                "Our Privacy Policy link includes the word 'Privacy', and can be easily be found on the page specified above.\n" +
                "\n" +
                "Users will be notified of any privacy policy changes:\n" +
                "      • Via Email\n" +
                "Users are able to change their personal information:\n" +
                "      • By logging in to their account\n" +
                "\n" +
                "How does our site handle do not track signals?\n" +
                "We honor do not track signals and do not track, plant cookies, or use advertising when a Do Not Track (DNT) browser mechanism is in place.\n" +
                "\n" +
                "Does our site allow third party behavioral tracking?\n" +
                "It's also important to note that we do not allow third party behavioral tracking\n" +
                "\n" +
                "COPPA (Children Online Privacy Protection Act)\n" +
                "\n" +
                "When it comes to the collection of personal information from children under 13, the Children's Online Privacy Protection Act (COPPA) puts parents in control. The Federal Trade Commission, the nation's consumer protection agency, enforces the COPPA Rule, which spells out what operators of websites and online services must do to protect children's privacy and safety online.\n" +
                "\n" +
                "We do not specifically market to children under 13.\n" +
                "\n" +
                "Fair Information Practices\n" +
                "\n" +
                "The Fair Information Practices Principles form the backbone of privacy law in the United States and the concepts they include have played a significant role in the development of data protection laws around the globe. Understanding the Fair Information Practice Principles and how they should be implemented is critical to comply with the various privacy laws that protect personal information.\n" +
                "\n" +
                "In order to be in line with Fair Information Practices we will take the following responsive action, should a data breach occur:\n" +
                "We will notify the users via email\n" +
                "      • Within 1 business day\n" +
                "\n" +
                "We also agree to the individual redress principle, which requires that individuals have a right to pursue legally enforceable rights against data collectors and processors who fail to adhere to the law. This principle requires not only that individuals have enforceable rights against data users, but also that individuals have recourse to courts or a government agency to investigate and/or prosecute non-compliance by data processors.\n" +
                "\n" +
                "CAN SPAM Act\n" +
                "\n" +
                "The CAN-SPAM Act is a law that sets the rules for commercial email, establishes requirements for commercial messages, gives recipients the right to have emails stopped from being sent to them, and spells out tough penalties for violations.\n" +
                "\n" +
                "We collect your email address in order to:\n" +
                "\n" +
                "To be in accordance with CANSPAM we agree to the following:\n" +
                "\n" +
                "If at any time you would like to unsubscribe from receiving future emails, you can email us at\n" +
                "and we will promptly remove you from ALL correspondence.\n" +
                "\n" +
                "If there are any questions regarding this privacy policy you may contact us using the information below.\n" +
                "\n" +
                "jalan surya asih 1 blok m/6\n" +
                "jakarta, jakarta 11520\n" +
                "indonesia\n" +
                "support@insantani.zendesk.com\n" +
                "\n" +
                "Last Edited on 2015-12-20");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.) {
//            return true;
//        }

        switch (id) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }

}
