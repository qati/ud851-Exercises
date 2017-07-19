package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.content.Context;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * BoardingPassInfo is a simple POJO that contains information about, you guessed it, a boarding
 * pass! Normally, it is best practice in Java to declare member variables as private and provide
 * getters, but we are leaving these fields public for ease of use.
 */
public class BoardingPassInfo {

    public String passengerName;
    public String flightCode;
    public String originCode;
    public String destCode;

    public Timestamp boardingTime;
    public Timestamp departureTime;
    public Timestamp arrivalTime;

    public String departureTerminal;
    public String departureGate;
    public String seatNumber;

    public int barCodeImageResource;

    private SimpleDateFormat mFormatter;
    private Context mContext;

    public BoardingPassInfo(Context context){
        mFormatter = new SimpleDateFormat(context.getString(R.string.timeFormat), Locale.getDefault());
        mContext = context;
    }

    public long getMinutesUntilBoarding() {
        long millisUntilBoarding = boardingTime.getTime() - System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toMinutes(millisUntilBoarding);
    }

    public String getBoardingTime(){
        return mFormatter.format(boardingTime);
    }
    public String getDepartureTime(){
        return mFormatter.format(departureTime);
    }
    public String getArrivalTime(){
        return mFormatter.format(arrivalTime);
    }
    public String getCountDown(){
        long totalMinutesUntilBoarding = getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        return mContext.getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);
    }
}