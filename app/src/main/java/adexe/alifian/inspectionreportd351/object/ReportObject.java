package adexe.alifian.inspectionreportd351.object;

/**
 * Created by adexe on 6/21/18.
 */

public class ReportObject {
    private String id;
    private String timeStamp;
    private String mekanik;
    private String no_polisi;
    private String catatan;
    private String no_telp_customer;
    private String type;

    public ReportObject(String id,String timeStamp, String mekanik, String no_polisi, String catatan,String no_telp_customer, String type){
        this.id = id;
        this.timeStamp = timeStamp;
        this.mekanik = mekanik;
        this.no_polisi = no_polisi;
        this.catatan = catatan;
        this.no_telp_customer = no_telp_customer;
        this.type = type;
    }

    public String getReportId(){
        return id;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getMekanik(){
        return mekanik;
    }

    public String getNoPolisi(){
        return no_polisi;
    }

    public String getCatatan(){
        return catatan;
    }

    public String getNoTelpCustomer(){
        return no_telp_customer;
    }

    public String getType(){
        return type;
    }
}
