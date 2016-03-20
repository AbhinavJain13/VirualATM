package com.example.ramakant.virualatm;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import Utils.DataHub;
import fragments.ATMFragment;
import fragments.AccountSummaryFragment;
import fragments.BalanceInfo;
import fragments.Mini_Statement_Fragment;
import fragments.NavigationDrawerFragment;
import fragments.NeedHelpFragment;
import fragments.SavedCardsFragment;
import fragments.VirtualATM;
import networking.SharedPreference;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    public CharSequence mTitle;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
       /* FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(posit]on + 1))
                .commit();*/

        switch (position)
        {
            case 0:

                ATMFragment fragment = new ATMFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("rowId",0);
                fragment.setArguments(bundle1);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 1:
                VirtualATM virtualATM = new VirtualATM();
                FragmentManager fragmentManager8 = getSupportFragmentManager();
                fragmentManager8.beginTransaction()
                        .replace(R.id.container, virtualATM)
                        .commit();
                break;

            case 2:
                Mini_Statement_Fragment fragmentMiniStat = new Mini_Statement_Fragment();
                FragmentManager fragmentManager6 = getSupportFragmentManager();
                fragmentManager6.beginTransaction()
                        .replace(R.id.container, fragmentMiniStat)
                        .commit();

                break;

            case 3:
               SavedCardsFragment fragmentSavedCards = new SavedCardsFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isTick", false);
                fragmentSavedCards.setArguments(bundle);
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.container, fragmentSavedCards)
                        .commit();
                break;

            case 4:
                AccountSummaryFragment fragmentAccountSummary = new AccountSummaryFragment();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2.beginTransaction()
                        .replace(R.id.container, fragmentAccountSummary)
                        .commit();
                break;

            case 5:
                BalanceInfo balanceInfoFragment = new BalanceInfo();
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                fragmentManager4.beginTransaction()
                        .replace(R.id.container, balanceInfoFragment)
                        .commit();
                break;

            case 6:
                NeedHelpFragment needHelpFragment = new NeedHelpFragment();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3.beginTransaction()
                        .replace(R.id.container, needHelpFragment)
                        .commit();

                break;
            case 7:
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Virtual ATM");
                i.putExtra(android.content.Intent.EXTRA_TEXT,"Virtual ATM");
                startActivity(Intent.createChooser(i,"Share via"));
                break;

            case 8:
                new AlertDialog.Builder(this)
                        //.setTitle("Delete entry")
                        .setMessage("Do you really want to Log Out from Virtual ATM ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreference.getInstance(getApplicationContext()).putInSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
                                Intent intent = new Intent(NavigationDrawer.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;



        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.generate_pin);
                break;
            case 2:
                mTitle = getString(R.string.title_mini_statement);
                break;
            case 3:
                mTitle = getString(R.string.title_saved_cards);
                break;
            case 4:
                mTitle = getString(R.string.title_account_summary);
                break;
            case 5:
                mTitle = getString(R.string.title_balance_info);
                break;
            case 6:
                mTitle = getString(R.string.title_need_help);
                break;
            case 7:
                mTitle = getString(R.string.title_share_via);
                break;
            case 8:
                mTitle = getString(R.string.title_logout);
                break;
            case 9:
                mTitle = getString(R.string.title_atm);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6CA6CD")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.navigation_drawer, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((NavigationDrawer) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
