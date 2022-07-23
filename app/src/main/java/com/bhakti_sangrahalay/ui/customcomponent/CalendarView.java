package com.bhakti_sangrahalay.ui.customcomponent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bhakti_sangrahalay.R;
import com.bhakti_sangrahalay.activity.DainikCalendarActivity;
import com.bhakti_sangrahalay.model.MonthDetailData;
import com.bhakti_sangrahalay.panchang.calculations.PanchangCalculation;
import com.bhakti_sangrahalay.util.Utility;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


/**
 * Created by a7med on 28/06/2015.
 */
public class CalendarView extends LinearLayout {
    private static final int DAYS_COUNT = 42;
    private static final String DATE_FORMAT = "MMM yyyy";
    private String dateFormat;
    private Calendar currentDate = Calendar.getInstance();
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private ExpandableHeightGridView grid;
    Context context;
    HashMap<String, Integer> iconHasmap;
    View previousSelectedView;
    int previousSelectedPosition;
    Date previousSelectedDate;


    public CalendarView(Context context) {
        super(context);
        this.context = context;
        //setHolidayIcons();
        initControl(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    /**
     * Load control xml layout
     */
    private void initControl(Context context, AttributeSet attrs) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_item_layout, this);
        //loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();
        updateCalendar();
    }

    private void initControl(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_item_layout, this);
        loadDateFormat();
        assignUiElements();
        assignClickHandlers();
        updateCalendar();
    }

    private void loadDateFormat() {
        //TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        // try to load provided date format, and fallback to default otherwise
        //dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
        //if (dateFormat == null)
        dateFormat = DATE_FORMAT;
    }

    private void assignUiElements() {
        TextView sunTextView = findViewById(R.id.sun_text);
        TextView monTextView = findViewById(R.id.mon_text);
        TextView tueTextView = findViewById(R.id.tue_text);
        TextView wedTextView = findViewById(R.id.wed_text);
        TextView thuTextView = findViewById(R.id.thu_text);
        TextView friTextView = findViewById(R.id.fri_text);
        TextView satTextView = findViewById(R.id.sat_text);

        btnPrev = findViewById(R.id.calendar_prev_button);
        btnNext = findViewById(R.id.calendar_next_button);
        txtDate = findViewById(R.id.calendar_date_display);
        grid = findViewById(R.id.calendar_grid);
        sunTextView.setTypeface(Utility.getMediumTypeface(context));
        monTextView.setTypeface(Utility.getMediumTypeface(context));
        tueTextView.setTypeface(Utility.getMediumTypeface(context));
        wedTextView.setTypeface(Utility.getMediumTypeface(context));
        thuTextView.setTypeface(Utility.getMediumTypeface(context));
        friTextView.setTypeface(Utility.getMediumTypeface(context));
        satTextView.setTypeface(Utility.getMediumTypeface(context));
        grid.setExpanded(true);

    }

    private void assignClickHandlers() {
        btnNext.setOnClickListener(v -> {
            currentDate.add(Calendar.MONTH, 1);
            updateCalendar();
        });

        btnPrev.setOnClickListener(v -> {
            currentDate.add(Calendar.MONTH, -1);
            updateCalendar();
        });

        grid.setOnItemLongClickListener((view, cell, position, id) -> true);

        grid.setOnItemClickListener((parent, view, position, id) -> {

        });

    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar() {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in grid
     */
    public void updateCalendar(Date date) {
        ArrayList<MonthDetailData> cells = new ArrayList<>();
        if (date != null) {
            currentDate.setTime(date);
        }

        Calendar calendar = (Calendar) currentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
        MonthDetailData monthDetailData;
        // fill cells
        while (cells.size() < DAYS_COUNT) {
            monthDetailData = new MonthDetailData();
            monthDetailData.setDate(calendar.getTime());
            monthDetailData.setFestName("");
            cells.add(monthDetailData);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        // update grid
        grid.setAdapter(new CalendarAdapter(getContext(), cells));

        // update title
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        txtDate.setText(sdf.format(currentDate.getTime()));
        //header.setBackgroundColor(getResources().getColor(R.color.calendar_color));
    }


    public class CalendarAdapter extends ArrayAdapter<MonthDetailData> {

        private LayoutInflater inflater;
        ArrayList<MonthDetailData> monthDetailDataArrayList;


        CalendarAdapter(Context context, ArrayList<MonthDetailData> days) {
            super(context, R.layout.control_calendar_day, days);
            inflater = LayoutInflater.from(context);
            monthDetailDataArrayList = days;
        }

        @NotNull
        @Override
        public View getView(int position, View view, @NotNull ViewGroup parent) {
            // day in question
            MonthDetailData monthDetailData = getItem(position);
            assert monthDetailData != null;
            int day = monthDetailData.getDate().getDate();
            int month = monthDetailData.getDate().getMonth();
            int year = monthDetailData.getDate().getYear();

            // today
            Date today = currentDate.getTime();

            // inflate item if it does not exist yet
            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, parent, false);

            // if this day has an event, specify event image
            TextView textView1 = view.findViewById(R.id.day_text1);
            TextView tithiTV = view.findViewById(R.id.thithi_text);
            ImageView moonPhaseImg = view.findViewById(R.id.moon_phase_iv);

            textView1.setTypeface(Utility.getMediumTypeface(context));
            tithiTV.setTypeface(Utility.getMediumTypeface(context));

            FrameLayout frameLayout = view.findViewById(R.id.main_frame);

            LinearLayout festDetailLayout = view.findViewById(R.id.fest_detail_container);
            TextView festDate = view.findViewById(R.id.datetv);
            TextView festName = view.findViewById(R.id.festName);
            ImageView festImage = view.findViewById(R.id.holiday_image);
            if (month == today.getMonth() && year == today.getYear()) {
                if (!TextUtils.isEmpty(monthDetailData.getFestName())) {
                    festDetailLayout.setVisibility(VISIBLE);
                    festImage.setVisibility(VISIBLE);
                    festImage.setImageResource(iconHasmap.get(monthDetailData.getFestName()));
                    festName.setText(monthDetailData.getFestName());
                    festDate.setText(String.valueOf(monthDetailData.getDate().getDate()));
                    textView1.setVisibility(GONE);

                } else {
                    festDetailLayout.setVisibility(GONE);
                    festImage.setVisibility(GONE);
                    textView1.setVisibility(VISIBLE);

                }
            }
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            layoutParams.height = getHeightOfCell();
            frameLayout.setLayoutParams(layoutParams);

            textView1.setBackgroundResource(0);
            textView1.setTextColor(Color.BLACK);

            if (position == 0 || position == 7 || position == 14 || position == 21 || position == 28 || position == 35) {
                frameLayout.setBackgroundColor(getResources().getColor(R.color.panchanag_bg_color));
                textView1.setTextColor(getResources().getColor(R.color.red));
            }
            if (month != today.getMonth() || year != today.getYear()) {
                textView1.setTextColor(getResources().getColor(R.color.greyed_out));
                textView1.setAlpha(.4f);
                frameLayout.setBackgroundColor(getResources().getColor(R.color.light_gray));
            } else if (day == new Date().getDate() && month == new Date().getMonth()) {
                previousSelectedView = view;
                previousSelectedPosition = position;
                previousSelectedDate = monthDetailData.getDate();
                frameLayout.setBackground(getResources().getDrawable(R.drawable.rect_rounded_image));
            }
            if (position == 0 || position == 7 || position == 14 || position == 21 || position == 28 || position == 35) {
                textView1.setTextColor(getResources().getColor(R.color.red));
            }

            textView1.setText(String.valueOf(monthDetailData.getDate().getDate()));
            Calendar cal = Calendar.getInstance();
            cal.setTime(monthDetailData.getDate());
            int tithiInt = ((DainikCalendarActivity) context).viewModel.getTithiInt(cal, Utility.getPlaceForPanchang());
            if (tithiInt > 15) {
                tithiInt = tithiInt - 15;
            }
            tithiTV.setText(tithiInt + "");
            moonPhaseImg.setImageDrawable(PanchangCalculation.INSTANCE.getMoonPhaseImage(context.getResources(), tithiInt));
            frameLayout.setOnClickListener(view1 -> {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, day);
                calendar.set(Calendar.MONTH, month);
                // calendar.set(Calendar.YEAR, year);
                ((DainikCalendarActivity) context).getPanchanDataForSelectedDate(calendar);
            });
            return view;
        }


        public void notifyData() {
            this.notifyDataSetChanged();
        }
    }

    public void setEventHandler(EventHandler eventHandler) {
    }


    public interface EventHandler {
        void onDayLongPress(Date date);
    }


    private int getHeightOfCell() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.widthPixels;
        return height / 6;
    }

    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public int getScreenHeight() {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y - 730;
        return height / 6;
    }
}
