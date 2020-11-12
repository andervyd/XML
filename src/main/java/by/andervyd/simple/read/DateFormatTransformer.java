package by.andervyd.simple.read;

import org.simpleframework.xml.transform.Transform;

import java.text.DateFormat;
import java.util.Date;


public class DateFormatTransformer implements Transform<Date> {

    private DateFormat dateFormat;

    public DateFormatTransformer(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Date read(String arg0) throws Exception {
        return dateFormat.parse(arg0);
    }

    @Override
    public String write(Date arg0) throws Exception {
        return dateFormat.format(arg0);
    }

}
