package adexe.alifian.inspectionreportd351.object;

/**
 * Created by adexe on 6/21/18.
 */

public class ReportObject {

    private String reportId;
    private String timeStamp;
    private String mekanik;
    private String noPolisi;
    private String catatan;
    private String noTelpCustomer;
    private String type;

    public ReportObject(String noPolisi,String mekanik,String catatan, String type,String reportId,String timeStamp,String noTelpCustomer){
        this.noPolisi = noPolisi;
        this.mekanik = mekanik;
        this.catatan = catatan;
        this.type = type;
        this.reportId = reportId;
        this.timeStamp = timeStamp;
        this.noTelpCustomer = noTelpCustomer;
    }

    public ReportObject(){
    }


    public String getReportId(){
        return reportId;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getMekanik(){
        return mekanik;
    }

    public String getNoPolisi(){
        return noPolisi;
    }

    public String getCatatan(){
        return catatan;
    }

    public String getNoTelpCustomer(){
        return noTelpCustomer;
    }

    public String getType(){
        return type;
    }
}
