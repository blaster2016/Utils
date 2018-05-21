
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dinesh on 3/21/2018.
 */
public class DateParser {

    private Calendar mCalender;
    private static LocalDateParser instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DateParser getInstance() {
        if (null == instance)
            instance = new LocalDateParser();
        return instance;
    }

    /**
     * Gets date string.
     *
     * @param date        the date
     * @param parsingType the parsing type
     * @param resultDate  the result date
     * @return the date string
     */
    public String getDateString(String date, DATE parsingType, DATE resultDate) {
        Object result = parseDate(false, date, parsingType, resultDate);
        return null == result ? "" : result.toString();
    }

    private Object parseDate(boolean returnDate, String date, DATE parsingType, DATE resultDate) {
        if (resultDate == null)
            return "";
        if (parsingType == null)
            return "";
        if (parsingType == DATE.LOCALE_CURRENT) {
            SimpleDateFormat outputFormat = new SimpleDateFormat(resultDate.toString(), Locale.ENGLISH);
            if (returnDate)
                try {
                    return outputFormat.parse(outputFormat.format(new Date()));
                } catch (ParseException ignored) {
                    return new Date();
                }
            return outputFormat.format(Calendar.getInstance().getTime());
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat(parsingType.toString(), Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(resultDate.toString(), Locale.ENGLISH);
        Object result = null;
        try {
            if (returnDate)
                result = outputFormat.parse(inputFormat.parse(date).toString());
            else
                result = outputFormat.format(inputFormat.parse(date));
        } catch (ParseException ignored) {
        }
        return result;
    }

    /**
     * Gets date.
     *
     * @param date        the date
     * @param parsingType the parsing type
     * @param resultDate  the result date
     * @return the date
     */
    public Date getDate(String date, DATE parsingType, DATE resultDate) {
        Object result = parseDate(true, date, parsingType, resultDate);
        if (result instanceof Date)
            return (Date) result;
        return null;
    }

    /**
     * Parse date string.
     *
     * @param date        the date
     * @param parsingType the parsing type
     * @return the string
     */
    public String parseDate(Date date, DATE parsingType) {
        DateFormat df = new SimpleDateFormat(parsingType.toString(), Locale.ENGLISH);
        return df.format(date);
    }

    /**
     * Gets date.
     *
     * @param date        the date
     * @param parsingType the parsing type
     * @return the date
     */
    public Date getDate(String date, DATE parsingType) {
        DateFormat df = new SimpleDateFormat(parsingType.toString(), Locale.ENGLISH);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * Gets day.
     *
     * @param date the date
     * @return the day
     */
    public String getDay(Date date) {
        if (null == date)
            return "";
        initCalender(date);
        return Integer.toString(mCalender.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Gets month.
     *
     * @param date the date
     * @return the month
     */
    public String getMonth(Date date) {
        if (null == date)
            return "";
        initCalender(date);
        return Integer.toString(mCalender.get(Calendar.MONTH));
    }

    /**
     * Gets year.
     *
     * @param date the date
     * @return the year
     */
    public String getYear(Date date) {
        if (null == date)
            return "";
        initCalender(date);
        return Integer.toString(mCalender.get(Calendar.YEAR));
    }

    /**
     * Initialize Calender
     */

    private void initCalender(Date date) {
        if (null == mCalender)
            mCalender = Calendar.getInstance(Locale.ENGLISH);
        if (null != date)
            mCalender.setTime(date);
    }

    /**
     * Declaring the Input and Output Format for provided Date
     */
    public enum DATE {

        LOCALE_T {
            @Override
            public String toString() {
                return "yyyy-MM-dd'T'HH:mm:ss";
            }
        },

        LOCALE_CURRENT,

        LOCALE_T_SSS {
            @Override
            public String toString() {
                return "yyyy-MM-dd'T'HH:mm:ss.SSS";
            }
        },

        LOCALE {
            @Override
            public String toString() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },

        OUTPUT_YYYY {
            @Override
            public String toString() {
                return "yyyy";
            }
        },

        OUTPUT_DAY_TIME {
            @Override
            public String toString() {
                return "E, dd-MMM-yyyy HH:mm:ss";
            }
        },

        OUTPUT_DAY_DATE {
            @Override
            public String toString() {
                return "E, dd-MMM-yyyy";
            }
        },

        OUTPUT_MM_YYYY {
            @Override
            public String toString() {
                return "MMM yyyy";
            }
        },

        OUTPUT_DD_MM_YYYY {
            @Override
            public String toString() {
                return "dd-MM-yyyy";
            }
        },

        OUTPUT_MM_DD_YYYY {
            @Override
            public String toString() {
                return "MM dd yyyy";
            }
        },

        OUTPUT_DD_MM_YYYY_HHMMSS {
            @Override
            public String toString() {
                return "dd-MMM-yyyy HH:mm:ss";
            }
        },

        OUTPUT_YYYY_MM_DD_HHMMSS {
            @Override
            public String toString() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },

        OUTPUT_YYYY_MM_DD_hhMMSS {
            @Override
            public String toString() {
                return "yyyy-MM-dd hh:mm:ss";
            }
        },

        OUTPUT_DD_MMM_YYYY {
            @Override
            public String toString() {
                return "dd-MMM-yyyy";
            }
        },

        OUTPUT_YYYY_MM_DD {
            @Override
            public String toString() {
                return "yyyy-MM-dd";
            }
        },

        OUTPUT_T_Z {
            @Override
            public String toString() {
                return "yyyy-MM-dd'T'HH:mm:ss Z";
            }
        },

        OUTPUT_T_S {
            @Override
            public String toString() {
                return "yyyy-MM-dd'T'HH:mm:ss.sss";
            }
        },

        OUTPUT_D_MMM_YYYY {
            @Override
            public String toString() {
                return "d-MMM-yyyy";
            }
        },

        OUTPUT_HH_MM_A {
            @Override
            public String toString() {
                return "hh:mm a";
            }
        },

        OUTPUT_DD_MMM_YYYY_HH_MM_A {
            @Override
            public String toString() {
                return "dd-MM-yyyy hh:mm a";
            }
        },

        OUTPUT_DD_MMM_YYYY_HH_MM_SS_A {
            @Override
            public String toString() {
                return "dd-MMM-yyyy hh:mm:ss a";
            }
        }
    }

}
