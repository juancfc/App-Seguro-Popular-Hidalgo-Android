package com.repss.apprepss;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Introduccion extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private Button anteriorButton, siguienteButton, terminarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduccion);

        final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);

        if(settings.getBoolean("introduccion",false)){
            Intent properties = new Intent(Introduccion.this, Login.class);
            startActivity(properties);
        }


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        anteriorButton = (Button)findViewById(R.id.anteriorButton);
        siguienteButton = (Button)findViewById(R.id.siguienteButton);
        terminarButton = (Button)findViewById(R.id.terminarButton);

        anteriorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
            }
        });

        siguienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
            }
        });

        terminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences settings = getSharedPreferences("UserSettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("introduccion",true);
                editor.apply();
                startActivity(new Intent(Introduccion.this, Login.class));
            }
        });

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position)
                {
                    case 0:
                    {
                        if(anteriorButton.getVisibility() == View.VISIBLE )
                            anteriorButton.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case 1:
                    {
                        if(anteriorButton.getVisibility() == View.INVISIBLE )
                            anteriorButton.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 6:
                    {
                        if(siguienteButton.getVisibility() == View.GONE)
                        {
                            siguienteButton.setVisibility(View.VISIBLE);
                            terminarButton.setVisibility(View.GONE);
                        }
                        break;
                    }
                    case 7:
                    {
                        siguienteButton.setVisibility(View.GONE);
                        terminarButton.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                    {
                        if(anteriorButton.getVisibility() == View.VISIBLE )
                            anteriorButton.setVisibility(View.INVISIBLE);
                        break;
                    }
                    case 1:
                    {
                        if(anteriorButton.getVisibility() == View.INVISIBLE )
                            anteriorButton.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 6:
                    {
                        if(siguienteButton.getVisibility() == View.GONE)
                        {
                            siguienteButton.setVisibility(View.VISIBLE);
                            terminarButton.setVisibility(View.GONE);
                        }
                        break;
                    }
                    case 7:
                    {
                        siguienteButton.setVisibility(View.GONE);
                        terminarButton.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            View rootView = inflater.inflate(R.layout.fragment_introduccion, container, false);
            ImageView imageViewTutorial = (ImageView) rootView.findViewById(R.id.imageViewTutorial);

            int seccion = getArguments().getInt(ARG_SECTION_NUMBER);

            switch (seccion)
            {
                case 1:
                {
                    imageViewTutorial.setImageResource(R.drawable.uno);
                    break;
                }
                case 2:
                {
                    imageViewTutorial.setImageResource(R.drawable.dos);
                    break;
                }
                case 3:
                {
                    imageViewTutorial.setImageResource(R.drawable.tres);
                    break;
                }
                case 4:
                {
                    imageViewTutorial.setImageResource(R.drawable.cuatro);
                    break;
                }
                case 5:
                {
                    imageViewTutorial.setImageResource(R.drawable.cinco);
                    break;
                }
                case 6:
                {
                    imageViewTutorial.setImageResource(R.drawable.seis);
                    break;
                }
                case 7:
                {
                    imageViewTutorial.setImageResource(R.drawable.siete);
                    break;
                }
                case 8:
                {
                    imageViewTutorial.setImageResource(R.drawable.nueve);
                    break;
                }
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 8;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
                case 3:
                    return "SECTION 4";
                case 4:
                    return "SECTION 5";
                case 5:
                    return "SECTION 6";
                case 6:
                    return "SECTION 7";
                case 7:
                    return "SECTION 8";
            }
            return null;
        }
    }
}
