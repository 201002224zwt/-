package Entity;

import java.io.File;

public class AcademicActivity {
    String activity_id;
    String master_id;
    String activity_name;
    String date;
    String report_name;
    //以File形式读入图片
    File certificate;
    String image_type;
    boolean tutor_view;
    boolean master_view;


    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public File getCertificate() {
        return certificate;
    }

    public void setCertificate(File certificate) {
        this.certificate = certificate;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public boolean isTutor_view() {
        return tutor_view;
    }

    public void setTutor_view(boolean tutor_view) {
        this.tutor_view = tutor_view;
    }

    public boolean isMaster_view() {
        return master_view;
    }

    public void setMaster_view(boolean master_view) {
        this.master_view = master_view;
    }
}
