package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * This ViewHolder class is help to represent data in ListView by recycling.
     */
    static class ViewHolder {

        private TextView sectionTextView;
        private TextView authorTextView;
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView timeTextView;
    }


    /**
     * Create a new {@link NewsAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param object  is the list of {@link News} to be displayed.
     */
    public NewsAdapter(Context context, ArrayList<News> object) {
        super(context, 0, object);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Create a ViewHolder object.
        ViewHolder holder;

        // Check if the existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.news_list_item, parent, false);
            holder = new ViewHolder();
            holder.sectionTextView = convertView.findViewById(R.id.section);
            holder.authorTextView = convertView.findViewById(R.id.author);
            holder.titleTextView = convertView.findViewById(R.id.title);
            holder.dateTextView = convertView.findViewById(R.id.date);
            holder.timeTextView = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the News object located at this position in the list.
        News currentNews = getItem(position);

        // Create a new Date object from the time in ISO-8601 format of the news.
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss'Z'", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(currentNews.getPublicationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Format the date string (i.e. "Mar 3, 1984").
        String formattedDate = formatDate(dateObject);
        // Format the time string (i.e. "4:30PM").
        String formattedTime = formatTime(dateObject);

        // Set proper data in news_list_item by using ViewHolder.
        holder.sectionTextView.setText(currentNews.getSectionName());
        holder.authorTextView.setText(currentNews.getAuthor());
        holder.titleTextView.setText(currentNews.getTitle());
        holder.dateTextView.setText(formattedDate);
        holder.timeTextView.setText(formattedTime);

        return convertView;
    }


    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     *
     * @param dateObject is the time of news in ISO-8601 format.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(dateObject);
    }


    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     *
     * @param dateObject is the time of news in ISO-8601 format0.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        timeFormat.setTimeZone(TimeZone.getDefault());
        return timeFormat.format(dateObject);
    }
}

